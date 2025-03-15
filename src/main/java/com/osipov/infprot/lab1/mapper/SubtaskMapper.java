package com.osipov.infprot.lab1.mapper;

import com.osipov.infprot.lab1.dto.subtask.RequestNewSubtask;
import com.osipov.infprot.lab1.dto.subtask.RequestUpdateSubtask;
import com.osipov.infprot.lab1.dto.subtask.SubtaskDto;
import com.osipov.infprot.lab1.entity.Subtask;
import com.osipov.infprot.lab1.dto.subtask.SubtaskInTaskDto;
import com.osipov.infprot.lab1.entity.Task;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SubtaskMapper {
    public static Subtask fromDto(RequestNewSubtask newSubtask, Task task){
        return new Subtask(null, newSubtask.getText(), task);
    }

    public static SubtaskDto toSubtaskDto(Subtask subtask){
        return new SubtaskDto(subtask.getId(), subtask.getTask().getId(), subtask.getText());
    }

    public static Collection<SubtaskDto> toSubtaskDto(Collection<Subtask> subtasks){
        return subtasks.stream().map(SubtaskMapper::toSubtaskDto).toList();
    }

    public static SubtaskInTaskDto toSubtaskInTaskDto(Subtask subtask){
        return new SubtaskInTaskDto(subtask.getId(), subtask.getText());
    }

    public static Set<SubtaskInTaskDto> toSubtaskInTaskDto(Collection<Subtask> subtasks) {
        if (subtasks == null)
            return null;

        return subtasks.stream().map(SubtaskMapper::toSubtaskInTaskDto).collect(Collectors.toSet());
    }
}
