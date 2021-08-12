create table time_bookers (id bigint not null auto_increment,
                           start_time date,
                           end_time date,
                           status varchar(255),
                           guest_id bigint not null,
                           primary key (id),
                           foreign key (guest_id) references guests(id));