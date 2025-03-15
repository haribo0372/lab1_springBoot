package com.osipov.infprot.lab1.dto.subtask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskInTaskDto {
    private Long id;
    private String text;
}
