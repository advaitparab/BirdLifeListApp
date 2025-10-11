insert into app_user(id, email, display_name) values (1,'demo@birdlife.app','Demo User');

insert into bird (bird_id, common_name, species_name, color, default_location, description)
values
 (1,'Northern Cardinal','Cardinalis cardinalis','red','Ohio','Iconic red songbird.'),
 (2,'American Robin','Turdus migratorius','orange','Ohio','Common backyard thrush.'),
 (3,'Blue Jay','Cyanocitta cristata','blue','Ohio','Loud, crested jay.');

insert into bird_images (bird_id, image_uri) values
 (1,'https://example.com/cardinal.jpg'),
 (2,'https://example.com/robin.jpg'),
 (3,'https://example.com/bluejay.jpg');
