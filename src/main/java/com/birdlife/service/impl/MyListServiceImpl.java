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

    private static MyListEntryDto map(MyListEntry e) {
        return MyListEntryDto.builder()
                .entryId(e.getEntryId())
                .birdId(e.getBird().getBirdId())
                .dateSeen(e.getDateSeen())
                .locationSeen(e.getLocationSeen())
                .notes(e.getNotes())
                .build();
    }

    @Override
    public List<MyListEntryDto> getMyList() {
        return myRepo.findAll()
                .stream()
                .map(MyListServiceImpl::map)
                .toList();
    }

    @Override
    public MyListEntryDto addToMyList(Long birdId) {

        Bird b = birdRepo.findById(birdId)
                .orElseThrow(() -> new RuntimeException("Bird not found"));


        Optional<MyListEntry> existing = myRepo.findByBird(b);
        if (existing.isPresent()) {
            return map(existing.get());
        }

        MyListEntry entry = MyListEntry.builder()
                .bird(b)
                .build();

        return map(myRepo.save(entry));
    }

    @Override
    public void removeFromMyList(Long birdId) {
        Bird b = birdRepo.findById(birdId)
                .orElseThrow(() -> new RuntimeException("Bird not found"));

        myRepo.findByBird(b).ifPresent(myRepo::delete);
    }

    @Override
    public MyListEntryDto upsertObservation(ObservationUpsertDto p) {

        Bird b = birdRepo.findById(p.getBirdId())
                .orElseThrow(() -> new RuntimeException("Bird not found"));

        // Your rule: find entry for bird or create one
        MyListEntry entry = myRepo.findByBird(b)
                .orElseGet(() -> MyListEntry.builder().bird(b).build());

        entry.setDateSeen(p.getDateSeen());
        entry.setLocationSeen(p.getLocationSeen());
        entry.setNotes(p.getNotes());

        return map(myRepo.save(entry));
    }


}
