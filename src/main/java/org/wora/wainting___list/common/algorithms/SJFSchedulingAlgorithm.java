package org.wora.wainting___list.common.algorithms;

import org.wora.wainting___list.common.SchedulingAlgorithm;
import org.wora.wainting___list.waitingList.domain.entity.Visit;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SJFSchedulingAlgorithm implements SchedulingAlgorithm {

    @Override
    public List<Visit> schedule(WaitingList waitingList) {
        return waitingList.getVisits().stream()
                .sorted(Comparator.comparingLong(visit -> visit.getEstimatedProcessingTime().toMinutes()))
                .collect(Collectors.toList());
    }
}
