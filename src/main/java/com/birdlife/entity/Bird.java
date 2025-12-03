package com.birdlife.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Entity @Table(name = "bird") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Bird {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long birdId;

    @NotBlank private String commonName;
    @NotBlank private String speciesName;
    @NotBlank private String color;
    private String defaultLocation;
    @NotBlank private String description;
    @Column(length = 2000) private String notes;

    @ElementCollection
    @CollectionTable(name = "bird_images", joinColumns = @JoinColumn(name = "bird_id"))
    @Column(name = "image_uri")
    private List<String> images;
}
