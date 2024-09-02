CREATE TABLE IF NOT EXISTS Content (
  id INTEGER AUTO_INCREMENT,
  title varchar(255) NOT NULL,
  desc text,
  status varchar(20),
  contentType varchar(50) NOT NULL,
  dateCreated TIMESTAMP NOT NULL,
  dateUpdated TIMESTAMP,
  url VARCHAR(255),
  primary key (id)
);

INSERT INTO Content (title, desc, status, contentType, dateCreated)
VALUES ('SPRIN test', 'SPRING test descriptin', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP)