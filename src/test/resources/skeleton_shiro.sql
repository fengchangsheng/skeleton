/*
Navicat MySQL Data Transfer

Source Server         : fcs
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : skeleton_shiro

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-03-10 18:25:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) DEFAULT NULL COMMENT "权限类型",
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ("1", null, "用户创建", null, "admin:user:create");
INSERT INTO `t_permission` VALUES ("2", null, "用户查看", null, "admin:user:view");
INSERT INTO `t_permission` VALUES ("3", null, "用户删除", null, "admin:user:delete");
INSERT INTO `t_permission` VALUES ("4", null, "用户更新", null, "admin:user:update");
INSERT INTO `t_permission` VALUES ("5", null, "角色查看", null, "admin:role:view");

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ("1", "admin", "管理员");
INSERT INTO `t_role` VALUES ("2", "leader", "公司领导");

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) DEFAULT NULL,
  `perid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ("1", "1", "1");
INSERT INTO `t_role_permission` VALUES ("2", "1", "2");
INSERT INTO `t_role_permission` VALUES ("3", "1", "3");
INSERT INTO `t_role_permission` VALUES ("4", "1", "4");
INSERT INTO `t_role_permission` VALUES ("5", "2", "1");
INSERT INTO `t_role_permission` VALUES ("6", "2", "5");

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` tinyint(2) DEFAULT "1" COMMENT "启用/禁用",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=839662116311633921 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ("1", "三毛", null, "15669852653", "1");
INSERT INTO `t_user` VALUES ("836043268161261568", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("837134894975549440", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("837135134361255936", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("837135380675952640", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("837135380675952641", "fcs", "123", null, "1");
INSERT INTO `t_user` VALUES ("839659443315163136", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("839660000863993856", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("839660220125437952", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("839660221983514624", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("839661736089612288", "张三", "456", "15526538456", "1");
INSERT INTO `t_user` VALUES ("839662116311633920", "张三", "456", "15526538456", "1");

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ("1", "837135380675952641", "1");
INSERT INTO `t_user_role` VALUES ("2", "837135380675952641", "2");
