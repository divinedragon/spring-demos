CREATE SCHEMA IF NOT EXISTS sd_oauth;

CREATE TABLE IF NOT EXISTS sd_oauth.account (
    a_id                    UUID                PRIMARY KEY,
    a_hash                  VARCHAR(66)         NOT NULL,
    a_email                 VARCHAR(500)        NOT NULL,
    a_password              VARCHAR(60)         NOT NULL,
    a_verification_key      UUID                NOT NULL,
    a_disabled              BOOLEAN             DEFAULT FALSE NOT NULL,
    a_verified              BOOLEAN             DEFAULT FALSE NOT NULL,
    a_role                  VARCHAR(20)         NOT NULL,
    a_created_at            TIMESTAMP           DEFAULT NOW() NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_email ON sd_oauth.account (a_email);
CREATE INDEX IF NOT EXISTS idx_hash ON sd_oauth.account (a_hash);


INSERT INTO sd_oauth.account(a_id, a_hash, a_email, a_password, a_verification_key, a_disabled, a_verified, a_role) VALUES
('267975d7-9059-4763-b844-fd99902c0799', '', 'administrator@divinedragon.com', '$2a$10$a65XF01UMzCmy6SM.Sz/WOm0htoi/JP8g48xKa2A9wh96ZjGjb2tW', '267975d7-9059-4763-b844-fd99902c0799', FALSE, TRUE, 'ROLE_USER');
