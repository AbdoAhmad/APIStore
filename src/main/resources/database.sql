CREATE TABLE Users
(
 userName varchar(100) NOT NULL,
 userEmail varchar(100) DEFAULT NULL,
 userPassword varchar(100) DEFAULT NULL,
 PRIMARY KEY (userEmail)
);

CREATE TABLE NormalUser
(
 normalUserEmail varchar(100) DEFAULT NULL,
 FOREIGN KEY (normalUserEmail) REFERENCES Users(userEmail)
);
CREATE TABLE StoreOwner
(
  StoreOwnerEmail varchar(100) DEFAULT NULL,
 FOREIGN KEY (StoreOwnerEmail) REFERENCES Users(userEmail)
);

CREATE TABLE AdministratorUser
(
AdministratorUserEmail varchar(100) DEFAULT NULL,
FOREIGN kEY (AdministratorUserEmail) REFERENCES Users(userEmail)
);