package com.birdlife.service;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import java.util.List;

public interface MyListService {
    List<MyListEntryDto> getMyList(Long userId);
    MyListEntryDto addToMyList(Long userId, Long birdId);
    void removeFromMyList(Long userId, Long birdId);
    MyListEntryDto upsertObservation(Long userId, ObservationUpsertDto payload);
}
