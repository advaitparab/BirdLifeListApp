package com.birdlife.repo;

import com.birdlife.entity.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaypointRepository extends JpaRepository<Waypoint, Long> { }
