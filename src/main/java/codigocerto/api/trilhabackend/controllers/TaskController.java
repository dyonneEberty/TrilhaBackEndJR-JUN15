package codigocerto.api.trilhabackend.controllers;

import codigocerto.api.trilhabackend.domain.task.Task;
import codigocerto.api.trilhabackend.domain.task.TaskRequestDTO;
import codigocerto.api.trilhabackend.domain.task.TaskResponseDTO;
import codigocerto.api.trilhabackend.domain.task.TaskUpdateDTO;
import codigocerto.api.trilhabackend.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postTask(@RequestBody @Valid TaskRequestDTO body, UriComponentsBuilder uriBuilder) {

        Task newTask = new Task(body);
        this.repository.save(newTask);

        var uri = uriBuilder.path("/task/{id}").buildAndExpand(newTask.getId()).toUri();
        return ResponseEntity.created(uri).body(new TaskResponseDTO(newTask));
    }

    @GetMapping
    public ResponseEntity getAllTasks(){
        List<TaskResponseDTO> taskList = this.repository.findAll().stream()
                .map(TaskResponseDTO::new).toList();

        return ResponseEntity.ok(taskList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTask(@RequestBody @Valid TaskUpdateDTO body){
        Task task = repository.getReferenceById(body.id());
        task.updateData(body);

        return ResponseEntity.ok(new TaskResponseDTO(task));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTask(@PathVariable Long id){

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
