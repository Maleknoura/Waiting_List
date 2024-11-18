package org.wora.wainting___list.waitingList.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.wainting___list.common.algorithms.FifoAlgorithm;
import org.wora.wainting___list.common.algorithms.PriorityAlgorithm;
import org.wora.wainting___list.common.algorithms.ShortestJobFirstAlgorithm;
import org.wora.wainting___list.waitingList.domain.entity.Visit;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;
import org.wora.wainting___list.waitingList.domain.enums.Status;
import org.wora.wainting___list.waitingList.domain.repository.VisitRepository;
import org.wora.wainting___list.waitingList.domain.repository.WaitingListRepository;

import java.time.Duration;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class WaintingService {
    private final WaitingListRepository waitingListRepository;
    private final VisitRepository visitRepository;
    private final FifoAlgorithm fifoAlgorithm;
    private final PriorityAlgorithm priorityAlgorithm;
    private final ShortestJobFirstAlgorithm sjfAlgorithm;

    @Transactional
    public List<Visit> getScheduledVisits(Long waitingListId) {
        WaitingList waitingList = waitingListRepository.findById(waitingListId)
                .orElseThrow(() -> new RuntimeException("Waiting List not found"));

        return switch (waitingList.getAlgorithm()) {
            case FIFO -> fifoAlgorithm.schedule(waitingList);
            case PRIORITY -> priorityAlgorithm.schedule(waitingList);
            case SHORTEST_JOB_FIRST -> sjfAlgorithm.schedule(waitingList);
            default -> throw new IllegalStateException("Unknown algorithm type: " + waitingList.getAlgorithm());
        };
    }

    @Transactional
    public Visit addVisit(Visit visit) {
        WaitingList waitingList = waitingListRepository.findById(visit.getWaitingList().getId())
                .orElseThrow(() -> new RuntimeException("Waiting List not found"));

        validateVisit(visit, waitingList);

        visit.setStatus(Status.PENDING);
        return visitRepository.save(visit);
    }

    private void validateVisit(Visit visit, WaitingList waitingList) {
        long activeVisits = visitRepository.countByWaitingListAndStatusIn(
                waitingList,
                List.of(Status.PENDING, Status.DONE)
        );

        if (activeVisits >= waitingList.getCapacity()) {
            throw new IllegalStateException("Waiting list is at maximum capacity");
        }


    }

    public Map<String, Object> getStatistics(Long waitingListId) {
        WaitingList waitingList = waitingListRepository.findById(waitingListId)
                .orElseThrow(() -> new RuntimeException("Waiting List not found"));

        List<Visit> completedVisits = visitRepository.findByWaitingListAndStatus(
                waitingList,
                Status.DONE
        );

        Duration avgWaitTime = calculateAverageWaitTime(completedVisits);
        Duration avgProcessTime = calculateAverageProcessingTime(completedVisits);
        long totalVisits = completedVisits.size();

        return Map.of(
                "averageWaitTime", avgWaitTime.toMinutes(),
                "averageProcessingTime", avgProcessTime.toMinutes(),
                "totalCompletedVisits", totalVisits,
                "algorithm", waitingList.getAlgorithm()
        );
    }

    private Duration calculateAverageWaitTime(List<Visit> visits) {
        if (visits.isEmpty()) return Duration.ZERO;

        long totalMinutes = visits.stream()
                .mapToLong(visit -> Duration.between(visit.getArrivalTime(), visit.getStartTime()).toMinutes())
                .sum();

        return Duration.ofMinutes(totalMinutes / visits.size());
    }

    private Duration calculateAverageProcessingTime(List<Visit> visits) {
        if (visits.isEmpty()) return Duration.ZERO;

        long totalMinutes = visits.stream()
                .mapToLong(visit -> Duration.between(visit.getStartTime(), visit.getEndDate()).toMinutes())
                .sum();

        return Duration.ofMinutes(totalMinutes / visits.size());
    }
}

