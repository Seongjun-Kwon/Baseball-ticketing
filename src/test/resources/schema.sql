CREATE TABLE IF NOT EXISTS `matches`
(
    `id`               bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `start_time`       timestamp   NOT NULL,
    `stadium_id`       bigint      NOT NULL,
    `home_team_name`   varchar(15) NOT NULL,
    `away_team_name`   varchar(15) NOT NULL,
    `left_seats_count` int         NOT NULL
);

CREATE TABLE IF NOT EXISTS `stadiums`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`        varchar(20) NOT NULL,
    `seats_count` int         NOT NULL
);

CREATE TABLE IF NOT EXISTS `users`
(
    `id`               bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`             varchar(5)  NOT NULL,
    `email`            varchar(50) NOT NULL,
    `phone_number`     varchar(15) NOT NULL,
    `created_at`       timestamp   NOT NULL,
    `last_modified_at` timestamp   NOT NULL
);

CREATE TABLE IF NOT EXISTS `seats`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `section`     varchar(30) NOT NULL,
    `seat_number` int         NOT NULL,
    `price`       int         NOT NULL,
    `stadium_id`  bigint      NOT NULL
);

CREATE TABLE IF NOT EXISTS `tickets`
(
    `id`               bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status`           varchar(10) NOT NULL,
    `created_at`       timestamp   NOT NULL,
    `last_modified_at` timestamp   NOT NULL,
    `match_id`         bigint      NOT NULL,
    `seat_id`          bigint      NOT NULL,
    `user_id`          bigint      NOT NULL,
    `price`            int         NOT NULL
);

CREATE TABLE IF NOT EXISTS `match_seats`
(
    `id`       bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `status`   varchar(10) NOT NULL,
    `match_id` bigint      NOT NULL,
    `seat_id`  bigint      NOT NULL
);