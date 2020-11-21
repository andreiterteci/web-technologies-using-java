CREATE TABLE exercise
(
    id       CHARACTER VARYING(255) not null
        constraint exercise_pkey primary key,
    name CHARACTER VARYING(255),
    exercise_type CHARACTER VARYING(255) not null,
    duration BIGINT not null,
    fitness_id CHARACTER VARYING(255) not null
);

CREATE TABLE fitness
(
    id       CHARACTER VARYING(255) not null
        constraint fitness_pkey primary key,
    duration BIGINT not null,
    health_data_id CHARACTER VARYING(255) not null
);

ALTER TABLE exercise
    ADD CONSTRAINT fk_exercise_fitness
        FOREIGN KEY (fitness_id) REFERENCES fitness (id);

CREATE TABLE meal
(
    id       CHARACTER VARYING(255) not null
        constraint meal_pkey primary key,
    calories BIGINT                 not null,
    names    TEXT,
    health_data_id CHARACTER VARYING(255) not null
);

CREATE TABLE health_data
(
    id   CHARACTER VARYING(255) not null
        constraint data_pkey primary key,
    date TIMESTAMP              not null
);

ALTER TABLE meal
    ADD CONSTRAINT fk_meal_data
        FOREIGN KEY (health_data_id) REFERENCES health_data (id);

ALTER TABLE fitness
    ADD CONSTRAINT fk_fitness_data
        FOREIGN KEY (health_data_id) REFERENCES health_data (id);