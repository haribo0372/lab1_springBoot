package com.osipov.infprot.lab1.mapper;

import com.osipov.infprot.lab1.entity.Task;
import com.osipov.infprot.lab1.entity.TaskInformation;
import com.osipov.infprot.lab1.dto.task.TaskDto;
import com.osipov.infprot.lab1.dto.task.TaskInformationDto;

import java.util.Collection;

public class TaskMapper {
    public static TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getText(),
                toTaskInformationDto(task.getInformation()),
                task.getInitiator().getId(),
                SubtaskMapper.toSubtaskInTaskDto(task.getSubtasks()));
    }

    public static Collection<TaskDto> toDto(Collection<Task> tasks){
        return tasks.stream().map(TaskMapper::toDto).toList();
    }

    private static TaskInformationDto toTaskInformationDto(TaskInformation taskInformation) {
        if (taskInformation == null)
            return null;

        return new TaskInformationDto(
                taskInformation.getCreateOn(),
                taskInformation.getDescription(),
                taskInformation.getPriority());
    }
}
