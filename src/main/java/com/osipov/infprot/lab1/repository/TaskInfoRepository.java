package com.osipov.infprot.lab1.repository;

import com.osipov.infprot.lab1.entity.TaskInformation;
import com.osipov.infprot.lab1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskInfoRepository extends JpaRepository<TaskInformation, Long> {
    Optional<TaskInformation> findByTaskId(Long taskId);

    @Query("SELECT ti FROM TaskInformation ti WHERE ti.createOn >= CAST(:date1 AS timestamp)")
    List<TaskInformation> findAllCreatedAfter(@Param("date1") LocalDateTime createOn);

}
