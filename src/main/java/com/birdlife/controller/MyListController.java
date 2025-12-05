package com.birdlife.controller;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import com.birdlife.service.MyListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mylist")
@CrossOrigin
public class MyListController {

    private final MyListService service;

    // GET /api/mylist
    @GetMapping
    public List<MyListEntryDto> myList() {
        return service.getMyList();
    }

    // POST /api/mylist/add/{birdId}
    @PostMapping("/add/{birdId}")
    public MyListEntryDto add(@PathVariable("birdId") Long birdId) {
        return service.addToMyList(birdId);
    }

    // POST /api/mylist/remove/{entryId}  (delete specific sighting)
    @PostMapping("/remove/{entryId}")
    public ResponseEntity<Void> remove(@PathVariable("entryId") Long entryId) {
        service.removeFromMyList(entryId);
        return ResponseEntity.noContent().build();
    }

    // PATCH /api/mylist/observation  (body contains birdId)
    @PatchMapping("/observation")
    public MyListEntryDto upsertObservation(
            @Valid @RequestBody ObservationUpsertDto payload) {

        return service.upsertObservation(payload);
    }

    // POST /api/mylist/{birdId}/observation (birdId in URL, not JSON)
    @PostMapping("/{birdId}/observation")
    public MyListEntryDto upsertObservationForBird(
            @PathVariable("birdId") Long birdId,
            @Valid @RequestBody ObservationUpsertDto payload) {

        payload.setBirdId(birdId);
        return service.upsertObservation(payload);
    }
}
