-- Create table
CREATE TABLE users (
    id INT KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255)
);

-- Insert data
INSERT INTO users (first_name, last_name, email) VALUES ('Francis', 'Lancis', 'fran.lanc@example.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Bill', 'Dill', 'bill.dill@example.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Mark', 'Spark', 'marky.sparky@example.com');