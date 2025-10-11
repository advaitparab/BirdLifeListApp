package com.birdlife.repo;

import com.birdlife.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BirdRepository extends JpaRepository<Bird, Long> {
    List<Bird> findByCommonNameContainingIgnoreCase(String q);

    @Query("select b from Bird b " +
            "where (:q is null or lower(b.commonName) like lower(concat('%', :q, '%'))) " +
            "and (:color is null or lower(b.color) = lower(:color)) " +
            "and (:loc is null or lower(b.defaultLocation) = lower(:loc))")

    List<Bird> search(String q, String color, String loc);
}
