package org.wora.wainting___list.visitor.application;

import org.wora.wainting___list.visitor.domain.dto.UpdateRequestDto;

public interface VisitorService {
    public void updateStatus(UpdateRequestDto updateRequestDto, long visitorId);
}
