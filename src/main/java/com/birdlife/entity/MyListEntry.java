package com.birdlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MyListEntry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private User user;
    @ManyToOne(optional = false) private Bird bird;

    private LocalDate dateSeen;
    private String locationSeen;
    @Column(length = 2000) private String notes;
}
