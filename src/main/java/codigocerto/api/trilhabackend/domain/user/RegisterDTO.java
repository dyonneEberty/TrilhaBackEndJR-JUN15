package codigocerto.api.trilhabackend.domain.user;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {
}
