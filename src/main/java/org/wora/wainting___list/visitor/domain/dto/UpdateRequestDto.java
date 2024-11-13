package org.wora.wainting___list.visitor.domain.dto;

import org.wora.wainting___list.waitingList.domain.enums.Status;

public record UpdateRequestDto(
        Status status
) {
}
