package org.wora.wainting___list.waitingList.domain.dto.response;

import java.util.Date;

public record WaitingListResponseDTO(
        long id,
        Date date,
        String algorithm,
        Integer capacity,
        String mode
) {
}
