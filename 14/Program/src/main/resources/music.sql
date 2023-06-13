create table singers
(
    id SERIAL PRIMARY KEY,
    name varchar not null
);

create table albums
(
    id SERIAL PRIMARY KEY,
    name varchar not null,
	singerid integer references singers(id)
);

create table songs
(
    id SERIAL PRIMARY KEY,
    name varchar not null,
	size bigint not null,
	albumid integer references albums(id)
);

create table singers_filtered
(
    id SERIAL PRIMARY KEY,
    name varchar not null
);

create table albums_filtered
(
    id SERIAL PRIMARY KEY,
    name varchar not null,
	singerid integer references singers_filtered(id)
);

create table songs_filtered
(
    id SERIAL PRIMARY KEY,
    name varchar not null,
	size bigint not null,
	albumid integer references albums_filtered(id)
);