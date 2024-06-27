package codigocerto.api.trilhabackend.domain.task;

import jakarta.validation.constraints.NotNull;

public record TaskUpdateDTO(
        @NotNull
        Long id,
        String title,
        String description
) {
}
