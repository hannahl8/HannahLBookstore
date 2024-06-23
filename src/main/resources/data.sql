USE HannahLBookstoreDB;

DELETE FROM book;
ALTER TABLE book AUTO_INCREMENT = 1001;

DELETE FROM category;
ALTER TABLE category AUTO_INCREMENT = 1001;

INSERT INTO `category` (`name`) VALUES ('Wine'),('Tea'),('Coffee'),('Beer'),('Bestsellers'),('Fiction'),('Non-Fiction'),('Award Winners'),('Sci-Fi'),('Fantasy'),('Horror'),('Romance'),('Historical'),('Educational'),('Biographical');

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('A Game of Thrones', 'George R. R. Martin', 'Everything''s better with some wine in the belly.', 1999, 0, TRUE, TRUE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('A Good Year', 'Peter Mayle', 'The wine, a rich, dark burgundy, was poured into glasses, and they all raised their glasses in a toast.', 499, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Secret of Santa Vittoria', 'Robert Crichton', 'They were about to lose a million bottles of wine to the Germans.', 999, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 'In the main hall a bar with a real brass rail was set up, and stocked with gins and liquors and with cordials so long forgotten that most of his female guests were too young to know one from another.', 1999, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Harry watched Hagrid getting redder and redder in the face as he called for more wine, finally kissing Professor McGonagall on the cheek, who, to Harry''s amazement, giggled and blushed, her top hat lop-sided.', 1999, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Winemaker''s Wife', 'Kristin Harmel', 'She sipped her wine and thought back to the harvest of 1940, the last year she had been here.', 1499, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Sideways', 'Rex Pickett', 'We had both ordered the lamb, and I went with a Rhône wine, a Châteauneuf-du-Pape.', 499, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Sun Also Rises', 'Ernest Hemingway', 'We lunched upstairs at Botin’s. It is one of the best restaurants in the world. We had roast young suckling pig and drank rioja alta.', 999, 0, TRUE, FALSE, 1001);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Duke & I', 'Julia Quinn', 'Violet Bridgerton loved tea. She loved the ritual of it, the serenity it brought, the way a good hot cup could warm her from the inside out.', 1999, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Jane Eyre', 'Charlotte Brontë', 'She rang the bell and ordered tea. The visit to Hay consumed some time, as it lay at a distance from the house. We returned late in the afternoon. We found the dinner already on the table; they were waiting for us.', 1999, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Sense and Sensibility', 'Jane Austen', 'When the tea-things were removed, Lady Middleton asked Marianne to sing. Marianne immediately began.', 1999, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('North and South', 'Elizabeth Gaskell', 'Mr. Hale and she got into some conversation about a village school, and Mr. Hale''s difficulties in managing it, till tea came in, and Mr. Bell reminded them that all this time Margaret had had no food since morning.', 1999, 0, TRUE, FALSE, 1002);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The girl with the Dragon Tattoo', 'Stieg Larsson', 'Coffee was the only reason she could survive all-nighters. The stronger, the better.', 1999, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Catcher in the Rye', 'J.D. Salinger', 'We went in this bar where the band was playing and ordered a couple of Scotch and sodas, all of a sudden, this girl got up on the stage and sang some kind of a song while she was drinking a cup of coffee.', 1999, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('One Hundred Years of Solitude', 'Gabriel García Márquez', 'On the sixth day of unrelieved insomnia, they were about to have breakfast, which Úrsula had brought to the table without realizing that she had made it a second time. A person is always better off with a good cup of hot coffee than he is in bed, for they are both only a temporary escape.', 1999, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Norwegian Wood', 'Haruki Murakami', 'After a little while, Midori brought us coffee and cake. I took a sip of the coffee, and it was so hot it felt like it was melting my tongue. Midori was looking at me with that faintly puzzled expression of hers.', 1999, 0, TRUE, FALSE, 1003);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Hobbit', 'J.R.R. Tolkien', 'So Thorin went on: ''We don''t want any more washing, all that we had was quite enough; and we also don''t want any more baking or boiling. We shall sit here and drink beer.', 1499, 0, FALSE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Of Mice and Men', 'John Steinbeck', 'George looked around at the little square table. ''I guess we gotta drink a hell of a lot of beer before we get an inch of the profits.'' He glanced over at Lennie, who was staring at the table, his huge hand half-covering his face.', 1999, 0, FALSE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Slaughterhouse-Five', 'Kurt Vonnegut', 'An Englishman who had never been to war before said he liked it very much, and the whiskey and the beer were excellent. He wished there were more of it. Billy sat at a large table and ate an egg sandwich and drank from a bottle of beer.', 1999, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Cannery Row', 'John Steinbeck', 'Mack and the boys came in then, just a little drunk. They had been in the Palace Flophouse and Grill. Mack had a quart of beer in his hand, and he drank out of it and passed it around, and each one took a swallow.', 1999, 0, TRUE, FALSE, 1004);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Da Vinci Code', 'Dan Brown', '', 1999, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Catcher in the Rye', 'J.D. Salinger', '', 1999, 0, FALSE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Immortal Life of Henrietta Lacks', 'Rebecca Skloot', '', 1999, 0, FALSE, FALSE, 1007);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Nightingale', 'Kristin Hannah', '', 1999, 0, FALSE, FALSE, 1008);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Dune', 'Frank Herbert', '', 1999, 0, FALSE, FALSE, 1009);