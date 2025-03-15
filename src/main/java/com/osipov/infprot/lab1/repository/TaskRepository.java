package com.osipov.infprot.lab1.repository;

import com.osipov.infprot.lab1.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByInitiatorIdAndId(Long initiatorId, Long taskId);

    List<Task> findAllByInitiatorId(Long initiatorId);

}
