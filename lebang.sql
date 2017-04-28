-- ----------------------------
-- 用户表
-- 1. 用户表的数据需要从app服务器导入吗？
-- 2. 登录使用用户名还是手机号
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` int(10) unsigned DEFAULT NULL COMMENT '用户来源app',
  `app_user_id` varchar(32) DEFAULT NULL COMMENT '用户在来源app中user_id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `mobile` char(11) NOT NULL COMMENT '手机号',
  `password` char(80) DEFAULT NULL COMMENT '密码',
  `all_history_balance` decimal(6,2) DEFAULT NULL COMMENT '历史全部余额',
  `balance` decimal(6,2) DEFAULT NULL COMMENT '当前账户余额',
  `freeze_balance` decimal(6,2) DEFAULT NULL COMMENT '冻结金额',
  `imei` varchar(32) DEFAULT NULL COMMENT '手机IMEI号',
  `last_login_time` bigint(20) NOT NULL COMMENT '上次登录时间',
  `status` tinyint(2) NOT NULL COMMENT '用户状态',
  `created_time` int(10) NOT NULL,
  `modified_time` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_mobile` (`mobile`) USING BTREE,
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- 任务类型表
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '任务类型名称',
  `enabled` tinyint(1) NOT NULL COMMENT '是否启用',
  `created_time` int(10) NOT NULL,
  `modified_time` int(10) NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 任务表
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_type_id` bigint(20) unsigned NOT NULL COMMENT '任务类型id',
  `task_type_name` varchar(100) NOT NULL COMMENT '任务类型名称，冗余',
  `price` decimal(6,2) unsigned NOT NULL COMMENT '任务单价',
  `amount` int(10) unsigned NOT NULL COMMENT '任务数量',
  `left_amount` int(10) unsigned NOT NULL COMMENT '任务余量，冗余',
  `review_period` int(10) unsigned DEFAULT NULL COMMENT '审核周期',
  `begin_time` int(10) unsigned NOT NULL COMMENT '任务开始时间',
  `end_time` int(10) unsigned NOT NULL COMMENT '任务结束时间',
  `device_type_id` int(10) unsigned NOT NULL COMMENT '设备类型id',
  `each_person_limit` int(10) unsigned NOT NULL DEFAULT 2147483647 COMMENT '每人限制次数',
  `recycle_days_limit` int(10) unsigned NOT NULL DEFAULT 2147483647 COMMENT '每次完成限制周期',
  `recommended_person` varchar(20) DEFAULT NULL COMMENT '推荐人',
  `max_procedure_num` tinyint(2) unsigned NOT NULL DEFAULT 5 COMMENT '最大允许步骤数',
  `check_city` tinyint(1) unsigned NOT NULL COMMENT '是否限定城市',
  `created_time` int(10) unsigned NOT NULL,
  `modified_time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 任务城市表
-- ----------------------------
DROP TABLE IF EXISTS `task_cities`;
CREATE TABLE `task_cities` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  `province_id` bigint(20) unsigned NOT NULL COMMENT '省份id，冗余',
  `city_id` bigint(20) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned NOT NULL,
  `modified_time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 任务步骤
-- ----------------------------
DROP TABLE IF EXISTS `task_procedure`;
CREATE TABLE `task_procedure` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  `order` tinyint(2) unsigned NOT NULL COMMENT '顺序',
  `desc` varchar(2000) NOT NULL COMMENT '描述',
  `images` varchar(500) NOT NULL COMMENT '图片',
  `created_time` int(10) unsigned NOT NULL,
  `modified_time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 用户任务
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL COMMENT '领取任务的用户id',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  `province_id` bigint(20) unsigned DEFAULT NULL COMMENT '省份id',
  `city_id` bigint(20) unsigned DEFAULT NULL COMMENT '城市id',
  `note` varchar(2000) NOT NULL COMMENT '用户完成任务留言',
  `images` varchar(1000) DEFAULT NULL COMMENT '用户完成任务图片',
  `status` tinyint(2) unsigned NOT NULL COMMENT '任务进度',
  `end_time` int(10) unsigned NOT NULL DEFAULT 2147483647 COMMENT '任务结束时间，冗余',
  `reviewer_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '审核用户id',
  `review_end_time` int(10) unsigned NOT NULL DEFAULT 2147483647 COMMENT '审核截至时间',
  `created_time` int(10) unsigned NOT NULL,
  `modified_time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 用户任务日志
-- ----------------------------
DROP TABLE IF EXISTS `user_task_log`;
CREATE TABLE `user_task_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_task_id` bigint(20) unsigned NOT NULL COMMENT '用户任务id',
  `operate_type` tinyint(2) unsigned NOT NULL COMMENT '操作类型',
  `operator_user` bigint(20) unsigned NOT NULL COMMENT '操作用户',
  `from_status` tinyint(2) unsigned NOT NULL COMMENT '操作前任务进度',
  `to_status` tinyint(2) unsigned NOT NULL COMMENT '操作后任务进度',
  `created_time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
