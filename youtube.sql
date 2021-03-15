use youtube;
create table movie (
 id int(11) NOT NULL AUTO_INCREMENT,
 name VARCHAR(50) NOT NULL,
 author VARCHAR(50) NOT NULL,
 category VARCHAR(50) NOT NULL,
 url VARCHAR(200) NOT NULL,
PRIMARY KEY (id)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='movie details';
