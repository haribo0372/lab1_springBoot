package com.osipov.infprot.lab1.service.task;

import com.osipov.infprot.lab1.dto.task.NewTaskRequest;
import com.osipov.infprot.lab1.dto.task.TaskDto;
import com.osipov.infprot.lab1.dto.task.UpdateTaskRequest;

import java.util.Collection;

public interface TaskService {
    TaskDto getById(Long initiatorId, Long taskId);
    Collection<TaskDto> getAllByInitiatorId(Long initiatorId);
    TaskDto create(Long initiatorId, NewTaskRequest newTaskRequest);
    TaskDto update(Long initiatorId, UpdateTaskRequest updateTaskRequest);
    void deleteById(Long initiatorId, Long taskId);
}
