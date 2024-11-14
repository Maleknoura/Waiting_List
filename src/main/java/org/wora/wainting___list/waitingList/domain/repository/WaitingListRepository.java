package org.wora.wainting___list.waitingList.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.wainting___list.waitingList.domain.entity.WaitingList;

public interface WaitingListRepository extends JpaRepository<WaitingList,Long> {
}
