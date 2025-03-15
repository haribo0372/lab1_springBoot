package com.osipov.infprot.lab1.controller;

import com.osipov.infprot.lab1.dto.subtask.RequestNewSubtask;
import com.osipov.infprot.lab1.dto.subtask.RequestUpdateSubtask;
import com.osipov.infprot.lab1.dto.subtask.SubtaskDto;
import com.osipov.infprot.lab1.service.subtask.SubtaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/subtasks")
@RequiredArgsConstructor
public class SubtaskController {
    private final SubtaskService subtaskService;

    @GetMapping
    public Collection<SubtaskDto> findAllByTaskId(@RequestHeader(name = "X-UserId") Long initiatorId,
                                                  @RequestParam(name = "taskId") Long taskId){
        return subtaskService.getAllByTaskId(initiatorId, taskId);
    }

    @GetMapping("/{subtaskId}")
    public SubtaskDto findById(@RequestHeader(name = "X-UserId") Long initiatorId, @PathVariable Long subtaskId){
        return subtaskService.getById(initiatorId, subtaskId);
    }

    @DeleteMapping("/{subtaskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestHeader(name = "X-UserId") Long initiatorId, @PathVariable Long subtaskId){
        subtaskService.delete(initiatorId, subtaskId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubtaskDto create(@RequestHeader(name = "X-UserId") Long initiatorId,
                             @RequestBody @Valid RequestNewSubtask requestNewSubtask){
        return subtaskService.create(initiatorId, requestNewSubtask);
    }

    @PutMapping
    public SubtaskDto update(@RequestHeader(name = "X-UserId") Long initiatorId,
                             @RequestBody @Valid RequestUpdateSubtask requestUpdateSubtask){
        return subtaskService.update(initiatorId, requestUpdateSubtask);
    }
}
