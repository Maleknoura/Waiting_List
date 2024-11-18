package org.wora.wainting___list.common.algorithms;

import org.springframework.stereotype.Component;
import org.wora.wainting___list.common.SchedulingAlgorithm;
import org.wora.wainting___list.waitingList.domain.entity.Visit;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;
import org.wora.wainting___list.waitingList.domain.enums.Status;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriorityAlgorithm implements SchedulingAlgorithm {
    @Override
    public List<Visit> schedule(WaitingList waitingList) {
        return waitingList.getVisits().stream()
                .filter(visit -> visit.getStatus() == Status.PENDING)
                .sorted(Comparator
                        .comparing(Visit::getPriority).reversed()
                        .thenComparing(Visit::getArrivalTime))
                .collect(Collectors.toList());
    }
}
