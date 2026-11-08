package co.ufps.refupet.auth;

import co.ufps.refupet.account.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterAccountHandlerTest {

    @Mock
    private IAccountRepository accountRepository;

    @Mock
    private PasswordHasher passwordHasher;

    @InjectMocks
    private RegisterAccountHandler handler;

    private RegisterAccountCommand validCommand;

    @BeforeEach
    void setUp() {
        validCommand = new RegisterAccountCommand(
                "usuario@refupet.com",
                "contraseña123",
                "Juan Pérez"
        );
    }

    @Nested
    @DisplayName("Registro exitoso de cuenta")
    class SuccessfulRegistration {

        @Test
        @DisplayName("Debe registrar una cuenta nueva cuando el email es único")
        void shouldRegisterNewAccountWhenEmailIsUnique() {
            when(accountRepository.existsByEmail(validCommand.email())).thenReturn(false);
            when(passwordHasher.hash(validCommand.password())).thenReturn("$2a$12$hashedPassword");
            when(accountRepository.guardar(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Account result = handler.execute(validCommand);

            assertNotNull(result);
            assertEquals(validCommand.email(), result.getEmail());
            assertEquals(Plan.BASICO, result.getPlan());
            assertEquals(AccountStatus.ACTIVE, result.getStatus());
            assertEquals(1, result.getProfileCount());
            assertEquals("Juan Pérez", result.getProfiles().get(0).getName());

            verify(accountRepository).existsByEmail(validCommand.email());
            verify(passwordHasher).hash(validCommand.password());
            verify(accountRepository).guardar(any(Account.class));
        }

        @Test
        @DisplayName("Debe hashrear la contraseña antes de guardar")
        void shouldHashPasswordBeforeSaving() {
            String hashedPassword = "$2a$12$hashedPassword";
            when(accountRepository.existsByEmail(validCommand.email())).thenReturn(false);
            when(passwordHasher.hash(validCommand.password())).thenReturn(hashedPassword);
            when(accountRepository.guardar(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

            handler.execute(validCommand);

            verify(passwordHasher).hash(validCommand.password());
            var captor = forClass(Account.class);
            verify(accountRepository).guardar(captor.capture());
            assertEquals(hashedPassword, captor.getValue().getPasswordHash());
            assertNotEquals(validCommand.password(), captor.getValue().getPasswordHash());
        }


        @Test
        @DisplayName("Debe crear un perfil inicial con el nombre proporcionado")
        void shouldCreateInitialProfileWithProvidedName() {
            when(accountRepository.existsByEmail(validCommand.email())).thenReturn(false);
            when(passwordHasher.hash(validCommand.password())).thenReturn("$2a$12$hashedPassword");
            when(accountRepository.guardar(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Account result = handler.execute(validCommand);

            assertEquals(1, result.getProfileCount());
            Profile profile = result.getProfiles().get(0);
            assertEquals("Juan Pérez", profile.getName());
            assertNotNull(profile.getProfileId());
            assertNotNull(profile.getCreatedAt());
        }

        @Test
        @DisplayName("Debe asignar el plan BASICO por defecto")
        void shouldAssignBasicPlanByDefault() {
            when(accountRepository.existsByEmail(validCommand.email())).thenReturn(false);
            when(passwordHasher.hash(validCommand.password())).thenReturn("$2a$12$hashedPassword");
            when(accountRepository.guardar(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Account result = handler.execute(validCommand);

            assertEquals(Plan.BASICO, result.getPlan());
        }
    }

    @Nested
    @DisplayName("Validación de email único")
    class EmailUniqueness {

        @Test
        @DisplayName("Debe lanzar EmailAlreadyExistsException cuando el email ya existe")
        void shouldThrowWhenEmailAlreadyExists() {
            when(accountRepository.existsByEmail(validCommand.email())).thenReturn(true);

            EmailAlreadyExistsException exception = assertThrows(
                    EmailAlreadyExistsException.class,
                    () -> handler.execute(validCommand)
            );

            assertTrue(exception.getMessage().contains(validCommand.email()));
            verify(passwordHasher, never()).hash(anyString());
            verify(accountRepository, never()).guardar(any(Account.class));
        }
    }

    @Nested
    @DisplayName("Validaciones del comando")
    class CommandValidation {

        @Test
        @DisplayName("Debe rechazar comando con email nulo")
        void shouldRejectNullEmail() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RegisterAccountCommand(null, "contraseña123", "Juan"));
        }

        @Test
        @DisplayName("Debe rechazar comando con email vacío")
        void shouldRejectBlankEmail() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RegisterAccountCommand("   ", "contraseña123", "Juan"));
        }

        @Test
        @DisplayName("Debe rechazar comando con contraseña corta")
        void shouldRejectShortPassword() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RegisterAccountCommand("email@test.com", "123456", "Juan"));
        }

        @Test
        @DisplayName("Debe rechazar comando con contraseña nula")
        void shouldRejectNullPassword() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RegisterAccountCommand("email@test.com", null, "Juan"));
        }

        @Test
        @DisplayName("Debe rechazar comando con nombre nulo")
        void shouldRejectNullName() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RegisterAccountCommand("email@test.com", "contraseña123", null));
        }

        @Test
        @DisplayName("Debe rechazar comando con nombre vacío")
        void shouldRejectBlankName() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RegisterAccountCommand("email@test.com", "contraseña123", "   "));
        }
    }
}
