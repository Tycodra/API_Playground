-- Create table
CREATE TABLE students (
    id INT KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    grade INT);

-- Insert data
INSERT INTO students (first_name, last_name, email, grade) VALUES ('Neal', 'Dover', 'n.dover@student.com', 6);
INSERT INTO students (first_name, last_name, email, grade) VALUES ('Ilene', 'Dover', 'i.dover@student.com', 7);
INSERT INTO students (first_name, last_name, email, grade) VALUES ('Ben', 'Dover', 'b.dover@student.com', 8);


