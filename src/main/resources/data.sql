/**
 * CREATE Script for init of DB
 */

insert into books (id, date_created, isbn, book_name, author, categories) values (1, now(), '99921-58-10-7', 'microservice',
'martin fowler', 'IT');

insert into books (id, date_created, isbn, book_name, author, categories) values (2, now(), '9971-5-0210-0', 'design pattern',
'martin fowler', 'IT');