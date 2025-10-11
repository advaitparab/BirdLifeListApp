package com.birdlife.service.impl;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import com.birdlife.entity.Bird;
import com.birdlife.entity.MyListEntry;
import com.birdlife.entity.User;
import com.birdlife.repo.BirdRepository;
import com.birdlife.repo.MyListRepository;
import com.birdlife.repo.UserRepository;
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
    private final UserRepository userRepo;
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
    public List<MyListEntryDto> getMyList(Long userId) {
        return myRepo.findByUserId(userId).stream()
                .map(MyListServiceImpl::map)
                .collect(Collectors.toList());
    }

    @Override
    public MyListEntryDto addToMyList(Long userId, Long birdId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Bird b = birdRepo.findById(birdId).orElseThrow(() -> new RuntimeException("Bird not found"));

        Optional<MyListEntry> existing = myRepo.findByUserAndBird(u, b);
        if (existing.isPresent()) {
            return map(existing.get());
        }

        MyListEntry e = MyListEntry.builder().user(u).bird(b).build();
        MyListEntry saved = myRepo.save(e);
        return map(saved);
    }

    @Override
    public void removeFromMyList(Long userId, Long birdId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Bird b = birdRepo.findById(birdId).orElseThrow(() -> new RuntimeException("Bird not found"));

        Optional<MyListEntry> entryOpt = myRepo.findByUserAndBird(u, b);
        if (entryOpt.isPresent()) {
            MyListEntry entry = entryOpt.get();
            myRepo.delete(entry);
        }
    }

    @Override
    public MyListEntryDto upsertObservation(Long userId, ObservationUpsertDto p) {
        User u = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Bird b = birdRepo.findById(p.getBirdId()).orElseThrow(() -> new RuntimeException("Bird not found"));

        MyListEntry e = myRepo.findByUserAndBird(u, b)
                .orElseGet(() -> MyListEntry.builder().user(u).bird(b).build());

        e.setDateSeen(p.getDateSeen());
        e.setLocationSeen(p.getLocationSeen());
        e.setNotes(p.getNotes());

        MyListEntry saved = myRepo.save(e);
        return map(saved);
    }
}

