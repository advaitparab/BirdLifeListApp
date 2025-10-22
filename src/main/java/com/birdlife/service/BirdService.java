package com.birdlife.service;

import com.birdlife.dto.BirdDto;
import com.birdlife.entity.Bird;

import java.util.List;

public interface BirdService {
    List<BirdDto> search(String query);
    List<BirdDto> filter(String color, String location);
    List<BirdDto> searchAdvanced(String q, String color, String loc);

    BirdDto getById(Long id);
    BirdDto create(BirdDto dto);
    BirdDto update(Long id, BirdDto dto);
    void delete(Long id);


}
