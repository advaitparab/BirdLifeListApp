package com.birdlife.config;

import com.birdlife.entity.Bird;

import com.birdlife.repo.BirdRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BirdRepository birdRepository;



    public DataInitializer(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        if(birdRepository.count() == 1) { // Only load if DB is empty

            Bird americanGoldfinch = Bird.builder()
                    .commonName("American Goldfinch")
                    .speciesName("Spinus tristis")
                    .color("Bright Yellow with Black Wings")
                    .defaultLocation("Fields, gardens, river edges")
                    .description("A small, vibrant yellow bird that feeds on seeds, often seen in flocks during summer in Ohio.")
                    .images(List.of("AmericanGoldfinch.png"))
                    .build();

            Bird redWingedBlackbird = Bird.builder()
                    .commonName("Red-winged Blackbird")
                    .speciesName("Agelaius phoeniceus")
                    .color("Black with Red Shoulder Patches")
                    .defaultLocation("Marshes, wetlands, farmland")
                    .description("A common wetland bird known for the males’ striking red and yellow shoulder patches.")
                    .images(List.of("RedWingedBlackbird.png"))
                    .build();

            Bird easternBluebird = Bird.builder()
                    .commonName("Eastern Bluebird")
                    .speciesName("Sialia sialis")
                    .color("Blue with Orange-Brown Chest")
                    .defaultLocation("Open fields, orchards, backyards with nest boxes")
                    .description("A small thrush with vivid blue plumage and a warm orange breast, commonly seen perching on fences.")
                    .images(List.of("EasternBluebird.png"))
                    .build();

            Bird mourningDove = Bird.builder()
                    .commonName("Mourning Dove")
                    .speciesName("Zenaida macroura")
                    .color("Grayish Brown")
                    .defaultLocation("Open fields, urban areas, backyards")
                    .description("A slender, soft-colored dove known for its mournful cooing, very common in Ohio.")
                    .images(List.of("MourningDove.png"))
                    .build();

            Bird blackCappedChickadee = Bird.builder()
                    .commonName("Black-capped Chickadee")
                    .speciesName("Poecile atricapillus")
                    .color("Gray, White, Black")
                    .defaultLocation("Deciduous forests, backyard feeders")
                    .description("A small, friendly songbird with a black cap and bib, often visiting feeders and flitting through trees.")
                    .images(List.of("BlackCappedChickadee.png"))
                    .build();

            Bird downyWoodpecker = Bird.builder()
                    .commonName("Downy Woodpecker")
                    .speciesName("Picoides pubescens")
                    .color("Black and White with Red Patch")
                    .defaultLocation("Woodlands, suburban areas, parks")
                    .description("The smallest North American woodpecker, with black-and-white plumage and a small red patch on the back of males’ heads.")
                    .images(List.of("DownyWoodpecker.png"))
                    .build();

            Bird americanRobin = Bird.builder()
                    .commonName("American Robin")
                    .speciesName("Turdus migratorius")
                    .color("Grayish Brown with Orange Chest")
                    .defaultLocation("Lawns, gardens, parks, woodlands")
                    .description("A familiar songbird with a bright orange breast, known for its melodic morning songs and worm-hunting on lawns.")
                    .images(List.of("AmericanRobin.png"))
                    .build();

            Bird houseSparrow = Bird.builder()
                    .commonName("House Sparrow")
                    .speciesName("Passer domesticus")
                    .color("Brown, Black, Gray")
                    .defaultLocation("Urban areas, farms, backyards")
                    .description("A small, sociable bird often found around human habitation, feeding on seeds and scraps.")
                    .images(List.of("HouseSparrow.png"))
                    .build();

            Bird europeanStarling = Bird.builder()
                    .commonName("European Starling")
                    .speciesName("Sturnus vulgaris")
                    .color("Iridescent Black with Spots")
                    .defaultLocation("Cities, farmlands, parks")
                    .description("An invasive but abundant species, known for its iridescent plumage and large murmuration flights in the evening sky.")
                    .images(List.of("EuropeanStarling.png"))
                    .build();

            Bird redBelliedWoodpecker = Bird.builder()
                    .commonName("Red-bellied Woodpecker")
                    .speciesName("Melanerpes carolinus")
                    .color("Black and White Striped Back with Red Head")
                    .defaultLocation("Woodlands, suburban areas, parks")
                    .description("A striking woodpecker with zebra-like back pattern and red cap, often found clinging to tree trunks.")
                    .images(List.of("RedBelliedWoodpecker.png"))
                    .build();

            Bird easternPhoebe = Bird.builder()
                    .commonName("Eastern Phoebe")
                    .speciesName("Sayornis phoebe")
                    .color("Gray-brown with Lighter Belly")
                    .defaultLocation("Near water, bridges, buildings, backyards")
                    .description("A small flycatcher that wags its tail constantly; nests under eaves and bridges.")
                    .images(List.of("EasternPhoebe.png"))
                    .build();

            Bird tuftedTitmouse = Bird.builder()
                    .commonName("Tufted Titmouse")
                    .speciesName("Baeolophus bicolor")
                    .color("Gray with White Belly and Rust Flanks")
                    .defaultLocation("Deciduous forests, backyard feeders")
                    .description("A small, curious songbird with a prominent crest, often joins chickadees at feeders.")
                    .images(List.of("TuftedTitmouse.png"))
                    .build();

            Bird northernFlicker = Bird.builder()
                    .commonName("Northern Flicker")
                    .speciesName("Colaptes auratus")
                    .color("Brown with Black Spots, Yellow or Red Underside")
                    .defaultLocation("Open woods, forest edges, yards")
                    .description("A large woodpecker with a distinctive spotted belly and loud call, often seen on the ground eating ants.")
                    .images(List.of("NorthernFlicker.png"))
                    .build();

            Bird houseFinch = Bird.builder()
                    .commonName("House Finch")
                    .speciesName("Haemorhous mexicanus")
                    .color("Brown with Red Head and Breast")
                    .defaultLocation("Urban areas, parks, backyards")
                    .description("A small, lively finch with a cheerful song; males have bright red coloring on head and chest.")
                    .images(List.of("HouseFinch.png"))
                    .build();


            birdRepository.saveAll(List.of(americanGoldfinch, redWingedBlackbird, easternBluebird, mourningDove,blackCappedChickadee,
                    downyWoodpecker,americanRobin,houseSparrow,europeanStarling,redBelliedWoodpecker,easternPhoebe,tuftedTitmouse,
                    northernFlicker,houseFinch));
            System.out.println("Seeded initial bird data!");
        }
    }
}
