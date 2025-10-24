-- Sample user for testing
INSERT INTO app_user(id, email, display_name) VALUES (1,'demo@birdlife.app','Demo User');

-- Ohio bird species
INSERT INTO bird (bird_id, common_name, species_name, color, default_location, description)
VALUES
(1,'Northern Cardinal','Cardinalis cardinalis','Red','Ohio','A bright red bird with a distinctive crest. Males are vibrant red, females are pale brown with red tinges.'),
(2,'American Robin','Turdus migratorius','Red-Brown','Ohio','A large songbird with a gray-brown back and brick-red breast. Common in yards and parks.'),
(3,'Blue Jay','Cyanocitta cristata','Blue','Ohio','A bold, intelligent bird with bright blue plumage, black necklace, and prominent crest.'),
(4,'American Goldfinch','Spinus tristis','Yellow','Ohio','A small bright yellow bird with black wings and cap. Ohio state bird.'),
(5,'Black-capped Chickadee','Poecile atricapillus','Black-White-Gray','Ohio','A small, energetic bird with a black cap and bib, white cheeks, and gray back.'),
(6,'Red-tailed Hawk','Buteo jamaicensis','Brown-Red','Ohio','A large hawk with a distinctive red tail. Common in open areas and along highways.'),
(7,'Mourning Dove','Zenaida macroura','Gray-Brown','Ohio','A graceful dove with a long tail. Named for its mournful cooing call.'),
(8,'Downy Woodpecker','Dryobates pubescens','Black-White','Ohio','A small woodpecker with black and white plumage. Males have a red patch on the back of the head.'),
(9,'Red-winged Blackbird','Agelaius phoeniceus','Black-Red','Ohio','Males are black with distinctive red and yellow shoulder patches. Common in wetlands.'),
(10,'House Sparrow','Passer domesticus','Brown-Gray','Ohio','A common small bird often found near human habitation. Males have gray heads and black bibs.');

-- Sample bird images (using placeholder URLs - replace with real images later)
INSERT INTO bird_images (bird_id, image_url) VALUES
(1,'https://example.com/cardinal.jpg'),
(2,'https://example.com/robin.jpg'),
(3,'https://example.com/bluejay.jpg'),
(4,'https://example.com/goldfinch.jpg'),
(5,'https://example.com/chickadee.jpg'),
(6,'https://example.com/redtailedhawk.jpg'),
(7,'https://example.com/mourningdove.jpg'),
(8,'https://example.com/downywoodpecker.jpg'),
(9,'https://example.com/redwingedblackbird.jpg'),
(10,'https://example.com/housesparrow.jpg');
