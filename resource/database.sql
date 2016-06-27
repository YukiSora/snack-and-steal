DROP DATABASE IF EXISTS BluebellAdventuresRecord;
CREATE DATABASE BluebellAdventuresRecord;
USE BluebellAdventuresRecord;

CREATE TABLE Records (
    Id INT NOT NULL AUTO_INCREMENT,
    Record INT NOT NULL,
    Date_Time Date NOT NULL,

    PRIMARY KEY (Id)
);
