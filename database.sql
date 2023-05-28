-- creating roles table
create table roles
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

-- creating users table
create table users
(
    id         int auto_increment
        primary key,
    firstname  varchar(50)                         not null,
    lastname   varchar(50)                         not null,
    email      varchar(50)                         not null,
    username   varchar(30)                         not null,
    password   varchar(100)                        null,
    role_id    int       default 3                 not null,
    image      varchar(255)                        null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp default CURRENT_TIMESTAMP not null,
    constraint users_username_uindex
        unique (username),
    constraint users_roles_id_fk
        foreign key (role_id) references roles (id)
            on update cascade
);


-- inserting initial test role records
insert into roles (name)
values ('admin'),
       ('employee'),
       ('client');

-- inserting initial test user records
insert into users (firstname, lastname, email, username, password, role_id, image, created_at, updated_at)
values ('Joe', 'Doe', 'joe.doe@gmail.com', 'joe', 123, 1, 'storage/avatars/0-02-05-05c271785fd6166f6cf10c1e5fbe6a0582c14772bbdfc4fb6ef81e12e4131275_8369f590.jpg', '2023-03-18 22:07:03',
        '2023-03-18 22:07:03'),
       ('Rocco', 'Simpson', 'rocco.simpson@mail.com', 'rocco', 123, 2, 'storage/avatars/images.png',
        '2023-03-20 20:41:59', '2023-03-20 20:41:59'),
       ('Susanne', 'Gray', 'susanne.gray@mail.com', 'susanne', 123, 2, 'storage/avatars/images.png',
        '2023-04-02 14:28:48', '2023-04-02 14:28:48'),
       ('Kelly', 'Richardson', 'kelly.richardson@mail.com', 'kelly', 123, 2, 'storage/avatars/images.png',
        '2023-04-02 14:31:41', '2023-04-02 14:31:41'),
       ('Jessie', 'Murphy', 'jessie.murphy@mail.com', 'jessie', 123, 3, 'storage/avatars/images.png',
        '2023-04-02 14:31:41', '2023-04-02 14:31:41'),
       ('Esme', 'Gordon', 'esme.gordon@mail.com', 'esme', 123, 3, 'storage/avatars/0-02-05-05c271785fd6166f6cf10c1e5fbe6a0582c14772bbdfc4fb6ef81e12e4131275_8369f590.jpg', '2023-04-02 14:31:41',
        '2023-04-02 14:31:41'),
       ('Molly', 'Parker', 'molly.parker@mail.com', 'molly', 123, 3, 'storage/avatars/images.png',
        '2023-04-02 14:31:41', '2023-04-02 14:31:41'),
       ('Yasmin', 'Campbell', 'yasmin.campbell@mail.com', 'yasmin', 123, 3, 'storage/avatars/images.png',
        '2023-04-02 14:31:41', '2023-04-02 14:31:41'),
       ('Sienna', 'O\'Gallagher', 'sienna.o.gallagher@mail.com', 'sienna', 123, 3, 'storage/avatars/images.png',
        '2023-04-02 14:31:41', '2023-04-02 14:31:41'),
       ('Natalie', 'Ruiz', 'natalie.ruiz@mail.com', 'natalie', 123, 3, 'storage/avatars/images.png',
        '2023-04-02 14:31:41', '2023-04-02 14:31:41'),
       ('Ashton', 'Crowder', 'ashton.crowder@mail.com', 'ashton', 123, 2, 'storage/avatars/0-02-05-05c271785fd6166f6cf10c1e5fbe6a0582c14772bbdfc4fb6ef81e12e4131275_8369f590.jpg',
        '2023-04-12 16:46:28', '2023-04-12 16:46:28'),
       ('Davaa', 'Kakalina', 'davaa.kakalina@mail.com', 'davaa', 123, 2, null, '2023-04-13 00:39:42',
        '2023-04-13 00:39:42'),
       ('Cliff', 'Joaquín', 'cliff.joaquin@mail.com', 'cliff', 123, 3, null, '2023-04-13 00:40:56',
        '2023-04-13 00:40:56');