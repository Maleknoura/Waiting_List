package org.wora.wainting___list.waitingList.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.wainting___list.waitingList.domain.entity.Visit;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit,Long> {
    Optional<Visit> findByVisitorId(Long visitorId);

}
