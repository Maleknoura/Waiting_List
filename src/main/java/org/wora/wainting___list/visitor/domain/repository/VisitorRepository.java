package org.wora.wainting___list.visitor.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wora.wainting___list.visitor.domain.entity.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
