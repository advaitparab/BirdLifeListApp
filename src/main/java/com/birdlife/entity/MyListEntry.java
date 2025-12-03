package com.birdlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "my_list_entry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyListEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bird_id", referencedColumnName = "birdId")
    private Bird bird;


    private LocalDate dateSeen;

    private String locationSeen;

    @Column(length = 2000)
    private String notes;
}
