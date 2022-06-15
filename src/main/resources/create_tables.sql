# Дамп таблицы roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
                         `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                         `role` varchar(250) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id` (`id`),
                         UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `roles` (`id`, `role`)
VALUES
    (1,'ROLE_ADMIN'),
    (2,'ROLE_USER'),
    (3,'GUEST');

# Дамп таблицы users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                         `firstname` varchar(250) DEFAULT NULL,
                         `lastname` varchar(250) DEFAULT NULL,
                         `email` varchar(250) DEFAULT NULL,
                         `username` varchar(250) DEFAULT NULL,
                         `password` varchar(250) DEFAULT NULL,

                          PRIMARY KEY (`id`)


) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`id`, `firstname`, `lastname`,`email`, `username`, `password`)
VALUES
    (1,'Ivan','Ivanov','ivan@mail.ru', 'user','{noop}user'),
    (2,'Ilya','Kizilov','iliakiz@mail.ru', 'admin','{noop}admin');

# Дамп таблицы users_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
                               `user_id` bigint(20) unsigned DEFAULT NULL,
                               `role_id` bigint(20) unsigned DEFAULT NULL,
                               KEY `hasuser` (`user_id`),
                               KEY `hasrole` (`role_id`),
                               CONSTRAINT `hasrole` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT `hasuser` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                               UNIQUE KEY (`user_id` , `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users_roles` VALUES (1,2), (2,1);