create table UserConnection (userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(255) not null,
    secret varchar(255),
    refreshToken varchar(255),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);

create table users (
  id serial primary key
);

alter table users add column email varchar(255);

create table campaigns (
  id serial primary key,
  title varchar(255),
  description varchar(255)
);

create table segments (
  id serial primary key,
  remoteId int not null
);

create table campaignSegments (
  campaignId int not null,
  segmentId int not null,
  primary key (campaignId, segmentId)
);

create table donations (
  id serial primary key,
  amount int not null,
  userId int not null,
  campaignId int not null,
  remoteId varchar(255) not null
);

alter table users add column admin boolean default false;