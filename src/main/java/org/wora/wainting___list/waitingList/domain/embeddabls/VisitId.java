package org.wora.wainting___list.waitingList.domain.embeddabls;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitId implements Serializable {


    private static final long serialVersionUID = 1L;

    @Column(name = "visitor_id", nullable = false)
    private Long visitorId;

    @Column(name = "waiting_list_id", nullable = false)
    private Long waitingListId;



}
