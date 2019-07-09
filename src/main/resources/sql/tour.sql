/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : tour

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-09 14:08:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_fans
-- ----------------------------
DROP TABLE IF EXISTS `a_fans`;
CREATE TABLE `a_fans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='粉丝';

-- ----------------------------
-- Records of a_fans
-- ----------------------------

-- ----------------------------
-- Table structure for a_live
-- ----------------------------
DROP TABLE IF EXISTS `a_live`;
CREATE TABLE `a_live` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直播';

-- ----------------------------
-- Records of a_live
-- ----------------------------

-- ----------------------------
-- Table structure for a_talk
-- ----------------------------
DROP TABLE IF EXISTS `a_talk`;
CREATE TABLE `a_talk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聊天';

-- ----------------------------
-- Records of a_talk
-- ----------------------------

-- ----------------------------
-- Table structure for b_card
-- ----------------------------
DROP TABLE IF EXISTS `b_card`;
CREATE TABLE `b_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='名片';

-- ----------------------------
-- Records of b_card
-- ----------------------------

-- ----------------------------
-- Table structure for b_comment
-- ----------------------------
DROP TABLE IF EXISTS `b_comment`;
CREATE TABLE `b_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of b_comment
-- ----------------------------

-- ----------------------------
-- Table structure for b_company
-- ----------------------------
DROP TABLE IF EXISTS `b_company`;
CREATE TABLE `b_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司（供应商）';

-- ----------------------------
-- Records of b_company
-- ----------------------------

-- ----------------------------
-- Table structure for b_oauth
-- ----------------------------
DROP TABLE IF EXISTS `b_oauth`;
CREATE TABLE `b_oauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_oauth
-- ----------------------------

-- ----------------------------
-- Table structure for b_order
-- ----------------------------
DROP TABLE IF EXISTS `b_order`;
CREATE TABLE `b_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_type` int(11) DEFAULT NULL COMMENT '支付类型 ：0 支付宝 1 微信',
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型: 0 认证  1  打赏',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_order
-- ----------------------------

-- ----------------------------
-- Table structure for b_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `b_order_detail`;
CREATE TABLE `b_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for b_post
-- ----------------------------
DROP TABLE IF EXISTS `b_post`;
CREATE TABLE `b_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_type` int(11) DEFAULT NULL COMMENT '文章类型：0 个人介绍 1 个人动态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of b_post
-- ----------------------------

-- ----------------------------
-- Table structure for b_product
-- ----------------------------
DROP TABLE IF EXISTS `b_product`;
CREATE TABLE `b_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_product
-- ----------------------------

-- ----------------------------
-- Table structure for b_project
-- ----------------------------
DROP TABLE IF EXISTS `b_project`;
CREATE TABLE `b_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目';

-- ----------------------------
-- Records of b_project
-- ----------------------------

-- ----------------------------
-- Table structure for c_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_menu`;
CREATE TABLE `c_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_menu
-- ----------------------------

-- ----------------------------
-- Table structure for c_role
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_role
-- ----------------------------

-- ----------------------------
-- Table structure for c_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_role_menu`;
CREATE TABLE `c_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user` (
  `USER_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `REALNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `SSEX` char(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `AVATAR` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  `STATUS` char(1) NOT NULL COMMENT '状态 0锁定 1有效',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最近访问时间',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of c_user
-- ----------------------------

-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_user_role`;
CREATE TABLE `c_user_role` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `USER_ROLE` varchar(30) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of c_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for s_parameter
-- ----------------------------
DROP TABLE IF EXISTS `s_parameter`;
CREATE TABLE `s_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数';

-- ----------------------------
-- Records of s_parameter
-- ----------------------------

-- ----------------------------
-- Function structure for findDeptChildren
-- ----------------------------
DROP FUNCTION IF EXISTS `findDeptChildren`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `findDeptChildren`(rootId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
  DECLARE sTemp VARCHAR(4000);
    DECLARE sTempChd VARCHAR(4000);
    SET sTemp = '$';
    SET sTempChd = CAST(rootId as CHAR);
    WHILE sTempChd is not null DO
    SET sTemp = CONCAT(sTemp,',',sTempChd);
    SELECT GROUP_CONCAT(dept_id) INTO sTempChd FROM t_dept
    WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for findMenuChildren
-- ----------------------------
DROP FUNCTION IF EXISTS `findMenuChildren`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `findMenuChildren`(rootId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
  DECLARE sTemp VARCHAR(4000);
    DECLARE sTempChd VARCHAR(4000);
    SET sTemp = '$';
    SET sTempChd = CAST(rootId as CHAR);
    WHILE sTempChd is not null DO
    SET sTemp = CONCAT(sTemp,',',sTempChd);
    SELECT GROUP_CONCAT(menu_id) INTO sTempChd FROM t_menu
    WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;
