CREATE SCHEMA IF NOT EXISTS sd_oauth;

CREATE TABLE IF NOT EXISTS sd_oauth.dogs (
    d_id                    UUID                PRIMARY KEY,
    d_name                  VARCHAR(30)         NOT NULL,
    d_breed                 VARCHAR(50)         NOT NULL,
    d_created_at            TIMESTAMP           DEFAULT NOW() NOT NULL
);

INSERT INTO sd_oauth.dogs(d_id, d_name, d_breed) VALUES
('267975d7-9059-4763-b844-fd99902c0799', 'Alex', 'akita'),
('edcd0c12-9696-11e8-9eb6-529269fb1459', 'Tiger', 'boxer'),
('edcd0ff0-9696-11e8-9eb6-529269fb1459', 'Sheru', 'doberman'),
('edcd1270-9696-11e8-9eb6-529269fb1459', 'Zoey', 'golden retreiver'),
('edcd14fa-9696-11e8-9eb6-529269fb1459', 'Lila', 'husky');
