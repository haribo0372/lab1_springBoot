package com.osipov.infprot.lab1.dto.task;

import com.osipov.infprot.lab1.model.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskRequest {
    @NotBlank(message = "'text' не может быть пустым или 'null'")
    @Size(min = 3, max = 200, message = "Длина 'text' должна быть не менее 3 и не более 200")
    private String text;

    @Size(min = 20, max = 7000, message = "Длина 'description' должна быть не менее 20 и не более 7000")
    private String description;

    @NotNull
    private TaskPriority taskPriority;
}
