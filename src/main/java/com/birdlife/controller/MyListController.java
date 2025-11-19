package com.birdlife.controller;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import com.birdlife.service.impl.MyListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/mylist")
@CrossOrigin
public class MyListController {
    private final MyListService service;

    @GetMapping("/{userId}")
    public List<MyListEntryDto> myList(@PathVariable Long userId) {
        return service.getMyList(userId);
    }

    @PostMapping("/{userId}/add/{birdId}")
    public MyListEntryDto add(@PathVariable Long userId, @PathVariable Long birdId) {
        return service.addToMyList(userId, birdId);
    }

    @DeleteMapping("/{userId}/remove/{birdId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long birdId) {
        service.removeFromMyList(userId, birdId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/observation")
    public MyListEntryDto upsertObservation(@PathVariable Long userId,
                                            @Valid @RequestBody ObservationUpsertDto payload) {
        return service.upsertObservation(userId, payload);
    }
}
