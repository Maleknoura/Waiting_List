package org.wora.wainting___list.common;

import org.wora.wainting___list.waitingList.domain.entity.Visit;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;

import java.util.List;

public interface SchedulingAlgorithm {
    List<Visit> schedule(WaitingList waitingList);
}
