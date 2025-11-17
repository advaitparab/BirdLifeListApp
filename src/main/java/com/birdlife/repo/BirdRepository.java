package com.birdlife.repo;

import com.birdlife.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BirdRepository extends JpaRepository<Bird, Long> {

    List<Bird> findByCommonNameContainingIgnoreCase(String q);

    @Query("SELECT b FROM Bird b " +
            "WHERE (:q IS NULL OR LOWER(b.commonName) LIKE LOWER(CONCAT('%', :q, '%'))) " +
            "AND (:color IS NULL OR LOWER(b.color) = LOWER(:color)) " +
            "AND (:loc IS NULL OR LOWER(b.defaultLocation) = LOWER(:loc))")
    List<Bird> searchAdvanced(@Param("q") String q,
                              @Param("color") String color,
                              @Param("loc") String loc);

    @Query("SELECT DISTINCT b.color FROM Bird b")
    List<String> findDistinctColors();

    @Query("SELECT DISTINCT b.defaultLocation FROM Bird b")
    List<String> findDistinctDefaultLocations();
}

