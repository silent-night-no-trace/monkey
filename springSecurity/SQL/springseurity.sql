/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : springseurity

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-04-17 18:15:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:菜单 2:接口',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `url` varchar(255) DEFAULT '',
  `icon` varchar(255) DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序,1最大',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0:不应用 1:应用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES ('1', '系统管理', '1', '0', '', '', '1', '1');
INSERT INTO `admin_menu` VALUES ('2', '菜单管理', '1', '0', '', '', '2', '1');
INSERT INTO `admin_menu` VALUES ('3', '用户管理', '1', '1', '', '', '1', '1');
INSERT INTO `admin_menu` VALUES ('4', '角色管理', '1', '1', '', '', '2', '1');
INSERT INTO `admin_menu` VALUES ('5', '获取用户信息', '1', '0', '/getUser2Info', '', '999', '1');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `created_at` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '管理员', '0', null);
INSERT INTO `admin_role` VALUES ('2', '开发', '0', null);
INSERT INTO `admin_role` VALUES ('3', '测试', '0', null);

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES ('1', '1', '1');
INSERT INTO `admin_role_menu` VALUES ('2', '1', '2');
INSERT INTO `admin_role_menu` VALUES ('3', '1', '3');
INSERT INTO `admin_role_menu` VALUES ('4', '1', '4');
INSERT INTO `admin_role_menu` VALUES ('5', '1', '5');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '用户状态',
  `last_visit_time` bigint(20) DEFAULT NULL COMMENT '上次登录时间',
  `created_at` bigint(20) NOT NULL COMMENT '创建用户时间',
  `updated_at` bigint(20) DEFAULT NULL COMMENT '更新用户时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', '31a2d8e66515dea109b7d98efc9091ae', '1', null, '0', null);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES ('1', '1', '1');
INSERT INTO `admin_user_role` VALUES ('2', '1', '2');
