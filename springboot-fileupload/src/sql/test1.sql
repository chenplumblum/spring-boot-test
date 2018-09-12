/*
Navicat MySQL Data Transfer

Source Server         : 我的
Source Server Version : 50626
Source Host           : 119.29.159.194:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-09-12 11:09:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `passWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('28', 'aa', 'a123456', 'MAN', null, null, null);
INSERT INTO `users` VALUES ('29', 'bb', 'b123456', 'WOMAN', null, null, null);
INSERT INTO `users` VALUES ('30', 'cc', 'b123456', 'WOMAN', null, null, null);
