select customer0_.id as id1_0_0_, customer0_.firstname as firstnam2_0_0_, customer0_.lastname as lastname3_0_0_ from customer customer0_ where customer0_.id=1CREATE TABLE customer(
    id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
);
