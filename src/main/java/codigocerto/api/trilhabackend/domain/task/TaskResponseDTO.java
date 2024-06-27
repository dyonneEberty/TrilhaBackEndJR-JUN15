package codigocerto.api.trilhabackend.domain.task;

public record TaskResponseDTO(
        Long id,
        String title,
        String description
) {

    public TaskResponseDTO(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription());
    }
}
