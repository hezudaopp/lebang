/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 100110
 Source Host           : localhost
 Source Database       : lebang

 Target Server Type    : MySQL
 Target Server Version : 100110
 File Encoding         : utf-8

 Date: 06/05/2017 16:51:49 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `app`
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) NOT NULL COMMENT 'app名称',
  `secret` char(32) NOT NULL COMMENT 'app_secret',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `app`
-- ----------------------------
BEGIN;
INSERT INTO `app` VALUES ('1', '1496647145', '1', '1496647145', 'app1', 'DJnNL@f~PThY^eue1-sMhnx%Psa7DtE8'), ('2', '1496647154', '1', '1496647154', 'app2', 'V%nv~TPVj^8oYk18x$DJKW-9oK$$IYPe'), ('3', '1496647164', '1', '1496647164', 'app3', 'rx%Zw7nnc+jXPtGar~XVK%W3yGKOJfSj');
COMMIT;

-- ----------------------------
--  Table structure for `reviewer_task_statistics`
-- ----------------------------
DROP TABLE IF EXISTS `reviewer_task_statistics`;
CREATE TABLE `reviewer_task_statistics` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `accepted_amount` int(10) unsigned NOT NULL COMMENT '用户审核通过的任务数量',
  `begin_time` int(10) unsigned NOT NULL COMMENT '统计开始时间',
  `end_time` int(10) unsigned NOT NULL COMMENT '统计结束时间',
  `reviewed_amount` int(10) unsigned NOT NULL COMMENT '用户审核通过和拒绝的任务数量总和',
  `reviewer_user_id` bigint(20) unsigned NOT NULL COMMENT '审核人员UserId',
  `reviewer_username` varchar(20) DEFAULT NULL COMMENT '审核人员用户名',
  `total_flow` decimal(11,2) unsigned NOT NULL COMMENT '用户审核通过的总流水',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_reviewer_begin_end` (`reviewer_user_id`,`begin_time`,`end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `reviewer_task_statistics`
-- ----------------------------
BEGIN;
INSERT INTO `reviewer_task_statistics` VALUES ('1', '3', '1493568000', '1496246400', '4', '2', 'reviewer1', '23.89'), ('2', '2', '1493568000', '1496246400', '2', '3', 'reviewer2', '4.00'), ('3', '1', '1493568000', '1496246400', '2', '4', 'reviewer3', '2.00'), ('4', '2', '1493568000', '1496246400', '3', '5', 'reviewer4', '4.00');
COMMIT;

-- ----------------------------
--  Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `accepted_amount` int(10) unsigned NOT NULL COMMENT '任务通过量',
  `amount` int(10) unsigned NOT NULL COMMENT '任务数量',
  `begin_time` int(10) unsigned NOT NULL COMMENT '任务开始时间',
  `city_limited` tinyint(1) unsigned NOT NULL COMMENT '是否限定城市',
  `completed_amount` int(10) unsigned NOT NULL COMMENT '任务完成量',
  `cost` decimal(6,2) unsigned DEFAULT NULL COMMENT '任务成本',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `device_type_mask` tinyint(3) unsigned NOT NULL COMMENT '设备类型掩码',
  `each_person_limit` int(10) unsigned NOT NULL COMMENT '每人限制次数',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `end_time` int(10) unsigned NOT NULL COMMENT '任务结束时间',
  `left_amount` int(10) unsigned NOT NULL COMMENT '任务余量',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(100) NOT NULL COMMENT '任务名称',
  `price` decimal(6,2) unsigned NOT NULL COMMENT '任务单价',
  `recommended_person` varchar(20) DEFAULT NULL COMMENT '推荐人',
  `recycle_days_limit` int(10) unsigned NOT NULL COMMENT '每次完成限制周期',
  `rejected_amount` int(10) unsigned NOT NULL COMMENT '任务拒绝量',
  `review_period` int(10) unsigned DEFAULT NULL COMMENT '审核周期',
  `task_type_id` bigint(20) unsigned NOT NULL COMMENT '任务类型id',
  `task_type_name` varchar(100) NOT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `task`
-- ----------------------------
BEGIN;
INSERT INTO `task` VALUES ('1', '1', '5', '1493790500', '0', '1', '18.00', '1496647451', '127', '1', '1', '1573780500', '0', '1496647451', '淘宝评论任务', '19.89', null, '0', '0', null, '1', '淘宝评论'), ('2', '12', '500', '1393790500', '0', '18', '18.00', '1496647590', '127', '1', '1', '1603780500', '475', '1496647590', '微信朋友圈任务', '2.00', null, '0', '5', null, '2', '分享微信朋友圈');
COMMIT;

-- ----------------------------
--  Table structure for `task_app_statistics`
-- ----------------------------
DROP TABLE IF EXISTS `task_app_statistics`;
CREATE TABLE `task_app_statistics` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `accepted_amount` int(10) unsigned NOT NULL COMMENT '任务渠道通过量',
  `app_id` bigint(20) unsigned NOT NULL COMMENT 'app渠道id',
  `app_name` varchar(50) DEFAULT NULL COMMENT '用户来源渠道app名称',
  `begin_time` int(10) unsigned NOT NULL COMMENT '统计开始时间',
  `completed_amount` int(10) unsigned NOT NULL COMMENT '任务渠道完成量',
  `end_time` int(10) unsigned NOT NULL COMMENT '统计结束时间',
  `received_amount` int(10) unsigned NOT NULL COMMENT '任务渠道领取量',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  `task_name` varchar(100) DEFAULT NULL COMMENT '任务名称',
  `total_flow` decimal(11,2) unsigned NOT NULL COMMENT '任务渠道流水',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_app_begin_end` (`task_id`,`app_id`,`begin_time`,`end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `task_app_statistics`
-- ----------------------------
BEGIN;
INSERT INTO `task_app_statistics` VALUES ('1', '5', '2', 'app2', '1496592000', '6', '1496678400', '0', '2', '微信朋友圈任务', '10.00'), ('2', '0', '1', 'app1', '1496160000', '0', '1496246400', '1', '1', '淘宝评论任务', '0.00');
COMMIT;

-- ----------------------------
--  Table structure for `task_city`
-- ----------------------------
DROP TABLE IF EXISTS `task_city`;
CREATE TABLE `task_city` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `city_id` bigint(20) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `province_id` bigint(20) unsigned NOT NULL COMMENT '省份id',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_city` (`task_id`,`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `task_procedure`
-- ----------------------------
DROP TABLE IF EXISTS `task_procedure`;
CREATE TABLE `task_procedure` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `description` varchar(2000) NOT NULL COMMENT '描述',
  `images` varchar(500) NOT NULL COMMENT '图片',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `procedure_order` tinyint(1) unsigned NOT NULL COMMENT '顺序',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_procedure_order` (`task_id`,`procedure_order`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `task_procedure`
-- ----------------------------
BEGIN;
INSERT INTO `task_procedure` VALUES ('1', '1496647451', 'order 1', '1.jpg,2.jpg', '1496647451', '3', '1'), ('2', '1496647451', 'order 2', '1.jpg,2.jpg', '1496647451', '1', '1'), ('3', '1496647590', 'order 1', '1.jpg,2.jpg', '1496647590', '3', '2'), ('4', '1496647590', 'order 2', '1.jpg,2.jpg', '1496647590', '1', '2');
COMMIT;

-- ----------------------------
--  Table structure for `task_type`
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(100) NOT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `task_type`
-- ----------------------------
BEGIN;
INSERT INTO `task_type` VALUES ('1', '1496647192', '1', '1496647192', '淘宝评论'), ('2', '1496647267', '1', '1496647267', '分享微信朋友圈');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `password` char(80) DEFAULT NULL COMMENT '用户密码（登录用）,使用Spring Security的BaseEncoder加密',
  `role` varchar(20) NOT NULL COMMENT '用户角色',
  `status` tinyint(2) unsigned NOT NULL COMMENT '用户状态',
  `username` varchar(20) NOT NULL COMMENT '用户账号（登录用）',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '1493863306', '1493863306', '471dc9c238566c9687b4f03822dd0bddec2069d883554fd5e8b7cff7b538447fd4f35420751447b3', 'ROLE_ADMIN', '1', 'lebang', null, null), ('2', '1493951428', '1493951428', 'd868a87d197667983fa5560f01a9bd6cf9d6d24c87ddb4a766b27b6603786d903e040baa561458fa', 'ROLE_TASK_REVIEWER', '1', 'reviewer1', null, null), ('3', '1493951434', '1493951434', '00c1c0f1e0279b09623d77a26ea795854fc70b6a0605f1bb7fa9067fa1fb4faf93254e28b8df1c58', 'ROLE_TASK_REVIEWER', '1', 'reviewer2', null, null), ('4', '1493951436', '1493951436', 'd79c9c9b445216fb8f4817f4e1b6011ff6594382124fda7b60a9aff421cb640f4983d78dee2201ab', 'ROLE_TASK_REVIEWER', '1', 'reviewer3', null, null), ('5', '1493951439', '1493951439', '93d9db7bced8efe57ec66f7ba5d48944ad09cca99df3d452822e5f5f887004c9c690f2ba9ac7e57a', 'ROLE_TASK_REVIEWER', '1', 'reviewer4', null, null), ('6', '1493951443', '1493951443', 'be07d414b258d4102bb8bde30460efbf24fb59a4d8470c7e620b1c59d31780d9744c6d8e36c38e07', 'ROLE_TASK_REVIEWER', '1', 'reviewer5', null, null), ('7', '1493951446', '1493951446', 'd1b0b4984577f1c2ef2bb586e8d0cf63da3c421412620e6e8ecf2159649c4db4eeacc556b3e87665', 'ROLE_TASK_REVIEWER', '1', 'reviewer6', null, null), ('8', '1493951450', '1493951450', '7dcc786a6dd09ba99babc895c66e21a938aeb9f0d29ada0addbc8601ca2b3f6ef7de27a47632ebe2', 'ROLE_TASK_REVIEWER', '1', 'reviewer7', null, null), ('9', '1493951454', '1493951454', '340ea2eac052f03b7af883e8972653cd354c78e3549f1947c0c3b03e09a64378b9847e6ee6ebc87a', 'ROLE_TASK_REVIEWER', '1', 'reviewer8', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user_task`
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '用户来源渠道app',
  `app_name` varchar(50) DEFAULT NULL COMMENT '用户来源渠道app名称',
  `app_user_id` varchar(32) NOT NULL COMMENT '用户在来源app中user_id',
  `city_id` bigint(20) unsigned DEFAULT NULL COMMENT '城市id',
  `completed_time` int(10) unsigned DEFAULT NULL COMMENT '任务完成时间',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `images` varchar(1000) DEFAULT NULL COMMENT '用户完成任务图片',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `note` varchar(200) DEFAULT NULL COMMENT '用户完成任务留言',
  `price` decimal(6,2) unsigned NOT NULL COMMENT '任务单价',
  `province_id` bigint(20) unsigned DEFAULT NULL COMMENT '省份id',
  `review_end_time` int(10) unsigned DEFAULT NULL COMMENT '审核截至时间',
  `reviewed_time` int(10) unsigned DEFAULT NULL COMMENT '任务审核时间',
  `reviewer_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '审核用户id',
  `reviewer_username` varchar(20) DEFAULT NULL COMMENT '审核人员用户名',
  `status` tinyint(2) unsigned NOT NULL COMMENT '任务进度',
  `task_end_time` int(10) unsigned NOT NULL COMMENT '任务结束时间',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  `task_name` varchar(100) NOT NULL COMMENT '任务名称',
  `task_type_name` varchar(100) NOT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_task`
-- ----------------------------
BEGIN;
INSERT INTO `user_task` VALUES ('1', '1', 'app1', 'app1user1', null, '1496389969', '1496216357', '1.jpg,2.jpg', '1496247654', '', '19.89', null, '3643872769', '1496237654', '2', 'reviewer1', '2', '1573780500', '1', '淘宝评论任务', '淘宝评论'), ('2', '1', 'app1', 'app1user1', null, '1496389983', '1496130004', '1.jpg,2.jpg', '1496247662', '', '2.00', null, '3643872783', '1496237662', '2', 'reviewer1', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('3', '1', 'app1', 'app1user2', null, '1496390015', '1496130016', '1.jpg,2.jpg', '1496247665', '', '2.00', null, '3643872815', '1496237665', '3', 'reviewer2', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('4', '1', 'app1', 'app1user3', null, '1496390022', '1496130020', '1.jpg,2.jpg', '1496247666', '', '2.00', null, '3643872822', '1496237666', '2', 'reviewer1', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('5', '1', 'app1', 'app1user4', null, '1496390030', '1496130023', '1.jpg,2.jpg', '1496247667', '', '2.00', null, '3643872830', '1496237667', '4', 'reviewer3', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('6', '1', 'app1', 'app1user5', null, '1496390037', '1496130025', '1.jpg,2.jpg', '1496247675', '', '2.00', null, '3643872837', '1496237675', '4', 'reviewer3', '3', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('7', '1', 'app1', 'app1user6', null, '1496390045', '1496389361', '1.jpg,2.jpg', '1496247676', '', '2.00', null, '3643872845', '1496237676', '5', 'reviewer4', '3', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('8', '1', 'app1', 'app1user7', null, '1496390059', '1496389365', '1.jpg,2.jpg', '1496247678', '', '2.00', null, '3643872859', '1496237678', '2', 'reviewer1', '3', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('9', '1', 'app1', 'app1user8', null, '1496390081', '1496389375', '1.jpg,2.jpg', '1496247686', '', '2.00', null, '3643872881', '1496237686', '5', 'reviewer4', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('10', '1', 'app1', 'app1user9', null, '1496390098', '1496389382', '1.jpg,2.jpg', '1496247687', '', '2.00', null, '3643872898', '1496237687', '5', 'reviewer4', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('11', '1', 'app1', 'app1user10', null, '1496390108', '1496389387', '1.jpg,2.jpg', '1496247689', '', '2.00', null, '3643872908', '1496237689', '3', 'reviewer2', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('12', '1', 'app1', 'app1user10', null, null, '1496389390', null, '1496389390', null, '19.89', null, null, null, null, null, '0', '1573780500', '1', '淘宝评论任务', '淘宝评论'), ('13', '1', 'app1', 'app1user9', null, null, '1496389396', null, '1496389396', null, '19.89', null, null, null, null, null, '0', '1573780500', '1', '淘宝评论任务', '淘宝评论'), ('14', '1', 'app1', 'app1user8', null, null, '1496389411', null, '1496389411', null, '19.89', null, null, null, null, null, '0', '1573780500', '1', '淘宝评论任务', '淘宝评论'), ('15', '2', 'app2', 'app2user1', null, '1496591988', '1496591814', '1.jpg,2.jpg', '1496592140', '', '2.00', null, '3644074788', '1496592140', '6', 'reviewer5', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('16', '2', 'app2', 'app2user2', null, '1496591998', '1496591823', '1.jpg,2.jpg', '1496592171', '', '2.00', null, '3644074798', '1496592171', '4', 'reviewer3', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('17', '2', 'app2', 'app2user3', null, '1496592005', '1496591826', '1.jpg,2.jpg', '1496592184', '', '2.00', null, '3644074805', '1496592184', '4', 'reviewer3', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('18', '2', 'app2', 'app2user4', null, '1496592008', '1496591827', '1.jpg,2.jpg', '1496592195', '', '2.00', null, '3644074808', '1496592195', '2', 'reviewer1', '3', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('19', '2', 'app2', 'app2user5', null, '1496592014', '1496591828', '1.jpg,2.jpg', '1496592014', '', '2.00', null, '3644074814', null, '7', 'reviewer6', '1', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('20', '2', 'app2', 'app2user6', null, '1496592021', '1496591829', '1.jpg,2.jpg', '1496678057', '', '2.00', null, '3644074821', '1496678057', '2', 'reviewer1', '3', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('21', '2', 'app2', 'app2user7', null, '1496592040', '1496591836', '1.jpg,2.jpg', '1496678066', '', '2.00', null, '3644074840', '1496678066', '3', 'reviewer2', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('22', '2', 'app2', 'app2user8', null, '1496592050', '1496591840', '1.jpg,2.jpg', '1496678067', '', '2.00', null, '3644074850', '1496678067', '7', 'reviewer6', '2', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('23', '2', 'app2', 'app2user9', null, null, '1496591842', null, '1496591842', null, '2.00', null, null, null, null, null, '0', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('24', '2', 'app2', 'app2user10', null, null, '1496591844', null, '1496591844', null, '2.00', null, null, null, null, null, '0', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('25', '2', 'app2', 'app2user11', null, null, '1496591846', null, '1496591846', null, '2.00', null, null, null, null, null, '0', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('26', '2', 'app2', 'app2user12', null, null, '1496591847', null, '1496591847', null, '2.00', null, null, null, null, null, '0', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('27', '2', 'app2', 'app2user13', null, null, '1496591848', null, '1496591848', null, '2.00', null, null, null, null, null, '0', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('28', '2', 'app2', 'app2user14', null, null, '1496591849', null, '1496591849', null, '2.00', null, null, null, null, null, '0', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈'), ('29', '2', 'app2', 'app2user15', null, null, '1496591854', null, '1496591854', null, '2.00', null, null, null, null, null, '0', '1603780500', '2', '微信朋友圈任务', '分享微信朋友圈');
COMMIT;

-- ----------------------------
--  Table structure for `user_task_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_task_log`;
CREATE TABLE `user_task_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `from_status` tinyint(2) unsigned NOT NULL COMMENT '操作前任务进度',
  `operator_app_id` varchar(20) DEFAULT NULL COMMENT '操作用户来源app',
  `operator_app_user_id` varchar(32) DEFAULT NULL COMMENT '操作用户在来源app中user_id',
  `operator_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '操作用户',
  `to_status` tinyint(2) unsigned NOT NULL COMMENT '操作后任务进度',
  `user_task_id` bigint(20) unsigned NOT NULL COMMENT '用户任务id',
  `remark` varchar(100) DEFAULT NULL COMMENT '操作备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_task_log`
-- ----------------------------
BEGIN;
INSERT INTO `user_task_log` VALUES ('1', '1496648209', '0', '1', 'app1user1', null, '0', '1', null), ('2', '1496216357', '0', '1', 'app1user1', null, '0', '1', null), ('3', '1496130004', '0', '1', 'app1user1', null, '0', '2', null), ('4', '1496130016', '0', '1', 'app1user2', null, '0', '3', null), ('5', '1496130020', '0', '1', 'app1user3', null, '0', '4', null), ('6', '1496130023', '0', '1', 'app1user4', null, '0', '5', null), ('7', '1496130025', '0', '1', 'app1user5', null, '0', '6', null), ('8', '1496389361', '0', '1', 'app1user6', null, '0', '7', null), ('9', '1496389365', '0', '1', 'app1user7', null, '0', '8', null), ('10', '1496389375', '0', '1', 'app1user8', null, '0', '9', null), ('11', '1496389382', '0', '1', 'app1user9', null, '0', '10', null), ('12', '1496389387', '0', '1', 'app1user10', null, '0', '11', null), ('13', '1496389390', '0', '1', 'app1user10', null, '0', '12', null), ('14', '1496389396', '0', '1', 'app1user9', null, '0', '13', null), ('15', '1496389411', '0', '1', 'app1user8', null, '0', '14', null), ('16', '1496389969', '0', '1', 'app1user1', null, '1', '1', null), ('17', '1496389983', '0', '1', 'app1user1', null, '1', '2', null), ('18', '1496390015', '0', '1', 'app1user2', null, '1', '3', null), ('19', '1496390022', '0', '1', 'app1user3', null, '1', '4', null), ('20', '1496390030', '0', '1', 'app1user4', null, '1', '5', null), ('21', '1496390037', '0', '1', 'app1user5', null, '1', '6', null), ('22', '1496390045', '0', '1', 'app1user6', null, '1', '7', null), ('23', '1496390059', '0', '1', 'app1user7', null, '1', '8', null), ('24', '1496390081', '0', '1', 'app1user8', null, '1', '9', null), ('25', '1496390098', '0', '1', 'app1user9', null, '1', '10', null), ('26', '1496390108', '0', '1', 'app1user10', null, '1', '11', null), ('27', '1496591814', '0', '2', 'app2user1', null, '0', '15', null), ('28', '1496591823', '0', '2', 'app2user2', null, '0', '16', null), ('29', '1496591826', '0', '2', 'app2user3', null, '0', '17', null), ('30', '1496591827', '0', '2', 'app2user4', null, '0', '18', null), ('31', '1496591828', '0', '2', 'app2user5', null, '0', '19', null), ('32', '1496591829', '0', '2', 'app2user6', null, '0', '20', null), ('33', '1496591836', '0', '2', 'app2user7', null, '0', '21', null), ('34', '1496591840', '0', '2', 'app2user8', null, '0', '22', null), ('35', '1496591842', '0', '2', 'app2user9', null, '0', '23', null), ('36', '1496591844', '0', '2', 'app2user10', null, '0', '24', null), ('37', '1496591846', '0', '2', 'app2user11', null, '0', '25', null), ('38', '1496591847', '0', '2', 'app2user12', null, '0', '26', null), ('39', '1496591848', '0', '2', 'app2user13', null, '0', '27', null), ('40', '1496591849', '0', '2', 'app2user14', null, '0', '28', null), ('41', '1496591854', '0', '2', 'app2user15', null, '0', '29', null), ('42', '1496591988', '0', '2', 'app2user1', null, '1', '15', null), ('43', '1496591998', '0', '2', 'app2user2', null, '1', '16', null), ('44', '1496592005', '0', '2', 'app2user3', null, '1', '17', null), ('45', '1496592008', '0', '2', 'app2user4', null, '1', '18', null), ('46', '1496592014', '0', '2', 'app2user5', null, '1', '19', null), ('47', '1496592021', '0', '2', 'app2user6', null, '1', '20', null), ('48', '1496592040', '0', '2', 'app2user7', null, '1', '21', null), ('49', '1496592050', '0', '2', 'app2user8', null, '1', '22', null), ('50', '1496591988', '1', null, null, '1', '2', '15', null), ('51', '1496591998', '1', null, null, '1', '2', '16', '123456'), ('52', '1496592005', '1', null, null, '1', '2', '17', '123456'), ('53', '1496592008', '1', null, null, '1', '3', '18', '123456'), ('54', '1496592021', '1', null, null, '1', '3', '20', '123456'), ('55', '1496592040', '1', null, null, '1', '2', '21', '123456'), ('56', '1496592050', '1', null, null, '1', '2', '22', '123456'), ('57', '1496389969', '1', null, null, '1', '2', '1', '123456'), ('58', '1496389983', '1', null, null, '1', '2', '2', '123456'), ('59', '1496390015', '1', null, null, '1', '2', '3', '123456'), ('60', '1496390022', '1', null, null, '1', '2', '4', '123456'), ('61', '1496390030', '1', null, null, '1', '2', '5', '123456'), ('62', '1496390037', '1', null, null, '1', '3', '6', '123456'), ('63', '1496390045', '1', null, null, '1', '3', '7', '123456'), ('64', '1496390059', '1', null, null, '1', '3', '8', '123456'), ('65', '1496390081', '1', null, null, '1', '2', '9', '123456'), ('66', '1496390098', '1', null, null, '1', '2', '10', '123456'), ('67', '1496390108', '1', null, null, '1', '2', '11', '123456');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
