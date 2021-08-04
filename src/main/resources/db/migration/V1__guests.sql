create table guests (id bigint not null auto_increment,
                     guest_name varchar(255),
                     guest_phoneNumber varchar(255),
                     medical_condition varchar(255),
                     timeBookers varchar(255),
                     status varchar(255) ,
                      primary key (id));

insert into guests (guest_name, guest_phoneNumber, medical_condition) values (
                                    'Frau Markgraf', '223344','malformation', 'not_paid');
insert into guests (guest_name, guest_phoneNumber, medical_condition) values (
                                    'Herr Zipfer', '123651', 'spinal problem', 'paid');