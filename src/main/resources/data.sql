
/*  Adresser - Entitet: Address */
INSERT INTO address (city, country, postal_code, street)
VALUES ('Staden', 'Landet', 'Koden', 'Gatan'),
       ('Stadens stad', 'Land i land', '666', 'Gatan');

/*  Blogginlägg - Entitet: Post  */
INSERT INTO post (title, author, blog_text)
VALUES ('Bathory', 'LG', 'Bathory var ett svenskt blackmetal-band från Stockholm som existerade mellan 1983 och 2004. Den huvudsakliga medlemmen var Thomas Forsberg (Quorthon). Bathory tillhörde den "första vågen" av black metal.'),
       ('Entombed', 'Post', 'Entombed är ett svenskt death metal-band som bildades ur banden Morbid och Nihilist 1989. Debutalbumet Left Hand Path gavs ut 1990.'),
       ('Dark Funeral', 'Ihsahn', 'Dark Funeral är ett svenskt black metal-band från Stockholm, bildat 1993 av gitarristerna Lord Ahriman och Blackmoon. '),
       ('Grave', 'Black', 'Grave är ett death metal-band från Sverige, som bildades i Visby på Gotland år 1986.'),
       ('Emperor', 'Doja', 'Emperor is a Norwegian black metal band formed in 1991.');

/*  Användare - Entitet: Member */
INSERT INTO member (first_name, last_name, email, phone, member_type, address_id)
VALUES  ('LG', 'Petrov', 'lg@entombed.se', '019191991', 'Standard', 1),
        ('Post', 'Malone', 'posty@malony.se', '019191991', 'Standard', 1),
        ('Ihsahn', 'Emp', 'Emperor@emp.se', '019191991', 'Enhanced', 1),
        ('Black', 'Sabbath', 'wizard@sweetleaf.se', '019191991', 'Standard', 1),
        ('Doja', 'Cat', 'doja@cat.se', '019191991', 'Premium', 2);


