package codigocerto.api.trilhabackend.domain.user;

public record AuthenticationDTO(
        String login,
        String password
) {
}
