package com.birdlife.service.impl;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import com.birdlife.entity.Bird;
import com.birdlife.entity.MyListEntry;
import com.birdlife.repo.BirdRepository;
import com.birdlife.repo.MyListRepository;
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

    private static MyListEntryDto map(MyListEntry e) {
        return new MyListEntryDto(
                e.getId(),
                e.getBird().getBirdId(),
                e.getBird().getCommonName(),
                e.getBird().getSpeciesName(),
                e.getBird().getColor(),
                e.getBird().getDefaultLocation(),
                e.getBird().getDescription(),
                e.getDateSeen(),
                e.getLocationSeen(),
                e.getNotes()
        );
    }

    @Override
    public List<MyListEntryDto> getMyList() {
        return myRepo.findAll().stream()
                .map(MyListServiceImpl::map)
                .collect(Collectors.toList());
    }

    @Override
    public MyListEntryDto addToMyList(Long birdId) {
        Bird b = birdRepo.findById(birdId)
                .orElseThrow(() -> new RuntimeException("Bird not found"));

        Optional<MyListEntry> existing = myRepo.findByBird(b);

        if (existing.isPresent()) {
            return map(existing.get());
        }

        MyListEntry e = MyListEntry.builder()
                .bird(b)
                .build();

        MyListEntry saved = myRepo.save(e);
        return map(saved);
    }

    @Override
    public void removeFromMyList(Long birdId) {
        Bird b = birdRepo.findById(birdId)
                .orElseThrow(() -> new RuntimeException("Bird not found"));

        Optional<MyListEntry> entryOpt = myRepo.findByBird(b);
        entryOpt.ifPresent(myRepo::delete);
    }

    @Override
    public MyListEntryDto upsertObservation(ObservationUpsertDto p) {

        Bird b = birdRepo.findById(p.getBirdId())
                .orElseThrow(() -> new RuntimeException("Bird not found"));

        MyListEntry e = myRepo.findByBird(b)
                .orElseGet(() -> MyListEntry.builder().bird(b).build());

        e.setDateSeen(p.getDateSeen());
        e.setLocationSeen(p.getLocationSeen());
        e.setNotes(p.getNotes());

        MyListEntry saved = myRepo.save(e);
        return map(saved);
    }
}
