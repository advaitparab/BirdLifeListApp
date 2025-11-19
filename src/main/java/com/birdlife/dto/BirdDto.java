package com.birdlife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BirdDto {
    private Long birdId;
    private String commonName;
    private String speciesName;
    private String color;
    private String defaultLocation;
    private String description;
    private String notes;
    private List<String> images;
}
