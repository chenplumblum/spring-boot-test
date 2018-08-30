/*
Navicat MySQL Data Transfer

Source Server         : chenpeibin
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-08-30 15:13:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_oprlog
-- ----------------------------
DROP TABLE IF EXISTS `sys_oprlog`;
CREATE TABLE `sys_oprlog` (
  `OPRLOG_ID` varchar(36) NOT NULL COMMENT '操作日志id',
  `OPRLOG_TYPE` varchar(20) DEFAULT NULL COMMENT '操作类型，如1：增加 2：删除 3：查询 4：修改 5：其他',
  `OPRLOG_USER_NAME` varchar(40) DEFAULT NULL COMMENT '操作人姓名',
  `OPRLOG_USER_ID` varchar(36) DEFAULT NULL COMMENT '操作人ID',
  `OPRLOG_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `OPRLOG_REMARK` varchar(50) DEFAULT NULL COMMENT '操作备注',
  `OPRLOG_PARAM` varchar(2000) DEFAULT NULL COMMENT '操作参数，例如删除则填对应的ID',
  `OPRLOG_STATUS` varchar(1) DEFAULT NULL COMMENT '1：成功   0：失败',
  `OPRLOG_LOGIN_IP` varchar(40) DEFAULT NULL COMMENT '访问ip',
  `OPRLOG_USER_ACCOUNT` varchar(40) DEFAULT NULL COMMENT '访问账号',
  `OPRLOG_MODULE_NAME` varchar(40) DEFAULT NULL COMMENT '访问模块',
  `OPRLOG_DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '操作描述',
  `LOGIN_DEPT_ID` varchar(36) DEFAULT NULL COMMENT '用户公司Id',
  PRIMARY KEY (`OPRLOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;
