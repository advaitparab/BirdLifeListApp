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

    @GetMapping("/search")
    public List<BirdDto> search(@RequestParam(required = false) String q) {
        return service.search(q);
    }

    @GetMapping("/filter")
    public List<BirdDto> filter(@RequestParam(required = false) String color,
                                @RequestParam(required = false, name = "location") String location) {
        return service.searchAdvanced(null, color, location);
    }

    @GetMapping("/{id}")
    public BirdDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public BirdDto create(@Valid @RequestBody BirdDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public BirdDto update(@PathVariable Long id, @Valid @RequestBody BirdDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
