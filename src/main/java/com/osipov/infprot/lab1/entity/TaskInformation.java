package com.osipov.infprot.lab1.entity;

import com.osipov.infprot.lab1.entity.base.BaseModel;
import com.osipov.infprot.lab1.model.enums.TaskPriority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "task_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskInformation extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "information")
    private Task task;

    @Column(name = "createOn", nullable = false)
    private LocalDateTime createOn;

    @Column(name = "description", nullable = false, length = 7000)
    private String description;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
