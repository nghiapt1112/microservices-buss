CREATE TABLE hibernate_sequence(
next_val BIGINT(20)
);

INSERT INTO hibernate_sequence (next_val) VALUE (1);

CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) ,
  product_code VARCHAR(255) ,
  weight INT,
  is_delete BIT,
  modified_at DATETIME,
  created_at DATETIME,
  PRIMARY KEY (id)
);
