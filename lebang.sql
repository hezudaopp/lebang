-- ----------------------------
-- 用户表
-- 1. 用户表的数据需要从app服务器导入吗？
-- 2. 登录使用用户名还是手机号
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` char(64) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `password` char(80) DEFAULT NULL,
  `all_history_balance` decimal(6,2) DEFAULT NULL,
  `balance` decimal(6,2) DEFAULT NULL,
  `channel` varchar(50) DEFAULT NULL,
  `first_check_time` bigint(20) NOT NULL,
  `freeze_balance` decimal(6,2) DEFAULT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `invite_code` char(5) DEFAULT NULL,
  `is_valid` tinyint(1) NOT NULL,
  `valid_time` bigint(20) NOT NULL,
  `last_login_time` bigint(20) NOT NULL,
  `reg_ip` bigint(20) NOT NULL,
  `uuid` varchar(128) DEFAULT NULL,
  `master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_phone` (`phone`) USING BTREE,
  KEY `idx_invite_code` (`invite_code`) USING BTREE,
  KEY `idx_master_id` (`master_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 任务类型表
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 任务表
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;