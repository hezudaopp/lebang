/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : lebang

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-05-04 10:10:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `city_task`
-- ----------------------------
DROP TABLE IF EXISTS `city_task`;
CREATE TABLE `city_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `city_id` bigint(20) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `province_id` bigint(20) unsigned NOT NULL COMMENT '省份id',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city_task
-- ----------------------------

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `amount` int(10) unsigned NOT NULL COMMENT '任务数量',
  `begin_time` int(10) unsigned NOT NULL COMMENT '任务开始时间',
  `city_limited` tinyint(1) unsigned NOT NULL COMMENT '是否限定城市',
  `cost` decimal(6,2) unsigned NOT NULL COMMENT '任务成本',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `device_type_mask` tinyint(3) unsigned NOT NULL COMMENT '设备类型掩码',
  `each_person_limit` int(10) unsigned NOT NULL COMMENT '每人限制次数',
  `end_time` int(10) unsigned NOT NULL COMMENT '任务结束时间',
  `left_amount` int(10) unsigned NOT NULL COMMENT '任务余量',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `price` decimal(6,2) unsigned NOT NULL COMMENT '任务单价',
  `procedure_num` tinyint(1) unsigned NOT NULL COMMENT '任务步骤数',
  `recommended_person` varchar(20) DEFAULT NULL COMMENT '推荐人',
  `recycle_days_limit` int(10) unsigned NOT NULL COMMENT '每次完成限制周期',
  `review_period` int(10) unsigned DEFAULT NULL COMMENT '审核周期',
  `task_type_id` bigint(20) unsigned NOT NULL COMMENT '任务类型id',
  `task_type_name` varchar(100) NOT NULL COMMENT '任务类型名称',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '5', '1493790500', '0', '18.00', '1493781437', '127', '1', '1503780500', '5', '1493781437', '19.89', '2', null, '0', null, '2', '淘宝评论', '1');

-- ----------------------------
-- Table structure for `task_city`
-- ----------------------------
DROP TABLE IF EXISTS `task_city`;
CREATE TABLE `task_city` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `city_id` bigint(20) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `province_id` bigint(20) unsigned NOT NULL COMMENT '省份id',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_city
-- ----------------------------

-- ----------------------------
-- Table structure for `task_procedure`
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_procedure
-- ----------------------------

-- ----------------------------
-- Table structure for `task_type`
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(100) NOT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_type
-- ----------------------------
INSERT INTO `task_type` VALUES ('1', '1493780037', '0', '1493780260', '微信朋友圈');
INSERT INTO `task_type` VALUES ('2', '1493780049', '1', '1493780049', '微信群');
INSERT INTO `task_type` VALUES ('3', '1493780091', '1', '1493780091', 'QQ空间分享');
INSERT INTO `task_type` VALUES ('4', '1493780102', '1', '1493780102', '微博分享');
INSERT INTO `task_type` VALUES ('5', '1493780112', '1', '1493780112', '微信好友分享');
INSERT INTO `task_type` VALUES ('6', '1493780130', '1', '1493780130', '淘宝评论');

-- ----------------------------
-- Table structure for `user`
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1493863306', '1493863306', '471dc9c238566c9687b4f03822dd0bddec2069d883554fd5e8b7cff7b538447fd4f35420751447b3', 'ROLE_ADMIN', '1', 'lebang');

-- ----------------------------
-- Table structure for `user_task`
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `app_id` int(10) unsigned DEFAULT NULL COMMENT '用户来源app',
  `app_user_id` varchar(32) DEFAULT NULL COMMENT '用户在来源app中user_id',
  `city_id` bigint(20) unsigned NOT NULL COMMENT '城市id',
  `completed_time` int(10) unsigned DEFAULT NULL COMMENT '任务完成时间',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `images` varchar(1000) DEFAULT NULL COMMENT '用户完成任务图片',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `note` varchar(200) DEFAULT NULL COMMENT '用户完成任务留言',
  `province_id` bigint(20) unsigned NOT NULL COMMENT '省份id',
  `review_end_time` int(10) unsigned DEFAULT NULL COMMENT '审核截至时间',
  `reviewed_time` int(10) unsigned DEFAULT NULL COMMENT '任务审核时间',
  `reviewer_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '审核用户id',
  `status` tinyint(2) unsigned NOT NULL COMMENT '任务进度',
  `task_end_time` int(10) unsigned NOT NULL COMMENT '任务结束时间',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_task
-- ----------------------------

-- ----------------------------
-- Table structure for `user_task_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_task_log`;
CREATE TABLE `user_task_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `from_status` tinyint(2) unsigned NOT NULL COMMENT '操作前任务进度',
  `operate_type` tinyint(2) unsigned NOT NULL COMMENT '操作类型',
  `operator_user_id` bigint(20) unsigned NOT NULL COMMENT '操作用户',
  `to_status` tinyint(2) unsigned NOT NULL COMMENT '操作后任务进度',
  `user_task_id` bigint(20) unsigned NOT NULL COMMENT '用户任务id',
  `operator_app_id` int(10) unsigned DEFAULT NULL COMMENT '操作用户来源app',
  `operator_app_user_id` varchar(32) DEFAULT NULL COMMENT '操作用户在来源app中user_id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_task_log
-- ----------------------------
