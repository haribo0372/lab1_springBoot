package com.osipov.infprot.lab1.dto.subtask;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestNewSubtask {
    @NotNull(message = "'taskId' должен быть указан")
    private Long taskId;

    @NotBlank(message = "'text' не должен быть пустым или null")
    private String text;
}
