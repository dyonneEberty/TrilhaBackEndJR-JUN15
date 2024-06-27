package codigocerto.api.trilhabackend.repository;

import codigocerto.api.trilhabackend.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
