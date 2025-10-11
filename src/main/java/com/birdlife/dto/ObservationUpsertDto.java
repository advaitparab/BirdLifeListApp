package com.birdlife.dto;

import java.time.LocalDate;

public class ObservationUpsertDto {
    private Long birdId;
    private LocalDate dateSeen;
    private String locationSeen;
    private String notes;

    public ObservationUpsertDto() {}

    public ObservationUpsertDto(Long birdId, LocalDate dateSeen, String locationSeen, String notes) {
        this.birdId = birdId;
        this.dateSeen = dateSeen;
        this.locationSeen = locationSeen;
        this.notes = notes;
    }

    public Long getBirdId() {
        return birdId;
    }

    public void setBirdId(Long birdId) {
        this.birdId = birdId;
    }

    public LocalDate getDateSeen() {
        return dateSeen;
    }

    public void setDateSeen(LocalDate dateSeen) {
        this.dateSeen = dateSeen;
    }

    public String getLocationSeen() {
        return locationSeen;
    }

    public void setLocationSeen(String locationSeen) {
        this.locationSeen = locationSeen;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
