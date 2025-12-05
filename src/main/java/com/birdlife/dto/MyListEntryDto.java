package com.birdlife.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyListEntryDto {

    private Long entryId;

    private Long birdId;
    private String commonName;
    private String speciesName;
    private String color;
    private String defaultLocation;
    private String description;

    private LocalDate dateSeen;
    private String locationSeen;
    private String notes;
}
