CREATE TABLE comment
(
    id         UUID NOT NULL,
    menssage   VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    status     VARCHAR(255),
    profile_id UUID,
    topic_id   UUID,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE course
(
    id       UUID NOT NULL,
    name     VARCHAR(255),
    category VARCHAR(255),
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE profile
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_profile PRIMARY KEY (id)
);

CREATE TABLE topic
(
    id         UUID NOT NULL,
    title      VARCHAR(255),
    menssage   VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    status     VARCHAR(255),
    profile_id UUID,
    course_id  UUID,
    CONSTRAINT pk_topic PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       UUID NOT NULL,
    name     VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_profiles
(
    profiles_id UUID NOT NULL,
    users_id    UUID NOT NULL
);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES profile (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_TOPIC FOREIGN KEY (topic_id) REFERENCES topic (id);

ALTER TABLE topic
    ADD CONSTRAINT FK_TOPIC_ON_COURSE FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE topic
    ADD CONSTRAINT FK_TOPIC_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES profile (id);

ALTER TABLE users_profiles
    ADD CONSTRAINT fk_usepro_on_profile FOREIGN KEY (profiles_id) REFERENCES profile (id);

ALTER TABLE users_profiles
    ADD CONSTRAINT fk_usepro_on_user FOREIGN KEY (users_id) REFERENCES users (id);