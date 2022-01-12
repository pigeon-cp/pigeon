CREATE TABLE message
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender        VARCHAR(100)  NOT NULL,
    target        VARCHAR(1000) NOT NULL,
    type          ENUM (
        'MAIL'
        )                       NOT NULL,
    sp_type       ENUM (
        'ALI_CLOUD'
        ),
    sp_account_id BIGINT        NOT NULL,
    title         VARCHAR(100)  NOT NULL,
    content       VARCHAR(1000) NOT NULL,
    template_id   BIGINT        NOT NULL,
    params        VARCHAR(1000) NOT NULL,
    tag           VARCHAR(50)   NOT NULL,
    status        enum (
        'NOT_SEND',
        'DELIVERED',
        'SENT',
        'FAIL'
        )                       not null
);

CREATE TABLE message_template
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    sp_type          ENUM (
        'ALI_CLOUD'
        ),
    sp_account_id    BIGINT        NOT NULL,
    third_code       VARCHAR(64)   NOT NULL,
    title_snapshot   varchar(200)  NOT NULL,
    content_snapshot varchar(1000) NOT NULL,
    tag              VARCHAR(50)   NOT NULL
);

CREATE TABLE third_account
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    sp_type    ENUM (
        'ALI_CLOUD'
        ),
    username   VARCHAR(100) NOT NULL,
    app_id     VARCHAR(50)  NULL,
    app_secret VARCHAR(50)  NULL
);
