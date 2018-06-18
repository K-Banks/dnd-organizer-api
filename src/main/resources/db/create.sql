SET MODE PostgreSQL;

--DROP TABLE characters;

CREATE TABLE IF NOT EXISTS characters (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    race VARCHAR,
    classId INTEGER,
    level INTEGER,
    preparedSpells VARCHAR
);

CREATE TABLE IF NOT EXISTS classes (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS spells(
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR,
    school VARCHAR,
    level INTEGER,
    castTime VARCHAR,
    ritual BOOLEAN,
    concentration BOOLEAN,
    duration VARCHAR,
    range VARCHAR,
    verbal BOOLEAN,
    somatic BOOLEAN,
    material VARCHAR
);

CREATE TABLE IF NOT EXISTS classes_spells (
    id int PRIMARY KEY auto_increment,
    classId INTEGER,
    spellId INTEGER
);