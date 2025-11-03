package com.birdlife.controller;

import com.birdlife.dto.BirdDto;
import com.birdlife.service.BirdService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/birds")
@CrossOrigin
public class BirdController {
    private final BirdService service;

    @GetMapping("/search")
    public List<BirdDto> search(@RequestParam(required=false) String q) {
        return service.search(q);
    }

    /**
     * Performs an advanced bird search by optional parameters such as name, color, and location.
     * So I renamed /filter to /search/advanced and removed the null argument
     * @param name optional name filter
     * @param color optional color filter
     * @param location optional location filter
     * @return a list of birds matching the criteria
     */
    @GetMapping("/search/advanced")
    public List<BirdDto> searchAdvanced(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String color,
                                        @RequestParam(required = false) String location) {
        return service.searchAdvanced(name, color, location);
    }
    /**
     * Retrieves a bird by ID.
     *
     * @param id the unique ID of the bird
     * @return ResponseEntity containing the bird data if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            BirdDto bird = service.getById(id);
            return ResponseEntity.ok(bird);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping public BirdDto create(@Valid @RequestBody BirdDto dto){ return service.create(dto); }
    @PutMapping("/{id}") public BirdDto update(@PathVariable Long id, @Valid @RequestBody BirdDto dto){ return service.update(id,dto); }
    /**
     * Deletes a bird by ID.
     *
     * @param id the ID of the bird to delete
     * @return HTTP 204 if successful, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
