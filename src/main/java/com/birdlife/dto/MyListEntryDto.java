package com.birdlife.dto;

import java.time.LocalDate;

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

    public MyListEntryDto() {}

    public MyListEntryDto(Long entryId, Long birdId, String commonName, String speciesName,
                          String color, String defaultLocation, String description,
                          LocalDate dateSeen, String locationSeen, String notes) {
        this.entryId = entryId;
        this.birdId = birdId;
        this.commonName = commonName;
        this.speciesName = speciesName;
        this.color = color;
        this.defaultLocation = defaultLocation;
        this.description = description;
        this.dateSeen = dateSeen;
        this.locationSeen = locationSeen;
        this.notes = notes;
    }

    public Long getEntryId() { return entryId; }
    public void setEntryId(Long entryId) { this.entryId = entryId; }

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

    public LocalDate getDateSeen() { return dateSeen; }
    public void setDateSeen(LocalDate dateSeen) { this.dateSeen = dateSeen; }

    public String getLocationSeen() { return locationSeen; }
    public void setLocationSeen(String locationSeen) { this.locationSeen = locationSeen; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
