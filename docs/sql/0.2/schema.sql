ALTER TABLE `message`
    ADD COLUMN sub_mass_id BIGINT NULL;
ALTER TABLE `message`
    ADD COLUMN signature VARCHAR(50) NULL AFTER `content`;
ALTER TABLE `message`
    ADD COLUMN third_template_code VARCHAR(50) NULL AFTER `template_id`;
ALTER TABLE `message`
    ADD COLUMN delivery_id VARCHAR(64) NULL AFTER `params`;
ALTER TABLE `message`
    ADD COLUMN ext VARCHAR(1000) NULL AFTER `sub_mass_id`;

ALTER TABLE `mass_tactic`
    ADD COLUMN source_size INT NULL AFTER `source_type`;
ALTER TABLE `mass_tactic`
    ADD COLUMN default_signature VARCHAR(50) NULL AFTER `default_params`;
ALTER TABLE `mass_tactic`
    ADD COLUMN default_ext VARCHAR(1000) NULL AFTER `default_signature`;

create table sub_mass
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    main_id     BIGINT      NOT NULL,
    status      ENUM (
        'INIT',
        'DELIVERING',
        'DELIVERED'
        ),
    delivery_id VARCHAR(64) NULL,
    serial_num  INT         NOT NULL,
    size        INT         NOT NULL,
    start       INT         NOT NULL
);

ALTER TABLE `message_template`
    ADD COLUMN placeholder_rule VARCHAR(50) AFTER `type`;

ALTER TABLE `message_mass`
    ADD COLUMN type VARCHAR(50) AFTER id;
ALTER TABLE `message_mass`
    ADD COLUMN message_type VARCHAR(50) AFTER `type`;
ALTER TABLE `message_mass`
    ADD COLUMN sp_type VARCHAR(50) AFTER `message_type`;
