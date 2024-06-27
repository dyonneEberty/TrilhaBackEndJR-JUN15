package codigocerto.api.trilhabackend.domain.task;

import codigocerto.api.trilhabackend.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "tasks")
@Table(name = "tasks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Task(TaskRequestDTO data) {
        this.title = data.title();
        this.description = data.description();
    }

    public void updateData(TaskUpdateDTO body) {
        if (body.title() != null){
            this.title = body.title();
        }
        if (body.description() != null){
            this.description = body.description();
        }
    }
}
