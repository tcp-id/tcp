CREATE TABLE usser (
    userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role varchar(10),
    enabled boolean DEFAULT true
  );

CREATE TABLE anime (
   animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
   name text,
   description text,
    type text,
    year int,
   imageurl text);

CREATE TABLE author (
      authorid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
      name text,
      imageurl text);

CREATE TABLE anime_author (
animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
authorid uuid REFERENCES author(authorid) ON DELETE CASCADE,
PRIMARY KEY (animeid, authorid));
)

INSERT INTO anime(name, description, type, year, imageurl) VALUES
  ('One Piece','Pirates infinity series', 'TV dairy',1998, 'movie1.jpg'),
  ('Konosuba','Sex, Gags, Iseakai','TV', 2010, 'movie2.jpg'),
  ('Mushishi','Folklore japanesse','TV', 2020, 'movie3.jpg'),
  ('Dororo','Mutilated Sacred Tradition', 'TV', 2000, 'movie4.jpg');


CREATE TABLE file (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype TEXT,
    data bytea);

-- afegim un usuari de prova
CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usser (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));