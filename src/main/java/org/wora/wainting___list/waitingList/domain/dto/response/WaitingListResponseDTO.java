package org.wora.wainting___list.waitingList.domain.dto.response;

import org.wora.wainting___list.waitingList.domain.enums.Algorithm;

import java.time.LocalDate;
import java.util.Date;

public record WaitingListResponseDTO(
        long id,
        LocalDate date,
        Algorithm algorithm,
        Integer capacity,
        String mode
) {

}
