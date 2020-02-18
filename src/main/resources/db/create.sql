SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS sightings (
  id int PRIMARY KEY auto_increment,
  animalname VARCHAR,
  location VARCHAR,
  rangername VARCHAR
);