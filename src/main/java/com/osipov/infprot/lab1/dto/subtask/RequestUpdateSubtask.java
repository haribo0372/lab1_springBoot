package com.osipov.infprot.lab1.dto.subtask;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateSubtask {
    @NotNull(message = "'id' должен быть указан")
    private Long id;

    @NotBlank(message = "'text' не должен быть пустым или null")
    private String text;
}
