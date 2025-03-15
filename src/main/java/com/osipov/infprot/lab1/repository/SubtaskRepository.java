package com.osipov.infprot.lab1.repository;

import com.osipov.infprot.lab1.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    Collection<Subtask> findAllByTaskId(Long taskId);
}
