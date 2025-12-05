package com.birdlife.service.impl;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import com.birdlife.entity.Bird;
import com.birdlife.entity.MyListEntry;
import com.birdlife.repo.BirdRepository;
import com.birdlife.repo.MyListRepository;
import com.birdlife.service.MyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyListServiceImpl implements MyListService {

    private final MyListRepository myRepo;
    private final BirdRepository birdRepo;

    private MyListEntryDto toDto(MyListEntry entry) {
        return MyListEntryDto.builder()
                .entryId(entry.getEntryId())
                .birdId(entry.getBird().getBirdId())
                .commonName(entry.getBird().getCommonName())
                .speciesName(entry.getBird().getSpeciesName())
                .color(entry.getBird().getColor())
                .defaultLocation(entry.getBird().getDefaultLocation())
                .dateSeen(entry.getDateSeen())
                .locationSeen(entry.getLocationSeen())
                .notes(entry.getNotes())
                .build();
    }

    @Override
    public List<MyListEntryDto> getMyList() {
        return myRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MyListEntryDto addToMyList(Long birdId) {
        Bird bird = birdRepo.findById(birdId)
                .orElseThrow(() -> new RuntimeException("Bird not found: " + birdId));

        // If we already have an entry for this bird, just return it
        Optional<MyListEntry> existing = myRepo.findByBird(bird);
        if (existing.isPresent()) {
            return toDto(existing.get());
        }

        // New list entry for this bird
        MyListEntry entry = MyListEntry.builder()
                .bird(bird)
                .build();

        return toDto(myRepo.save(entry));
    }

    @Override
    public void removeFromMyList(Long birdId) {
        Bird bird = birdRepo.findById(birdId)
                .orElseThrow(() -> new RuntimeException("Bird not found: " + birdId));

        myRepo.findByBird(bird).ifPresent(myRepo::delete);
    }

    @Override
    public MyListEntryDto upsertObservation(ObservationUpsertDto payload) {
        if (payload.getBirdId() == null) {
            throw new IllegalArgumentException("birdId is required");
        }

        Bird bird = birdRepo.findById(payload.getBirdId())
                .orElseThrow(() ->
                        new RuntimeException("Bird not found: " + payload.getBirdId()));

        // Find existing entry for this bird or create one
        MyListEntry entry = myRepo.findByBird(bird)
                .orElseGet(() -> MyListEntry.builder()
                        .bird(bird)   // sets BIRD_ID FK
                        .build());

        // Update observation fields
        entry.setDateSeen(payload.getDateSeen());
        entry.setLocationSeen(payload.getLocationSeen());
        entry.setNotes(payload.getNotes());

        return toDto(myRepo.save(entry));
    }
}
