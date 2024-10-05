
-- create sequence bm_id_seq start with 1 increment by 50;
DROP TABLE IF EXISTS bookmarks;
create table bookmarks (
                           id bigint auto_increment not null,
                           title varchar(255) not null,
                           url varchar(255) not null,
                           author varchar(255) not null,
                           created_at timestamp,
                           primary key (id)
);