package com.birdlife.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservationUpsertDto {
    private Long birdId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSeen;

    private String locationSeen;
    private String notes;
}
