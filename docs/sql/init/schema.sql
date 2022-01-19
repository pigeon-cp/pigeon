CREATE TABLE message
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender         VARCHAR(100)  NOT NULL,
    target         VARCHAR(1000) NOT NULL,
    target_user_id VARCHAR(64)   NULL,
    type           VARCHAR(20)   NOT NULL,
    sp_type        VARCHAR(20)   NOT NULL,
    sp_account_id  BIGINT        NOT NULL,
    title          VARCHAR(100)  NOT NULL,
    content        VARCHAR(1000) NOT NULL,
    template_id    BIGINT        NULL,
    params         VARCHAR(1000) NOT NULL,
    tag            VARCHAR(50)   NOT NULL,
    status         enum (
        'NOT_SEND',
        'DELIVERED',
        'SENT',
        'FAIL'
        )                        NOT NULL,
    status_remark  VARCHAR(200)   NULL
);

CREATE TABLE message_template
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    type          VARCHAR(20)   NOT NULL,
    sp_type       VARCHAR(20)   NOT NULL,
    sp_account_id BIGINT        NOT NULL,
    third_code    VARCHAR(64)   NOT NULL,
    title         varchar(200)  NOT NULL,
    content       varchar(1000) NOT NULL,
    tag           VARCHAR(50)   NOT NULL
);

CREATE TABLE third_account
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    sp_type      VARCHAR(20)  NOT NULL,
    username     VARCHAR(100) NOT NULL,
    app_id       VARCHAR(50)  NULL,
    app_secret   VARCHAR(50)  NULL,
    access_token VARCHAR(200) NULL,
    remark       VARCHAR(200) NULL
);
