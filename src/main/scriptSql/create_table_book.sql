CREATE TABLE IF NOT EXISTS Book (
    id SERIAL PRIMARY KEY,
    bookName VARCHAR(255) NOT NULL,
    author_id INT,
    topic VARCHAR(200) CHECK (topic IN ('COMEDY', 'ROMANCE', 'OTHER')) DEFAULT 'OTHER',
    pageNumbers INTEGER,
    releaseDate DATE,
    FOREIGN KEY (author_id) REFERENCES Author(id_author)
    );
INSERT INTO Book (bookName, author_id, topic, pageNumbers, releaseDate)
VALUES
    ('Harry Potter and the Sorcerer''s Stone', 1, ROMABCE, 300, '2001-06-26'),
    ('The Great Gatsby', 2, 'COMEDY', 180, '1925-04-10'),
    ('Atomic Habits', 3, DEFAULT, 500, '1995-12-20');
