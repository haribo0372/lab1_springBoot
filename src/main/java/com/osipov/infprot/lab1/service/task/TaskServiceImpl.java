package com.osipov.infprot.lab1.service.task;

import com.osipov.infprot.lab1.entity.Task;
import com.osipov.infprot.lab1.entity.TaskInformation;
import com.osipov.infprot.lab1.entity.User;
import com.osipov.infprot.lab1.exceptions.model.NotFoundException;
import com.osipov.infprot.lab1.mapper.TaskMapper;
import com.osipov.infprot.lab1.dto.task.NewTaskRequest;
import com.osipov.infprot.lab1.dto.task.TaskDto;
import com.osipov.infprot.lab1.dto.task.UpdateTaskRequest;
import com.osipov.infprot.lab1.repository.TaskInfoRepository;
import com.osipov.infprot.lab1.repository.TaskRepository;
import com.osipov.infprot.lab1.service.base.BaseService;
import com.osipov.infprot.lab1.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
public class TaskServiceImpl extends BaseService<Task> implements TaskService {
    private final UserServiceImpl userService;
    private final TaskRepository rootRepo;
    private final TaskInfoRepository taskInfoRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository repository,
                           UserServiceImpl userService, TaskInfoRepository taskInfoRepository) {
        super(repository, "entity.Task");
        this.rootRepo = repository;
        this.userService = userService;
        this.taskInfoRepository = taskInfoRepository;
    }

    @Override
    public TaskDto getById(Long initiatorId, Long taskId) {
        userService.existsById(initiatorId);

        return TaskMapper.toDto(this.getTaskIfAccess(initiatorId, taskId));
    }

    @Override
    public Collection<TaskDto> getAllByInitiatorId(Long initiatorId) {
        userService.existsById(initiatorId);

        List<Task> tasks = rootRepo.findAllByInitiatorId(initiatorId);
        log.debug("Задачи пользователя entity.User{id={}} нашлись, [size={}]", initiatorId, tasks.size());
        return TaskMapper.toDto(tasks);
    }

    @Override
    public TaskDto create(Long initiatorId, NewTaskRequest newTaskRequest) {
        User initiator = userService.getUserById(initiatorId);
        Task task = new Task(null, newTaskRequest.getText(), null, initiator, new HashSet<>());

        TaskInformation taskInformation = new TaskInformation(
                null,
                task,
                LocalDateTime.now(),
                newTaskRequest.getDescription(),
                newTaskRequest.getTaskPriority());

        task.setInformation(taskInformation);
        taskInformation.setTask(task);
        return TaskMapper.toDto(super.save(task));
    }

    @Override
    public TaskDto update(Long initiatorId, UpdateTaskRequest updateTaskRequest) {
        userService.existsById(initiatorId);

        Task storageTask = getTaskIfAccess(initiatorId, updateTaskRequest.getId());
        if (updateTaskRequest.getText() != null){
            storageTask.setText(updateTaskRequest.getText());
        }

        if (updateTaskRequest.getDescription() != null){
            storageTask.getInformation().setDescription(updateTaskRequest.getDescription());
        }

        return TaskMapper.toDto(super.save(storageTask));
    }

    @Override
    public void deleteById(Long initiatorId, Long taskId) {
        Task task = getTaskIfAccess(initiatorId, taskId);
        super.deleteById(task.getId());
    }

    public void saveTask(Task task){
        super.save(task);
    }

    public Task getTaskIfAccess(Long initiatorId, Long taskId) {
        return rootRepo.findByInitiatorIdAndId(initiatorId, taskId).orElseThrow(() -> {
            String message = format("%s{id=%d} не найден или недоступен", nameClassForLog, taskId);
            log.debug("getIfAccess(initiatorId={}, taskId={}) -> {}", initiatorId, taskId, message);
            return new NotFoundException(message);
        });
    }
}
