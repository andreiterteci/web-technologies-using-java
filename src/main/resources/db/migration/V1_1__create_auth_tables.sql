CREATE TABLE role(
id CHARACTER VARYING(255) not null constraint role_pkey primary key,
role CHARACTER VARYING(255) not null
);

INSERT INTO role(id, role) VALUES
('-1', 'ROLE_USER');

CREATE TABLE app_user(
id CHARACTER VARYING(255) not null constraint app_user_pkey primary key,
email CHARACTER VARYING(255) not null,
password CHARACTER VARYING(255) not null,
first_name CHARACTER VARYING(255) not null,
last_name CHARACTER VARYING(255) not null
);

CREATE TABLE user_roles(
user_id CHARACTER VARYING(255),
role_id CHARACTER VARYING(255)
);

ALTER TABLE user_roles
  ADD CONSTRAINT fk_user_roles
    FOREIGN KEY (user_id) REFERENCES app_user (id);

ALTER TABLE user_roles
  ADD CONSTRAINT fk_roles_user
    FOREIGN KEY (role_id) REFERENCES role (id);

CREATE INDEX idx_user_roles_id ON user_roles (user_id);
CREATE INDEX idx_user_roles_code ON user_roles (role_id);