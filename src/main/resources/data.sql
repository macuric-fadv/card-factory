
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (1, 'Antonio', 'Novak', '56160176514',
        '2024-07-16 09:15:52.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (2, 'Ivana', 'Knežević', '58637851534',
        '2024-07-08 13:25:15.155' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (3, 'Marina', 'Marić', '14131362243',
        '2024-01-03 18:47:52.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (4, 'Marko', 'Kovačević', '22837201637',
        '2024-08-22 15:37:52.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (5, 'Nikola', 'Vuković', '97254419598',
        '2024-05-10 10:05:07.002' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (6, 'Petra', 'Marić', '01647576499',
        '2024-06-12 11:07:07.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (7, 'Luka', 'Babić', '99706225104',
        '2024-07-05 14:10:52.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (8, 'Sara', 'Kovačević', '15154315422',
        '2024-02-11 08:04:04.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (9, 'Petra', 'Tomić', '00165608708',
        '2024-01-28 19:03:03.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (10, 'Luka', 'Vuković', '76451204319',
        '2024-07-14 12:52:27.069' , 'cc_factory_app', null, null);
insert into CLIENT (ID, FIRST_NAME, LAST_NAME, OIB, CREATED_DATE, CREATED_BY, LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (11, 'Karlo', 'Tomić', '25373818573',
        '2024-03-11 11:28:52.069' , 'cc_factory_app', null, null);


insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (1, 'COMPLETED', '2024-03-28 18:47:52.069', 3,
        '2024-03-01 18:47:52.069', 'cc_factory_app',
        '2024-03-28 18:47:52.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (2, 'COMPLETED', '2024-06-12 10:05:07.002', 5,
        '2024-05-10 10:05:07.002', 'cc_factory_app',
        '2024-06-12 10:05:07.002', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (3, 'READY', null, 1,
        '2024-07-16 09:15:52.069', 'cc_factory_app',
        '2024-08-20 09:15:52.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (4, 'REJECTED', null, 2,
        '2024-07-08 13:25:15.155', 'cc_factory_app',
        '2024-07-17 13:25:15.155', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (5, 'APPROVED', null, 4,
        '2024-08-22 15:37:52.069', 'cc_factory_app',
        '2024-08-29 15:37:52.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (6, 'READY', null, 10,
        '2024-07-14 12:52:27.069', 'cc_factory_app',
        '2024-08-19 12:52:27.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (7, 'PENDING', null,6,
        '2024-06-12 11:07:07.069', 'cc_factory_app',
        '2024-07-18 11:07:07.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (8, 'PENDING', null,7,
        '2024-07-05 14:10:52.069', 'cc_factory_app',
        '2024-07-30 14:10:52.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (9, 'REJECTED', null, 8,
        '2024-02-11 08:04:04.069', 'cc_factory_app',
        '2024-02-27 08:04:04.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (10, 'COMPLETED', '2024-04-02 09:28:52.069', 11,
        '2024-03-11 11:28:52.069', 'cc_factory_app',
        '2024-04-02 09:28:52.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (11, 'COMPLETED', '2024-02-19 19:03:03.069', 9,
        '2024-01-28 19:03:03.069', 'cc_factory_app',
        '2024-02-19 19:03:03.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (12, 'READY', null, 4,
        '2024-04-09 18:47:52.069', 'cc_factory_app',
        '2024-05-21 18:47:52.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (13, 'COMPLETED', '2024-05-30 11:01:01.069', 5,
        '2024-05-03 17:01:01.069', 'cc_factory_app',
        '2024-05-30 11:01:01.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (14, 'READY', null, 6,
        '2024-05-03 17:01:01.069', 'cc_factory_app',
        '2024-07-01 17:01:01.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (15, 'PENDING', null, 7,
        '2024-06-29 15:12:12.069', 'cc_factory_app',
        '2024-07-15 15:12:12.069', 'cc_factory_app');
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (16, 'NEW', null, 8,
        '2024-08-30 17:16:16.069', 'cc_factory_app',
        null, null);
insert into CARD_REQUEST (ID, STATUS, COMPLETED_DATE, CLIENT_ID, CREATED_DATE, CREATED_BY,
                                 LAST_MODIFIED_DATE, LAST_MODIFIED_BY)
values (17, 'NEW', null, 10,
        '2024-08-27 09:13:13.069', 'cc_factory_app',
        null, null);