create table users
(
    id       bigserial,
    username varchar(30) not null,
    password varchar(80) not null,
    email    varchar(50) unique,
    score    integer default 0,
    primary key (id)
);

create table role
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references role (id)
);

insert into role (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');


insert into users (username, password, email, score)
values ('user', '$2a$10$zIUuseKyIitYs3lW43ZuzecHHRYg34NQP5JcYR9e1Drm5BCVb3LvW', 'user@gmail.com', 1),
       ('user1', '$2a$10$zIUuseKyIitYs3lW43ZuzecHHRYg34NQP5JcYR9e1Drm5BCVb3LvW', 'user1@gmail.com', 2),
       ('user2', '$2a$10$zIUuseKyIitYs3lW43ZuzecHHRYg34NQP5JcYR9e1Drm5BCVb3LvW', 'user2@gmail.com', 3);

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2);