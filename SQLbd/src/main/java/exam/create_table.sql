
CREATE TABLE meetings (
    name VARCHAR(50) NOT NULL UNIQUE,
    id serial NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE users (
                        id serial NOT NULL,
                        name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE participation_status (
                                      id serial NOT NULL,
                                      name VARCHAR(40) NOT NULL UNIQUE,
                                      PRIMARY KEY (id)
);
CREATE TABLE user_meeting (
                                id serial NOT NULL,
                                user_id INT NOT NULL,
                                meeting_id INT NOT NULL,
                                participation_status_id INT NOT NULL,
                                PRIMARY KEY (id)
    );
