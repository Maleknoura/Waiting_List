package org.wora.wainting___list.waitingList.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wora.wainting___list.waitingList.domain.enums.Algorithm;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "algorithm")
    private Algorithm algorithm;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 1000, message = "Capacity must not exceed 1000")
    private Integer capacity;

    @NotBlank(message = "Mode is required")
    @Size(max = 50, message = "Mode must not exceed 50 characters")
    private String mode;
    @OneToMany(mappedBy = "waitingList")
    private List<Visit> visits;

    public WaitingList(String s) {

    }
}

