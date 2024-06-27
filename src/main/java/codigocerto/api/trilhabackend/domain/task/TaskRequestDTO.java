package codigocerto.api.trilhabackend.domain.task;


import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(
        @NotBlank
        String title,
        String description
) {
}
