package org.wora.wainting___list.waitingList.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.wora.wainting___list.common.exception.SmallIntToByteCouverter;
import org.wora.wainting___list.visitor.domain.entity.Visitor;
import org.wora.wainting___list.waitingList.domain.embeddabls.VisitId;
import org.wora.wainting___list.waitingList.domain.enums.Status;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Visit {
    @EmbeddedId
    private VisitId id;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End date is required")
    private LocalTime endDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private Status status;

    @Convert(converter = SmallIntToByteCouverter.class)
    @NotNull(message = "Priority is required")
    @Min(value = 1, message = "Priority must be at least 1")
    @Max(value = 10, message = "Priority must not exceed 10")
    private Byte priority;

    @NotNull(message = "Estimated processing time is required")
    private Duration estimatedProcessingTime;

    @MapsId("visitorId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @MapsId("waitingListId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waiting_list_id")
    private WaitingList waitingList;
}
