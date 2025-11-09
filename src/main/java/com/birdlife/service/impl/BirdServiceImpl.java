package com.birdlife.service;

import com.birdlife.dto.BirdDto;
import com.birdlife.entity.Bird;
import com.birdlife.repo.BirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BirdServiceImpl implements BirdService {

    private final BirdRepository birdRepository;

    @Override
    public List<BirdDto> search(String query) {
        return birdRepository.findByCommonNameContainingIgnoreCase(query)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BirdDto> filter(String color, String location) {
        return searchAdvanced(null, color, location);
    }

    @Override
    public List<BirdDto> searchAdvanced(String q, String color, String loc) {
        return birdRepository.searchAdvanced(q, color, loc)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BirdDto getById(Long id) {
        return birdRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Bird not found: " + id));
    }

    @Override
    public BirdDto create(BirdDto dto) {
        Bird bird = toEntity(dto);
        return toDto(birdRepository.save(bird));
    }

    @Override
    public BirdDto update(Long id, BirdDto dto) {
        Bird bird = birdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bird not found: " + id));

        bird.setCommonName(dto.getCommonName());
        bird.setSpeciesName(dto.getSpeciesName());
        bird.setColor(dto.getColor());
        bird.setDefaultLocation(dto.getDefaultLocation());
        bird.setDescription(dto.getDescription());

        return toDto(birdRepository.save(bird));
    }

    @Override
    public void delete(Long id) {
        birdRepository.deleteById(id);
    }

    public List<String> getAllColors() {
        return birdRepository.findDistinctColors();
    }

    public List<String> getAllLocations() {
        return birdRepository.findDistinctDefaultLocations();
    }

    // DTO <-> Entity mapping
    private BirdDto toDto(Bird bird) {
        return BirdDto.builder()
                .birdId(bird.getBirdId())
                .commonName(bird.getCommonName())
                .speciesName(bird.getSpeciesName())
                .color(bird.getColor())
                .defaultLocation(bird.getDefaultLocation())
                .description(bird.getDescription())
                .build();
    }

    private Bird toEntity(BirdDto dto) {
        return Bird.builder()
                .birdId(dto.getBirdId())
                .commonName(dto.getCommonName())
                .speciesName(dto.getSpeciesName())
                .color(dto.getColor())
                .defaultLocation(dto.getDefaultLocation())
                .description(dto.getDescription())
                .build();
    }
}
