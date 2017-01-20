/*
Navicat MySQL Data Transfer

Source Server         : cf
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : cf

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2016-10-27 09:50:12
*/

USE cf;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill_detail
-- ----------------------------
DROP TABLE IF EXISTS `bill_detail`;
CREATE TABLE `bill_detail` (
  `bill_seq_id` bigint(20) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`bill_seq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bill_seq
-- ----------------------------
DROP TABLE IF EXISTS `bill_seq`;
CREATE TABLE `bill_seq` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `light_seq_id` bigint(20) NOT NULL,
  `order_user` varchar(255) NOT NULL,
  `order_time` datetime NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for catalog
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `made_in` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `flavor` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `is_recommendation` bit(1) NOT NULL DEFAULT b'0',
  `is_bottle` bit(1) NOT NULL DEFAULT b'0',
  `balance_amount` int(11) NOT NULL,
  `icon_url` varchar(255) NOT NULL,
  `is_on_sale` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) DEFAULT b'0',
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for light_seq
-- ----------------------------
DROP TABLE IF EXISTS `light_seq`;
CREATE TABLE `light_seq` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `checked_bill` bit(1) NOT NULL DEFAULT b'0',
  `open_time` datetime NOT NULL,
  `open_user` varchar(255) NOT NULL,
  `settle_time` datetime NOT NULL,
  `settle_user` varchar(255) NOT NULL,
  `has_fully_paid` bit(1) NOT NULL DEFAULT b'0',
  `paid_amount` decimal(10,2) NOT NULL,
  `bill_price` decimal(10,2) NOT NULL,
  `final_price` decimal(10,2) NOT NULL,
  `payment_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for membership
-- ----------------------------
DROP TABLE IF EXISTS `membership`;
CREATE TABLE `membership` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `vip_level_id` int(11) DEFAULT NULL,
  `gender` varchar(10) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operation_time_record
-- ----------------------------
DROP TABLE IF EXISTS `operation_time_record`;
CREATE TABLE `operation_time_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation_date` datetime NOT NULL,
  `sign_in_time` datetime NOT NULL,
  `sign_in_user` varchar(255) NOT NULL,
  `sign_out_time` datetime NOT NULL,
  `sign_out_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay_by_pay_for
-- ----------------------------
DROP TABLE IF EXISTS `pay_by_pay_for`;
CREATE TABLE `pay_by_pay_for` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pay_by_light_seq` bigint(20) NOT NULL,
  `pay_for_light_seq` bigint(20) NOT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pic_detail
-- ----------------------------
DROP TABLE IF EXISTS `pic_detail`;
CREATE TABLE `pic_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `catalog_id` bigint(20) NOT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `main` bit(1) NOT NULL DEFAULT b'0',
  `url` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `begin_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `recharge_user` varchar(255) NOT NULL,
  `recharge_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for refill_record
-- ----------------------------
DROP TABLE IF EXISTS `refill_record`;
CREATE TABLE `refill_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog_id` bigint(20) NOT NULL,
  `amount` varchar(255) NOT NULL,
  `refill_user` varchar(255) NOT NULL,
  `refill_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for storage_detail
-- ----------------------------
DROP TABLE IF EXISTS `storage_detail`;
CREATE TABLE `storage_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `tag_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for storage_req
-- ----------------------------
DROP TABLE IF EXISTS `storage_req`;
CREATE TABLE `storage_req` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) NOT NULL,
  `tag_number` varchar(255) DEFAULT NULL,
  `store_user` varchar(255) NOT NULL,
  `store_time` datetime NOT NULL,
  `claim_user` varchar(255) DEFAULT NULL,
  `claim_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vip_level
-- ----------------------------
DROP TABLE IF EXISTS `vip_level`;
CREATE TABLE `vip_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
