drop constant if exists fk_client_id;
drop table  if exists CREDIT_CARD_REQUEST;
drop index if exists idx_oib;
drop table if exists CLIENT;

create table CLIENT
(
    ID                  bigint primary key auto_increment not null,
    FIRST_NAME          varchar(255) not null,
    LAST_NAME           varchar(255) not null,
    OIB                 varchar(255) not null,
    CREATED_DATE        timestamp(6) not null,
    CREATED_BY          varchar(255) not null,
    LAST_MODIFIED_DATE  timestamp(6),
    LAST_MODIFIED_BY    varchar(255)
);

create index idx_oib ON CLIENT(OIB);

create table CREDIT_CARD_REQUEST
(
    ID                  bigint primary key auto_increment not null,
    STATUS              enum('APPROVED', 'COMPLETED', 'NEW', 'PENDING', 'READY', 'REJECTED') default 'NEW',
    COMPLETED_DATE      timestamp(6),
    CLIENT_ID           bigint not null,
    CREATED_DATE        timestamp(6) not null,
    CREATED_BY          varchar(255) not null,
    LAST_MODIFIED_DATE  timestamp(6),
    LAST_MODIFIED_BY    varchar(255),
    constraint fk_client_id foreign key (CLIENT_ID) references CLIENT
);