package com.osipov.infprot.lab1.service.subtask;

import com.osipov.infprot.lab1.dto.subtask.RequestNewSubtask;
import com.osipov.infprot.lab1.dto.subtask.RequestUpdateSubtask;
import com.osipov.infprot.lab1.dto.subtask.SubtaskDto;
import com.osipov.infprot.lab1.entity.Subtask;
import com.osipov.infprot.lab1.entity.Task;
import com.osipov.infprot.lab1.exceptions.model.NotFoundException;
import com.osipov.infprot.lab1.mapper.SubtaskMapper;
import com.osipov.infprot.lab1.repository.SubtaskRepository;
import com.osipov.infprot.lab1.service.base.BaseService;
import com.osipov.infprot.lab1.service.task.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
public class SubtaskServiceImpl extends BaseService<Subtask> implements SubtaskService {
    private final SubtaskRepository rootRepo;
    private final TaskServiceImpl taskService;

    @Autowired
    public SubtaskServiceImpl(SubtaskRepository repository, TaskServiceImpl taskService) {
        super(repository, "entity.Subtask");
        this.rootRepo = repository;
        this.taskService = taskService;
    }

    @Override
    public SubtaskDto create(Long initiatorId, RequestNewSubtask newSubtask) {
        Task task = taskService.getTaskIfAccess(initiatorId, newSubtask.getTaskId());
        Subtask subtask = SubtaskMapper.fromDto(newSubtask, task);
        task.addSubtask(subtask);
        return SubtaskMapper.toSubtaskDto(super.save(subtask));
    }

    @Override
    public Collection<SubtaskDto> getAllByTaskId(Long initiatorId, Long taskId) {
        Task task = taskService.getTaskIfAccess(initiatorId, taskId);
        return SubtaskMapper.toSubtaskDto(rootRepo.findAllByTaskId(task.getId()));
    }

    @Override
    public SubtaskDto getById(Long initiatorId, Long subtaskId) {
        return SubtaskMapper.toSubtaskDto(getSubtaskIfAccess(initiatorId, subtaskId));
    }

    @Override
    public SubtaskDto update(Long initiatorId, RequestUpdateSubtask updateSubtask) {
        Subtask subtask = getSubtaskIfAccess(initiatorId, updateSubtask.getId());
        subtask.setText(updateSubtask.getText());
        return SubtaskMapper.toSubtaskDto(super.save(subtask));
    }

    @Override
    public void delete(Long initiatorId, Long subtaskId) {
        Subtask subtask = getSubtaskIfAccess(initiatorId, subtaskId);
        Task task = subtask.getTask();
        task.getSubtasks().removeIf(i -> i.getId().equals(subtaskId));
        taskService.saveTask(task);
        super.deleteById(subtask.getId());
    }

    private Subtask getSubtaskIfAccess(Long initiatorId, Long subtaskId) {
        Optional<Subtask> subtaskOpt = rootRepo.findById(subtaskId);
        if (subtaskOpt.isEmpty() || !Objects.equals(subtaskOpt.get().getTask().getInitiator().getId(), initiatorId)) {
            String message = format("%s{id=%d} не найден или недоступен", nameClassForLog, subtaskId);
            log.debug("getSubtaskIfAccess(initiatorId={}, subtaskId={}) -> {}", initiatorId, subtaskId, message);
            throw new NotFoundException(message);
        }

        return subtaskOpt.get();
    }
}
