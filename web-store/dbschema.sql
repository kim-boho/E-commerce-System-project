CREATE TABLE users (
 email        VARCHAR(100) NOT NULL,
 password     VARCHAR(100),
 fname    VARCHAR(50),
 lname   VARCHAR(50),
 admin    TINYINT(1),
 checkout_count INT,
  PRIMARY KEY(email)
);

CREATE TABLE address (
 adrs_no        INT NOT NULL AUTO_INCREMENT,
 street     VARCHAR(50),
 city     VARCHAR(50),
 province    VARCHAR(30),
 zip   VARCHAR(30),
 phone  VARCHAR(15),
 email  VARCHAR(100) NOT NULL,
  PRIMARY KEY(adrs_no),
  FOREIGN KEY (email) REFERENCES users(email)
);


CREATE TABLE product (
 product_no  INT NOT NULL AUTO_INCREMENT,
 name     VARCHAR(50),
 description    TEXT,
 type   VARCHAR(30),
 brand    VARCHAR(50),
 instock INT,
 price DOUBLE,
 imgsrc VARCHAR(300),
 imgalt VARCHAR(300),
  PRIMARY KEY(product_no)
);

CREATE TABLE visitevent (
  id INT NOT NULL AUTO_INCREMENT,
  ip VARCHAR(20) NOT NULL,
  evendate date NOT NULL,
  eventtype VARCHAR(20) NOT NULL,
  product_no INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE review (
 review_no  INT NOT NULL AUTO_INCREMENT,
 email VARCHAR(100) NOT NULL,
 product_no INT NOT NULL,
 rate INT,
 description    TEXT,
  PRIMARY KEY(review_no),
  FOREIGN KEY (product_no) REFERENCES product(product_no),
  FOREIGN KEY (email) REFERENCES users(email)
);

CREATE TABLE cart (
email  VARCHAR(100) NOT NULL,
product_no  INT NOT NULL,
qnty INT,
  PRIMARY KEY(email,product_no),
  FOREIGN KEY (email) REFERENCES users(email),
  FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE orders (
order_no  INT NOT NULL AUTO_INCREMENT,
date  datetime,
email VARCHAR(100) NOT NULL,
adrs_no INT NOT NULL,
  PRIMARY KEY(order_no),
  FOREIGN KEY (email) REFERENCES users(email),
  FOREIGN KEY (adrs_no) REFERENCES address(adrs_no)
);

CREATE TABLE orderedproducts (
order_no  INT NOT NULL,
product_no INT NOT NULL,
qnty INT,
  PRIMARY KEY(order_no,product_no),
  FOREIGN KEY (order_no) REFERENCES orders(order_no),
  FOREIGN KEY (product_no) REFERENCES product(product_no)
);
