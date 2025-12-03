package com.birdlife.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservationUpsertDto {
    private Long birdId;
    private LocalDate dateSeen;
    private String locationSeen;
    private String notes;
}
