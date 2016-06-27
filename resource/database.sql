DROP DATABASE IF EXISTS BluebellAdventuresRecord;
CREATE DATABASE BluebellAdventuresRecord;
USE BluebellAdventuresRecord;

CREATE TABLE Records (
    Id INT NOT NULL AUTO_INCREMENT,
    Score INT NOT NULL,
    Date_Time DATETIME NOT NULL,

    PRIMARY KEY (Id)
);
