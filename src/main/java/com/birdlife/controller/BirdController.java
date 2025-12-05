package com.birdlife.controller;

import com.birdlife.dto.BirdDto;
import com.birdlife.service.BirdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/birds")
@CrossOrigin
public class BirdController {

    private final BirdService service;

    //Search bar functionality
    @GetMapping("/search")
    public List<BirdDto> search(@RequestParam(required = false) String q) {
        return service.search(q);
    }

    //Filter functionality
    @GetMapping("/filter")
    public List<BirdDto> filter(@RequestParam(required = false) String color,
                                @RequestParam(required = false, name = "location") String location) {
        return service.searchAdvanced(null, color, location);
    }

    //Allows retrieval of a specific bird
    @GetMapping("/{id}")
    public BirdDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    //Creates a new bird
    @PostMapping
    public BirdDto create(@Valid @RequestBody BirdDto dto) {
        return service.create(dto);
    }

    //Updates and existing bird
    @PutMapping("/{id}")
    public BirdDto update(@PathVariable("id") Long id, @Valid @RequestBody BirdDto dto) {
        return service.update(id, dto);
    }

    //Deletes existing bird
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
