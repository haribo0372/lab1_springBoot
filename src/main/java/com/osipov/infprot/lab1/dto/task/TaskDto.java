package com.osipov.infprot.lab1.dto.task;

import com.osipov.infprot.lab1.dto.subtask.SubtaskInTaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String text;
    private TaskInformationDto information;
    private Long initiatorId;
    private Set<SubtaskInTaskDto> subtasks;
}
