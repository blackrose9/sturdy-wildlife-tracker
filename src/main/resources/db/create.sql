SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS sightings (
  id int PRIMARY KEY auto_increment,
  animalName VARCHAR,
  location VARCHAR,
  rangerName VARCHAR
);