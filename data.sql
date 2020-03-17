DROP TABLE IF EXISTS `user`;


CREATE TABLE `user` (
  `user_id` int(11) NOT NULL COMMENT 'user id',
  `user_code` varchar(60) DEFAULT NULL COMMENT 'user code',
  `user_name` varchar(255) DEFAULT NULL COMMENT 'user name',
  `password` varchar(255) DEFAULT NULL COMMENT 'password',
  `state` varchar(1) DEFAULT NULL COMMENT 'A valid, X invalid, B not active',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='user';