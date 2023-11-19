CREATE TABLE IF NOT EXISTS Subscribers (
     visitorId SERIAL PRIMARY KEY,
     visitorName VARCHAR(255) NOT NULL,
     reference VARCHAR(200)

    );
INSERT INTO Subscribers (visitorName, reference)
VALUES
    ('Alice Johnson', 'AB123'),
    ('Bob Smith', 'CD456'),
    ('Eva Davis', 'EF789');


