package com.birdlife.dto;

import java.util.List;

public class BirdDto {
    private Long birdId;
    private String commonName;
    private String speciesName;
    private String color;
    private String defaultLocation;
    private String description;
    private List<String> images;

    public BirdDto() {}

    public BirdDto(Long birdId, String commonName, String speciesName, String color,
                   String defaultLocation, String description, List<String> images) {
        this.birdId = birdId;
        this.commonName = commonName;
        this.speciesName = speciesName;
        this.color = color;
        this.defaultLocation = defaultLocation;
        this.description = description;
        this.images = images;
    }

    public Long getBirdId() { return birdId; }
    public void setBirdId(Long birdId) { this.birdId = birdId; }

    public String getCommonName() { return commonName; }
    public void setCommonName(String commonName) { this.commonName = commonName; }

    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getDefaultLocation() { return defaultLocation; }
    public void setDefaultLocation(String defaultLocation) { this.defaultLocation = defaultLocation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
}
