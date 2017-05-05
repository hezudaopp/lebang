/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : lebang

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-05-05 10:45:26
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
  `name` varchar(100) NOT NULL COMMENT '任务名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '5', '1493790500', '0', '18.00', '1493781437', '127', '2', '1503780500', '3', '1493781437', '19.89', '2', null, '1', null, '2', '淘宝评论', '1', '评论快去');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_city` (`task_id`,`city_id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1493863306', '1493863306', '471dc9c238566c9687b4f03822dd0bddec2069d883554fd5e8b7cff7b538447fd4f35420751447b3', 'ROLE_ADMIN', '1', 'lebang');
INSERT INTO `user` VALUES ('2', '1493951428', '1493951428', 'd868a87d197667983fa5560f01a9bd6cf9d6d24c87ddb4a766b27b6603786d903e040baa561458fa', 'ROLE_TASK_REVIEWER', '1', 'reviewer1');
INSERT INTO `user` VALUES ('3', '1493951434', '1493951434', '00c1c0f1e0279b09623d77a26ea795854fc70b6a0605f1bb7fa9067fa1fb4faf93254e28b8df1c58', 'ROLE_TASK_REVIEWER', '1', 'reviewer2');
INSERT INTO `user` VALUES ('4', '1493951436', '1493951436', 'd79c9c9b445216fb8f4817f4e1b6011ff6594382124fda7b60a9aff421cb640f4983d78dee2201ab', 'ROLE_TASK_REVIEWER', '1', 'reviewer3');
INSERT INTO `user` VALUES ('5', '1493951439', '1493951439', '93d9db7bced8efe57ec66f7ba5d48944ad09cca99df3d452822e5f5f887004c9c690f2ba9ac7e57a', 'ROLE_TASK_REVIEWER', '1', 'reviewer4');
INSERT INTO `user` VALUES ('6', '1493951443', '1493951443', 'be07d414b258d4102bb8bde30460efbf24fb59a4d8470c7e620b1c59d31780d9744c6d8e36c38e07', 'ROLE_TASK_REVIEWER', '1', 'reviewer5');
INSERT INTO `user` VALUES ('7', '1493951446', '1493951446', 'd1b0b4984577f1c2ef2bb586e8d0cf63da3c421412620e6e8ecf2159649c4db4eeacc556b3e87665', 'ROLE_TASK_REVIEWER', '1', 'reviewer6');
INSERT INTO `user` VALUES ('8', '1493951450', '1493951450', '7dcc786a6dd09ba99babc895c66e21a938aeb9f0d29ada0addbc8601ca2b3f6ef7de27a47632ebe2', 'ROLE_TASK_REVIEWER', '1', 'reviewer7');
INSERT INTO `user` VALUES ('9', '1493951454', '1493951454', '340ea2eac052f03b7af883e8972653cd354c78e3549f1947c0c3b03e09a64378b9847e6ee6ebc87a', 'ROLE_TASK_REVIEWER', '1', 'reviewer8');

-- ----------------------------
-- Table structure for `user_task`
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `app_id` int(10) unsigned NOT NULL COMMENT '用户来源app',
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
  `status` tinyint(2) unsigned NOT NULL COMMENT '任务进度',
  `task_end_time` int(10) unsigned NOT NULL COMMENT '任务结束时间',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务id',
  `task_name` varchar(100) NOT NULL COMMENT '任务名称',
  `task_type_name` varchar(100) NOT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_task
-- ----------------------------
INSERT INTO `user_task` VALUES ('1', '1', '9822438', null, '1493951272', '1493910000', '1.jpg,2.jpg', '1493951272', '', '19.89', null, '3641434072', null, null, '1', '1503780500', '1', '评论快去', '淘宝评论');
INSERT INTO `user_task` VALUES ('2', '1', '9822438', null, '1493952296', '1493948260', '1.jpg,2.jpg', '1493952296', '', '19.89', null, '3641435096', null, '4', '1', '1503780500', '1', '评论快去', '淘宝评论');

-- ----------------------------
-- Table structure for `user_task_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_task_log`;
CREATE TABLE `user_task_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `from_status` tinyint(2) unsigned NOT NULL COMMENT '操作前任务进度',
  `operator_app_id` int(10) unsigned DEFAULT NULL COMMENT '操作用户来源app',
  `operator_app_user_id` varchar(32) DEFAULT NULL COMMENT '操作用户在来源app中user_id',
  `operator_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '操作用户',
  `to_status` tinyint(2) unsigned NOT NULL COMMENT '操作后任务进度',
  `user_task_id` bigint(20) unsigned NOT NULL COMMENT '用户任务id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_task_log
-- ----------------------------
INSERT INTO `user_task_log` VALUES ('1', '1493910000', '0', '1', '9822438', null, '0', '1');
INSERT INTO `user_task_log` VALUES ('2', '1493948260', '0', '1', '9822438', null, '0', '2');
INSERT INTO `user_task_log` VALUES ('4', '1493951272', '0', '1', '9822438', null, '1', '1');
INSERT INTO `user_task_log` VALUES ('5', '1493951761', '0', '1', '9822438', null, '1', '2');
INSERT INTO `user_task_log` VALUES ('6', '1493951779', '0', '1', '9822438', null, '1', '2');
INSERT INTO `user_task_log` VALUES ('7', '1493951808', '0', '1', '9822438', null, '1', '2');
INSERT INTO `user_task_log` VALUES ('8', '1493951819', '0', '1', '9822438', null, '1', '2');
INSERT INTO `user_task_log` VALUES ('9', '1493951829', '0', '1', '9822438', null, '1', '2');
INSERT INTO `user_task_log` VALUES ('10', '1493952296', '4', '1', '9822438', null, '1', '2');
