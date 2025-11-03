package com.birdlife.dto;

import java.time.LocalDate;

@author
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

    
    public MyListEntryDto(ListEntryDto listentrydto) {
    }
    
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

    @return commonName
    
    public String getCommonName() { return commonName; }
    public void setCommonName(String commonName) { this.commonName = commonName; }

    @return speciesName
    
    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }

    @return color
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    @return defaultLocation

    public String getDefaultLocation() { return defaultLocation; }
    public void setDefaultLocation(String defaultLocation) { this.defaultLocation = defaultLocation; }

    @return description
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @return dateSeen
    
    public LocalDate getDateSeen() { return dateSeen; }
    public void setDateSeen(LocalDate dateSeen) { this.dateSeen = dateSeen; }

    @return locationSeen
    
    public String getLocationSeen() { return locationSeen; }
    public void setLocationSeen(String locationSeen) { this.locationSeen = locationSeen; }

    @return notes
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}

