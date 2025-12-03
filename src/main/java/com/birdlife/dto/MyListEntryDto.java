package com.birdlife.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyListEntryDto {

    private Long entryId;
    private Long birdId;
    private LocalDate dateSeen;
    private String locationSeen;
    private String notes;
}
