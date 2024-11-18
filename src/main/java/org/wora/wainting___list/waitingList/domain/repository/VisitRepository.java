package org.wora.wainting___list.waitingList.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.wainting___list.waitingList.domain.entity.Visit;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;
import org.wora.wainting___list.waitingList.domain.enums.Status;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit,Long> {
    Optional<Visit> findByVisitorId(Long visitorId);
    long countByWaitingListAndStatusIn(WaitingList waitingList, List<Status> statuses);
    List<Visit> findByWaitingListAndStatus(WaitingList waitingList, Status status);



}
