package com.osipov.infprot.lab1.dto.task;

import com.osipov.infprot.lab1.model.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInformationDto {
    private LocalDateTime createOn;
    private String description;
    private TaskPriority priority;
}
