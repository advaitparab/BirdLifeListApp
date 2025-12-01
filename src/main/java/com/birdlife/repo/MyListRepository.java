package com.birdlife.repo;

import com.birdlife.entity.MyListEntry;
import com.birdlife.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyListRepository extends JpaRepository<MyListEntry, Long> {
    Optional<MyListEntry> findByBird(Bird bird);
}
