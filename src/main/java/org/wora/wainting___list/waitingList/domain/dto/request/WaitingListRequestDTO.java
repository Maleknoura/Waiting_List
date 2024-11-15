package org.wora.wainting___list.waitingList.domain.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;

public record WaitingListRequestDTO(
        @NotNull(message = "Date is required") LocalDate date,
        @NotBlank(message = "Algorithm name is required")
        @Size(max = 100, message = "Algorithm name must not exceed 100 characters") String algorithm,
        @NotNull(message = "Capacity is required")
        @Min(value = 1, message = "Capacity must be at least 1")
        @Max(value = 1000, message = "Capacity must not exceed 1000") Integer capacity,
        @NotBlank(message = "Mode is required")
        @Size(max = 50, message = "Mode must not exceed 50 characters") String mode
) {
}
