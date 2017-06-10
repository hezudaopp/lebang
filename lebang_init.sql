/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : lebang

 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 06/10/2017 21:58:40 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `oauth_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_approvals`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `access_token_validity` int(10) unsigned DEFAULT NULL COMMENT 'access token 有效期，单位为s',
  `additional_information` varchar(2048) DEFAULT NULL COMMENT '附加信息',
  `authorities` varchar(255) DEFAULT NULL COMMENT '权限',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '认证授权类型',
  `autoapprove` varchar(255) DEFAULT NULL COMMENT '???',
  `client_id` varchar(64) DEFAULT NULL COMMENT '客户端id，只能包含字母和数字',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '密码',
  `refresh_token_validity` int(10) unsigned DEFAULT NULL COMMENT 'refresh token 有效期，单位为s',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '资源id',
  `scope` varchar(255) DEFAULT NULL COMMENT '范围',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '重定向uri',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `oauth_client_details`
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('1', '7200', '{}', 'ROLE_CLIENT', 'password,refresh_token', '', 'lebang_client', 'lnpOeaUQrmQj7r9a6f94ltjCuzqY7jEvO', '2592000', '', 'read,write', ''), ('3', '7200', null, 'ROLE_APP', 'client_credentials', '', 'app1', '2NF@lnH@Ta@!eTl$yis9-%Ak+Kc$47~w', null, '', 'trust', '');
COMMIT;

-- ----------------------------
--  Table structure for `oauth_client_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_code`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_refresh_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `user` VALUES ('1', '1493863306', '1493863306', '471dc9c238566c9687b4f03822dd0bddec2069d883554fd5e8b7cff7b538447fd4f35420751447b3', 'ROLE_ADMIN', '1', 'lebang', null, null);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
