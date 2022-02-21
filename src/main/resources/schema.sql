DROP TABLE IF EXISTS `mass_tactic`;
CREATE TABLE `mass_tactic`
(
    `id`                bigint(20)                                           NOT NULL AUTO_INCREMENT,
    `type`              varchar(20)                                          NOT NULL,
    `status`            enum ('AVAILABLE','PREPARED','EXECUTING','ARCHIVED') NOT NULL,
    `has_test`          tinyint(1)                                                    DEFAULT NULL,
    `must_test`         tinyint(1)                                           NOT NULL,
    `exec_times`        int(11)                                              NOT NULL,
    `template_id`       bigint(11)                                           NOT NULL,
    `default_sender`    varchar(100)                                                  DEFAULT NULL,
    `default_params`    varchar(1000)                                                 DEFAULT NULL,
    `default_signature` varchar(50)                                                   DEFAULT NULL,
    `default_ext`       varchar(1000)                                                 DEFAULT NULL,
    `source`            varchar(8192)                                        NOT NULL,
    `source_type`       enum ('TEXT','FILE','URL')                           NOT NULL DEFAULT 'TEXT',
    `source_size`       int(11)                                                       DEFAULT NULL,
    `prepared_mass_id`  bigint(11)                                                    DEFAULT NULL,
    `created_at`        datetime                                             NOT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`
(
    `id`                  bigint(20)                                  NOT NULL AUTO_INCREMENT,
    `sender`              varchar(100)                                NOT NULL,
    `target`              varchar(1000)                               NOT NULL,
    `target_user_id`      varchar(64)   DEFAULT NULL,
    `type`                varchar(20)                                 NOT NULL,
    `sp_type`             varchar(20)                                 NOT NULL,
    `sp_account_id`       bigint(20)                                  NOT NULL,
    `title`               varchar(100)                                NOT NULL,
    `content`             varchar(1000)                               NOT NULL,
    `signature`           varchar(50)   DEFAULT NULL,
    `template_id`         bigint(20)    DEFAULT NULL,
    `third_template_code` varchar(50)   DEFAULT NULL,
    `params`              varchar(1000)                               NOT NULL,
    `delivery_id`         varchar(64)   DEFAULT NULL,
    `tag`                 varchar(50)                                 NOT NULL,
    `status`              enum ('NOT_SEND','DELIVERED','SENT','FAIL') NOT NULL,
    `status_remark`       varchar(200)  DEFAULT NULL,
    `mass_id`             bigint(11)    DEFAULT NULL,
    `sub_mass_id`         bigint(20)    DEFAULT NULL,
    `ext`                 varchar(1000) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for message_mass
-- ----------------------------
DROP TABLE IF EXISTS `message_mass`;
CREATE TABLE `message_mass`
(
    `id`            bigint(20)                                                     NOT NULL AUTO_INCREMENT,
    `type`          varchar(20)                                                    NOT NULL,
    `message_type`  varchar(50) DEFAULT NULL,
    `sp_type`       varchar(50) DEFAULT NULL,
    `test`          tinyint(1)                                                     NOT NULL,
    `status`        enum ('CREATING','NOT_DELIVERED','DELIVERING','ALL_DELIVERED') NOT NULL,
    `tactic_id`     bigint(20)  DEFAULT NULL,
    `created_at`    datetime                                                       NOT NULL,
    `size`          int(11)     DEFAULT NULL,
    `success_count` int(11)     DEFAULT NULL,
    `fail_count`    int(11)     DEFAULT NULL,
    `error_count`   int(11)     DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for message_template
-- ----------------------------
DROP TABLE IF EXISTS `message_template`;
CREATE TABLE `message_template`
(
    `id`               bigint(20)    NOT NULL AUTO_INCREMENT,
    `type`             varchar(20)   NOT NULL,
    `placeholder_rule` varchar(50)   NOT NULL,
    `sp_type`          varchar(20)   NOT NULL,
    `sp_account_id`    bigint(20)    NOT NULL,
    `third_code`       varchar(64)   NOT NULL,
    `title`            varchar(200)  NOT NULL,
    `content`          varchar(1000) NOT NULL,
    `tag`              varchar(50)   NOT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for sub_mass
-- ----------------------------
DROP TABLE IF EXISTS `sub_mass`;
CREATE TABLE `sub_mass`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `main_id`     bigint(20) NOT NULL,
    `status`      enum ('INIT','DELIVERING','DELIVERED') DEFAULT NULL,
    `delivery_id` varchar(64)                            DEFAULT NULL,
    `serial_num`  int(11)    NOT NULL,
    `size`        int(11)    NOT NULL,
    `start`       int(11)    NOT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for third_account
-- ----------------------------
DROP TABLE IF EXISTS `third_account`;
CREATE TABLE `third_account`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT,
    `type`         varchar(20)  NOT NULL,
    `sp_type`      varchar(20)  NOT NULL,
    `username`     varchar(100) NOT NULL,
    `app_id`       varchar(50)   DEFAULT NULL,
    `app_secret`   varchar(50)   DEFAULT NULL,
    `access_token` varchar(200)  DEFAULT NULL,
    `remark`       varchar(200)  DEFAULT NULL,
    `ext`          varchar(1000) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
