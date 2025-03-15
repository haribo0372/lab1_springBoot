package com.osipov.infprot.lab1.service.subtask;

import com.osipov.infprot.lab1.dto.subtask.RequestNewSubtask;
import com.osipov.infprot.lab1.dto.subtask.RequestUpdateSubtask;
import com.osipov.infprot.lab1.dto.subtask.SubtaskDto;

import java.util.Collection;

public interface SubtaskService {
    SubtaskDto create(Long initiatorId, RequestNewSubtask newSubtask);

    Collection<SubtaskDto> getAllByTaskId(Long initiatorId, Long taskId);

    SubtaskDto getById(Long initiatorId, Long subtaskId);

    SubtaskDto update(Long initiatorId, RequestUpdateSubtask updateSubtask);

    void delete(Long initiatorId, Long subtaskId);
}
