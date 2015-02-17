# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint not null,
  name                      varchar(255),
  mail                      varchar(255),
  password                  varchar(255),
  constraint pk_account primary key (id))
;

create table answer_column (
  id                        bigint not null,
  account_id                bigint,
  question_id               bigint,
  answer_sheet_id           bigint,
  selected_option_item_id   bigint,
  answer_date               timestamp,
  constraint pk_answer_column primary key (id))
;

create table answer_history (
  id                        bigint not null,
  account_id                bigint,
  answer_column_id          bigint,
  constraint pk_answer_history primary key (id))
;

create table answer_sheet (
  id                        bigint not null,
  operation_date            timestamp,
  account_id                bigint,
  is_finished               boolean,
  score                     integer,
  current_index             integer,
  constraint pk_answer_sheet primary key (id))
;

create table category (
  id                        bigint not null,
  name                      varchar(255),
  attr_name                 varchar(255),
  constraint pk_category primary key (id))
;

create table option_item (
  id                        bigint not null,
  sentence                  varchar(255),
  is_answer                 boolean,
  has_image                 boolean,
  question_id               bigint,
  constraint pk_option_item primary key (id))
;

create table question (
  id                        bigint not null,
  category_id               bigint,
  year                      integer,
  season_id                 bigint,
  no                        integer,
  sentence                  varchar(255),
  has_image                 boolean,
  tag_id                    varchar(255),
  constraint pk_question primary key (id))
;

create table question_sheet (
  id                        bigint,
  create_date               timestamp)
;

create table season (
  id                        bigint not null,
  name                      varchar(255),
  attr_name                 varchar(255),
  constraint pk_season primary key (id))
;

create table tag (
  id                        varchar(255) not null,
  name                      varchar(255),
  parent_id                 varchar(255),
  constraint pk_tag primary key (id))
;

create sequence account_seq;

create sequence answer_column_seq;

create sequence answer_history_seq;

create sequence answer_sheet_seq;

create sequence category_seq;

create sequence option_item_seq;

create sequence question_seq;

create sequence season_seq;

create sequence tag_seq;

alter table answer_column add constraint fk_answer_column_account_1 foreign key (account_id) references account (id) on delete restrict on update restrict;
create index ix_answer_column_account_1 on answer_column (account_id);
alter table answer_column add constraint fk_answer_column_question_2 foreign key (question_id) references question (id) on delete restrict on update restrict;
create index ix_answer_column_question_2 on answer_column (question_id);
alter table answer_column add constraint fk_answer_column_answerSheet_3 foreign key (answer_sheet_id) references answer_sheet (id) on delete restrict on update restrict;
create index ix_answer_column_answerSheet_3 on answer_column (answer_sheet_id);
alter table answer_column add constraint fk_answer_column_selectedOptio_4 foreign key (selected_option_item_id) references option_item (id) on delete restrict on update restrict;
create index ix_answer_column_selectedOptio_4 on answer_column (selected_option_item_id);
alter table answer_history add constraint fk_answer_history_account_5 foreign key (account_id) references account (id) on delete restrict on update restrict;
create index ix_answer_history_account_5 on answer_history (account_id);
alter table answer_history add constraint fk_answer_history_answerColumn_6 foreign key (answer_column_id) references answer_column (id) on delete restrict on update restrict;
create index ix_answer_history_answerColumn_6 on answer_history (answer_column_id);
alter table answer_sheet add constraint fk_answer_sheet_account_7 foreign key (account_id) references account (id) on delete restrict on update restrict;
create index ix_answer_sheet_account_7 on answer_sheet (account_id);
alter table option_item add constraint fk_option_item_question_8 foreign key (question_id) references question (id) on delete restrict on update restrict;
create index ix_option_item_question_8 on option_item (question_id);
alter table question add constraint fk_question_category_9 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_question_category_9 on question (category_id);
alter table question add constraint fk_question_season_10 foreign key (season_id) references season (id) on delete restrict on update restrict;
create index ix_question_season_10 on question (season_id);
alter table question add constraint fk_question_tag_11 foreign key (tag_id) references tag (id) on delete restrict on update restrict;
create index ix_question_tag_11 on question (tag_id);
alter table tag add constraint fk_tag_parent_12 foreign key (parent_id) references tag (id) on delete restrict on update restrict;
create index ix_tag_parent_12 on tag (parent_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists answer_column;

drop table if exists answer_history;

drop table if exists answer_sheet;

drop table if exists category;

drop table if exists option_item;

drop table if exists question;

drop table if exists question_sheet;

drop table if exists season;

drop table if exists tag;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists answer_column_seq;

drop sequence if exists answer_history_seq;

drop sequence if exists answer_sheet_seq;

drop sequence if exists category_seq;

drop sequence if exists option_item_seq;

drop sequence if exists question_seq;

drop sequence if exists season_seq;

drop sequence if exists tag_seq;

