package com.birdlife.service.impl;

import com.birdlife.dto.BirdDto;
import com.birdlife.entity.Bird;
import com.birdlife.repo.BirdRepository;
import com.birdlife.service.BirdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BirdServiceImpl implements BirdService {

    private final BirdRepository repo;

    private static BirdDto map(Bird b) {
        return new BirdDto(
                b.getBirdId(),
                b.getCommonName(),
                b.getSpeciesName(),
                b.getColor(),
                b.getDefaultLocation(),
                b.getDescription(),
                b.getImages()
        );
    }

    @Override
    public List<BirdDto> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return repo.findAll().stream()
                    .map(BirdServiceImpl::map)
                    .collect(Collectors.toList());
        }

        return repo.findByCommonNameContainingIgnoreCase(query).stream()
                .map(BirdServiceImpl::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<BirdDto> filter(String color, String location) {
        return repo.search(null, color, location).stream()
                .map(BirdServiceImpl::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<BirdDto> searchAdvanced(String q, String color, String loc) {
        return repo.search(q, color, loc).stream()
                .map(BirdServiceImpl::map)
                .collect(Collectors.toList());
    }

    @Override
    public BirdDto getById(Long id) {
        Bird b = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bird not found"));
        return map(b);
    }

    @Override
    public BirdDto create(BirdDto dto) {
        Bird b = Bird.builder()
                .commonName(dto.getCommonName())
                .speciesName(dto.getSpeciesName())
                .color(dto.getColor())
                .defaultLocation(dto.getDefaultLocation())
                .description(dto.getDescription())
                .images(dto.getImages())
                .build();
        return map(repo.save(b));
    }

    @Override
    public BirdDto update(Long id, BirdDto dto) {
        Bird b = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bird not found"));

        b.setCommonName(dto.getCommonName());
        b.setSpeciesName(dto.getSpeciesName());
        b.setColor(dto.getColor());
        b.setDefaultLocation(dto.getDefaultLocation());
        b.setDescription(dto.getDescription());
        b.setImages(dto.getImages());

        return map(repo.save(b));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
