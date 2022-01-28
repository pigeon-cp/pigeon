ALTER TABLE `message`
    ADD COLUMN sub_mass_id BIGINT NULL;

ALTER TABLE `mass_tactic`
    ADD COLUMN source_size INT NULL AFTER `source_type`;

create table sub_mass
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    main_id    BIGINT NOT NULL,
    status     ENUM (
        'INIT',
        'DELIVERING',
        'DELIVERED'
        ),
    serial_num INT    NOT NULL,
    size       INT    NOT NULL,
    start      INT    NOT NULL
);

ALTER TABLE `message_template`
    ADD COLUMN placeholder_rule VARCHAR(50) AFTER `type`;

ALTER TABLE `message_mass`
    ADD COLUMN type VARCHAR(50) AFTER `type`;
