CREATE TABLE IF NOT EXISTS Author (
    id_author SERIAL PRIMARY KEY,
    authorName VARCHAR(255) NOT NULL,
    sex CHAR(1) CHECK ( sex IN ('M', 'F'))
    );
INSERT INTO Author (id_author,authorName, sex)
VALUES
    (1,'John Smith', 'M'),
    (2,'Jane Doe', 'F'),
    (3,'Robert Johnson', 'M');

