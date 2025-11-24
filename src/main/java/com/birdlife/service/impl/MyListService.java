package com.birdlife.service.impl;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import java.util.List;

/**
 * Service for managing a user's personal bird list and observations.
 */
public interface MyListService {

    /**
     * Retrieves all birds on the given user's list.
     * @param userId the ID of the user
     * @return list of bird entries for that user
     */
    List<MyListEntryDto> getMyList(Long userId);

    /**
     * Adds a bird to the user's list if not already present.
     * @param userId ID of the user
     * @param birdId ID of the bird to add
     * @return the created list entry
     */
    MyListEntryDto addToMyList(Long userId, Long birdId);

    /**
     * Removes a bird from the user's list.
     * @param userId ID of the user
     * @param birdId ID of the bird to remove
     */
    void removeFromMyList(Long userId, Long birdId);

    /**
     * Adds or updates an observation for a specific bird.
     * @param userId ID of the user
     * @param payload observation data to upsert
     * @return the updated observation
     */
    MyListEntryDto upsertObservation(Long userId, ObservationUpsertDto payload);
}
