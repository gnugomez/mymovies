drop table if exists users;
drop table if exists group_members;

create table users
(
    id                  int          not null auto_increment,
    username            VARCHAR(50) PRIMARY KEY,
    password            VARCHAR(250) NOT NULL,
    user_role           VARCHAR(50)  NOT NULL,
    first_name          VARCHAR(100) NOT NULL,
    last_name           VARCHAR(100) NOT NULL,
    account_enabled     BOOLEAN      NOT NULL DEFAULT true,
    account_expired     BOOLEAN      NOT NULL DEFAULT false,
    account_locked      BOOLEAN      NOT NULL DEFAULT false,
    credentials_expired BOOLEAN      NOT NULL DEFAULT false,
    use_2fa             BOOLEAN      NOT NULL DEFAULT false
);

create table movies_user_specific_data
(
    user_id         int     not null,
    movie_id        int     not null,
    favorite        BOOLEAN NOT NULL DEFAULT false,
    personal_rating int,
    notes           VARCHAR(255),
    primary key (user_id, movie_id)
);