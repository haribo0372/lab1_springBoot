package com.osipov.infprot.lab1.entity;

import com.osipov.infprot.lab1.entity.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "information_id")
    private TaskInformation information;

    @ManyToOne
    @JoinColumn(name = "initiator", nullable = false)
    private User initiator;

    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Subtask> subtasks;

    public void addSubtask(Subtask subtask){
        if (subtasks == null)
            subtasks = new HashSet<>();
        subtasks.add(subtask);
    }
}
