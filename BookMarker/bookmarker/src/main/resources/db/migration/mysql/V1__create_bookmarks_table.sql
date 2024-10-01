-- create sequence bm_id_seq start with 1 increment by 50
-- create table bookmarks (
-- --                         id bigint not null,  (change this to)
--                            id bigint default nextval('bm_id_seq') not null,
--                            title varchar(255) not null,
--                            url varchar(255),
--                            author varchar(255) not null,
--                            created_at timestamp,
--                            primary key (id)
-- )

-- Create sequence for generating IDs
create sequence if not exists bm_id_seq start with 1 increment by 50;

-- Create table with ID column that uses the sequence
create table if not exists bookmarks (
    id bigint default nextval('bm_id_seq') not null,
    title varchar(255) not null,
    url varchar(255),
    author varchar(255) not null,
    created_at timestamp,
    primary key (id)
);