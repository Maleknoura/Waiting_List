package org.wora.wainting___list.visitor.application.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wora.wainting___list.visitor.application.VisitorService;
import org.wora.wainting___list.visitor.domain.dto.UpdateRequestDto;
import org.wora.wainting___list.visitor.domain.entity.Visitor;
import org.wora.wainting___list.visitor.domain.repository.VisitorRepository;
import org.wora.wainting___list.waitingList.domain.repository.VisitRepository;
import org.wora.wainting___list.waitingList.domain.entity.Visit;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;
    private final VisitRepository visitRepository;

    public void updateStatus(UpdateRequestDto updateRequestDto, long visitorId) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        Visit visit = visitRepository.findByVisitorId(visitorId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found for this visitor"));

        visit.setStatus(updateRequestDto.status());

        visitRepository.save(visit);
    }
}
