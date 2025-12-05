package com.birdlife.service;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import java.util.List;

public interface MyListService {
    List<MyListEntryDto> getMyList();
    MyListEntryDto addToMyList(Long birdId);
    void removeFromMyList(Long birdId);
    MyListEntryDto upsertObservation(ObservationUpsertDto payload);
}
