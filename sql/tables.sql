CREATE TABLE Person(
                       name VARCHAR NOT NULL,
                       email VARCHAR NOT NULL,
                       password VARCHAR NOT NULL,
                       role VARCHAR(10),
                       PRIMARY KEY(name)
);

CREATE TABLE Shopper(
                        name VARCHAR(20) NOT NULL,
                        fcoins INT,
                        id_shopper_parts INT,
                        id_shopper_collections INT,
                        PRIMARY KEY(name),
                        FOREIGN KEY(name)
                            REFERENCES Person(name)
);

CREATE TABLE Artist(
                       name VARCHAR(20) NOT NULL,
                       description VARCHAR(50) NOT NULL,
                       id_part INT,
                       id_collection INT,
                       PRIMARY KEY(name),
                       FOREIGN KEY(name)
                           REFERENCES Person(name)
);

CREATE TABLE Part(
                     title VARCHAR(40),
                     description VARCHAR,
                     price INT,
                     id_part INT,
                     PRIMARY KEY(title)
);

CREATE TABLE Collection(
                           title VARCHAR(50),
                           description VARCHAR(50),
                           parts INT,
                           PRIMARY KEY(title)
);