CREATE TABLE IF NOT EXISTS purchase (
    id SERIAL PRIMARY KEY,
    product varchar(64) NOT NULL,
    price BIGINT NOT NULL
);