package org.wora.wainting___list.waitingList.infrastructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.wainting___list.waitingList.application.service.WaintingListService;
import org.wora.wainting___list.waitingList.application.service.WaintingService;
import org.wora.wainting___list.waitingList.domain.dto.request.WaitingListRequestDTO;
import org.wora.wainting___list.waitingList.domain.dto.response.WaitingListResponseDTO;
import org.wora.wainting___list.waitingList.domain.entity.Visit;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;
import org.wora.wainting___list.waitingList.domain.repository.WaitingListRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/waiting-lists")
@AllArgsConstructor
public class WaitingListController {

    private final WaintingListService waitingListService;
    private final WaintingService waintingService;
    private final WaitingListRepository waitingListRepository;

    @PostMapping
    public ResponseEntity<WaitingListResponseDTO> createWaitingList(@RequestBody WaitingListRequestDTO requestDto) {
        WaitingListResponseDTO responseDto = waitingListService.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaitingListResponseDTO> getWaitingListById(@PathVariable Long id) {
        WaitingListResponseDTO responseDto = waitingListService.getById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WaitingListResponseDTO>> getAllWaitingLists() {
        List<WaitingListResponseDTO> responseDtoList = waitingListService.getAll();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaitingListResponseDTO> updateWaitingList(
            @PathVariable Long id,
            @RequestBody WaitingListRequestDTO requestDto) {
        WaitingListResponseDTO responseDto = waitingListService.update(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaitingList(@PathVariable Long id) {
        boolean isDeleted = waitingListService.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}/schedule")
    public ResponseEntity<List<Visit>> getScheduledVisits(@PathVariable Long id) {
        List<Visit> scheduledVisits = waintingService.getScheduledVisits(id);
        return ResponseEntity.ok(scheduledVisits);
    }

    @PostMapping("/{id}/visits")
    public ResponseEntity<Visit> addVisit(
            @PathVariable Long id,
            @Valid @RequestBody Visit visit) {
        WaitingList waitingList = waitingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Waiting List not found"));

        visit.setWaitingList(waitingList);

        Visit savedVisit = waintingService.addVisit(visit);
        return new ResponseEntity<>(savedVisit, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics(@PathVariable Long id) {
        Map<String, Object> statistics = waintingService.getStatistics(id);
        return ResponseEntity.ok(statistics);
    }
}

