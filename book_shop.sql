/*
Navicat MySQL Data Transfer

Source Server         : fengsn
Source Server Version : 80013
Source Host           : 127.0.0.1:3306
Source Database       : book_shop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-10-30 12:47:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `create_dt` datetime NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('5', '1', '北京市北京市市辖区东城区123', '2018-10-26 10:59:44');
INSERT INTO `address` VALUES ('6', '1', '0', '2018-10-26 11:21:10');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_introduce` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_type` int(11) NOT NULL,
  `book_img` varchar(100) NOT NULL,
  `book_price` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '百年孤独', '加西亚·马尔克斯', '拉丁美洲魔幻现实主义文学的代表作，被誉为“再现拉丁美洲历史社会图景的鸿篇巨著”', '0', '../images/book/shortbook0.jpg', '59.00');
INSERT INTO `book` VALUES ('2', '局外人', '加西亚', '拉丁美洲', '0', '../images/book/shortbook2.png', '55.00');

-- ----------------------------
-- Table structure for `card`
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `create_dt` datetime NOT NULL COMMENT '创建日期',
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('2', '1', '1', '2018-10-26 13:11:33', '2');

-- ----------------------------
-- Table structure for `collect`
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'collect ',
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `create_dt` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('2', '1', '1', '2018-10-26 17:26:35');

-- ----------------------------
-- Table structure for `my_order`
-- ----------------------------
DROP TABLE IF EXISTS `my_order`;
CREATE TABLE `my_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `create_dt` datetime NOT NULL,
  `order_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of my_order
-- ----------------------------
INSERT INTO `my_order` VALUES ('3', '1', '1', '1', '2018-10-25 13:23:51', '2');
INSERT INTO `my_order` VALUES ('4', '1', '1', '1', '2018-10-26 16:59:20', '2');
INSERT INTO `my_order` VALUES ('5', '1', '1', '1', '2018-10-26 16:59:24', '0');
INSERT INTO `my_order` VALUES ('6', '1', '1', '1', '2018-10-26 16:59:34', '0');
INSERT INTO `my_order` VALUES ('7', '1', '1', '1', '2018-10-26 17:28:23', '0');
INSERT INTO `my_order` VALUES ('8', '1', '1', '1', '2018-10-26 17:28:28', '0');
INSERT INTO `my_order` VALUES ('9', '1', '1', '1', '2018-10-26 17:29:31', '0');
INSERT INTO `my_order` VALUES ('10', '1', '1', '1', '2018-10-29 10:10:39', '0');
INSERT INTO `my_order` VALUES ('11', '1', '1', '1', '2018-10-29 10:19:39', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `Telphone` varchar(20) NOT NULL,
  `mail` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `用户唯一` (`user_name`,`password`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lina', '123456', '12345678912', '');
INSERT INTO `user` VALUES ('4', 'fengsn', '123456', '13456310888', '13456310888@163.com');
