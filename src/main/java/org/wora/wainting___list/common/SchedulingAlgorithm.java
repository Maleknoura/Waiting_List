package org.wora.wainting___list.common;

import org.wora.wainting___list.waitingList.domain.entity.Visit;
import org.wora.wainting___list.waitingList.domain.entity.WaintingList;

import java.util.List;

public interface SchedulingAlgorithm {
    List<Visit> schedule(WaintingList waitingList);
}
