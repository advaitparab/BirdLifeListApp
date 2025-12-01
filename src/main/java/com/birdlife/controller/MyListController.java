package com.birdlife.controller;

import com.birdlife.dto.MyListEntryDto;
import com.birdlife.dto.ObservationUpsertDto;
import com.birdlife.service.impl.MyListService;
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
    public MyListEntryDto add(@PathVariable Long birdId) {
        return service.addToMyList(birdId);
    }

    // DELETE /api/mylist/remove/{birdId}
    @DeleteMapping("/remove/{birdId}")
    public ResponseEntity<Void> remove(@PathVariable Long birdId) {
        service.removeFromMyList(birdId);
        return ResponseEntity.noContent().build();
    }

    // PATCH /api/mylist/observation
    @PatchMapping("/observation")
    public MyListEntryDto upsertObservation(
            @Valid @RequestBody ObservationUpsertDto payload
    ) {
        return service.upsertObservation(payload);
    }
}
