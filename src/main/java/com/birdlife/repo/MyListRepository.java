package com.birdlife.repo;

import com.birdlife.entity.MyListEntry;
import com.birdlife.entity.User;
import com.birdlife.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MyListRepository extends JpaRepository<MyListEntry, Long> {
    List<MyListEntry> findByUserId(Long userId);
    Optional<MyListEntry> findByUserAndBird(User user, Bird bird);
}
