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

    @Column(name = "date_seen")
    private LocalDate dateSeen;

    @Column(name = "location_seen")
    private String locationSeen;


    @Column(name = "notes", length = 3000)
    private String notes;
}
