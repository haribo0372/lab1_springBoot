package com.osipov.infprot.lab1.controller;

import com.osipov.infprot.lab1.dto.task.NewTaskRequest;
import com.osipov.infprot.lab1.dto.task.TaskDto;
import com.osipov.infprot.lab1.dto.task.UpdateTaskRequest;
import com.osipov.infprot.lab1.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public Collection<TaskDto> getAll(@RequestHeader(name = "X-UserId") Long initiatorId) {
        return taskService.getAllByInitiatorId(initiatorId);
    }

    @GetMapping("/{taskId}")
    public TaskDto getById(@RequestHeader(name = "X-UserId") Long initiatorId,
                           @PathVariable Long taskId) {
        return taskService.getById(initiatorId, taskId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestHeader(name = "X-UserId") Long initiatorId,
                          @RequestBody @Valid NewTaskRequest newTaskRequest) {
        return taskService.create(initiatorId, newTaskRequest);
    }

    @PatchMapping
    public TaskDto update(@RequestHeader(name = "X-UserId") Long initiatorId,
                          @RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
        return taskService.update(initiatorId, updateTaskRequest);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader(name = "X-UserId") Long initiatorId,
                       @PathVariable Long taskId){
        taskService.deleteById(initiatorId, taskId);
    }
}
