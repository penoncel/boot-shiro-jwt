/*
SQLyog v10.2 
MySQL - 5.5.28 : Database - web-mastar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`web-mastar` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `web-mastar`;

/*Table structure for table `nuomansitrad` */

DROP TABLE IF EXISTS `nuomansitrad`;

CREATE TABLE `nuomansitrad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mercode` varchar(255) DEFAULT NULL COMMENT '交易商户号',
  `car` varchar(255) DEFAULT NULL COMMENT '卡号',
  `money` double(10,5) DEFAULT NULL COMMENT '金额',
  `traddate` varchar(255) DEFAULT NULL COMMENT '日期',
  `tradtime` varchar(255) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nuomansitrad` */

/*Table structure for table `web_loginrecord` */

DROP TABLE IF EXISTS `web_loginrecord`;

CREATE TABLE `web_loginrecord` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `nikename` varchar(255) DEFAULT NULL COMMENT '昵称',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `device` varchar(255) DEFAULT NULL COMMENT '设备',
  `device_type` varchar(255) DEFAULT NULL COMMENT '设备类型',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
  `input_time` varchar(255) DEFAULT NULL COMMENT '登入时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='登入记录表';

/*Data for the table `web_loginrecord` */

insert  into `web_loginrecord`(`Id`,`username`,`nikename`,`ip`,`device`,`device_type`,`browser`,`input_time`) values (1,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-02-10 13:43:51'),(2,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-02-10 13:51:00'),(3,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-02-10 13:53:31'),(4,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-02-10 14:02:04'),(5,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-02-10 14:08:01'),(6,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 14:19:17'),(7,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-02-10 14:22:28'),(8,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 14:23:13'),(9,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 14:49:43'),(10,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 15:01:06'),(11,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 15:16:30'),(12,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 15:16:34'),(13,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 15:16:45'),(14,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 15:33:23'),(15,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-02-10 16:01:39'),(16,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:03:40'),(17,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:24:43'),(18,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:29:10'),(19,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:29:39'),(20,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:32:21'),(21,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:33:26'),(22,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:34:54'),(23,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:35:01'),(24,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:35:05'),(25,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:38:00'),(26,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:38:05'),(27,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:39:00'),(28,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:39:05'),(29,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:48:13'),(30,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:49:34'),(31,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:49:39'),(32,'[test','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:50:09'),(33,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:50:43'),(34,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:50:47'),(35,'[userAdmin','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:52:05'),(36,'[test','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:52:10'),(37,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:58:35'),(38,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 16:58:41'),(39,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 17:08:20'),(40,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-10 17:08:25'),(41,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-11 09:16:53'),(42,'[test','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-11 09:17:33'),(43,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-11 09:17:58'),(44,'[zq','','127.0.0.1','WINDOWS_10','72.0','FIREFOX7','2020-02-11 09:27:44'),(45,'[zq','','127.0.0.1','WINDOWS_10','73.0','FIREFOX7','2020-03-05 10:45:30'),(46,'[zq','','223.104.210.215','MAC_OS_X_IPHONE','13.0','MOBILE_SAFARI','2020-03-05 11:01:53'),(47,'[zq','','127.0.0.1','WINDOWS_10','53.0.2785.116','CHROME','2020-03-05 12:10:45'),(48,'[zq','','127.0.0.1','WINDOWS_10','53.0.2785.116','CHROME','2020-03-05 12:10:49'),(49,'[zq','','127.0.0.1','WINDOWS_10','73.0','FIREFOX7','2020-03-19 15:22:28'),(50,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 09:29:43'),(51,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 09:32:09'),(52,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 09:32:57'),(53,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 09:33:47'),(54,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 09:37:33'),(55,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 10:22:34'),(56,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 10:31:13'),(57,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-11 10:35:38'),(58,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-05-11 11:07:36'),(59,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-05-12 10:52:51'),(60,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-05-12 14:34:03'),(61,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-05-12 14:41:29'),(62,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-05-12 15:55:32'),(63,'[zq','','127.0.0.1','WINDOWS_10','70.0.3538.25','CHROME','2020-05-13 16:48:38'),(64,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:17:36'),(65,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:24:24'),(66,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:25:50'),(67,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:27:02'),(68,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:27:09'),(69,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:27:13'),(70,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:28:14'),(71,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 10:33:26'),(72,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-18 11:08:13'),(73,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-19 10:14:42'),(74,'[zq','','127.0.0.1','WINDOWS_10','75.0','FIREFOX7','2020-05-19 10:16:41');

/*Table structure for table `web_loginuser` */

DROP TABLE IF EXISTS `web_loginuser`;

CREATE TABLE `web_loginuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `phone` varchar(255) DEFAULT NULL COMMENT '账号(手机号)',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `icon` varchar(255) DEFAULT NULL COMMENT '头像',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) DEFAULT NULL COMMENT '地区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统登入用户表';

/*Data for the table `web_loginuser` */

/*Table structure for table `web_menu` */

DROP TABLE IF EXISTS `web_menu`;

CREATE TABLE `web_menu` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `parents` varchar(255) DEFAULT NULL COMMENT '父类菜单标识',
  `css` varchar(255) DEFAULT NULL COMMENT '菜单图标ioc',
  `href` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `lever` varchar(255) DEFAULT NULL COMMENT '菜单级别',
  `note` varchar(255) DEFAULT NULL COMMENT '说明',
  `type` varchar(255) DEFAULT NULL COMMENT '类型0目录，1菜单，2按钮',
  `superior` varchar(255) DEFAULT NULL COMMENT '上级',
  `lowerlevel` varchar(255) DEFAULT NULL COMMENT '下级',
  `belogin` int(11) DEFAULT '1' COMMENT '所属平台1后台2前台3App',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `web_menu` */

insert  into `web_menu`(`Id`,`menu_name`,`parents`,`css`,`href`,`lever`,`note`,`type`,`superior`,`lowerlevel`,`belogin`) values (1,'系统设置','0','layui-icon-snowflake','#','1','系统设置','0','01000000','01010000',1),(2,'菜单列表','1','layui-icon-layer','WebMenu/list','2','','1','01000000','01020000',1),(3,'添加目录','2','','WebMenu/addMenu','3','','2','','',1),(4,'修改目录','2','','WebMenu/edit','3','','2','','',1),(5,'删除目录','2','','WebMenu/del','3','','2','','',1),(6,'全部展开','2','','WebMenu/openmenu','3','','2','','',1),(7,'全部折叠','2','','WebMenu/closeMenu','3','','2','','',1),(8,'角色管理','1','layui-icon-friends','WebRole/list','2','','1','01000000','01030000',1),(9,'添加角色','8','','WebRole/add','3','','2','','',1),(10,'编辑角色','8','','WebRole/edit','3','','2','','',1),(11,'设置权限','8','','WebRole/power','3','','2','','',1),(12,'删除角色','8','','WebRole/del','3','','2','','',1),(13,'登入记录','1','layui-icon-search','WebLoginrecord/list','2','','1','01000000','01040000',1),(14,'系统用户','0','layui-icon-username','#','1','','0','02000000','02010000',1),(15,'用户管理','14','layui-icon-user','WebUser/list','2','','1','02000000','02020000',1),(16,'添加用户','15','','WebUser/add','3','','2','','',1),(17,'编辑用户','15','','WebUser/edit','3','','2','','',1),(18,'删除用户','15','','WebUser/del','3','','2','','',1),(19,'修改用户状态','15','','WebUser/stauts','3','','2','','',1);

/*Table structure for table `web_role` */

DROP TABLE IF EXISTS `web_role`;

CREATE TABLE `web_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL COMMENT '角色',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `add_time` varchar(255) DEFAULT NULL COMMENT '添加时间',
  `edit_time` varchar(255) DEFAULT NULL COMMENT '操作时间',
  `note` varchar(255) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*Data for the table `web_role` */

insert  into `web_role`(`Id`,`role`,`name`,`add_time`,`edit_time`,`note`) values (8,'sys','系統管理員','2020-02-10 15:18:21','2020-02-10 16:52:54',''),(9,'userAdmin','用户管理员','2020-02-10 16:51:30','2020-02-10 16:52:49','');

/*Table structure for table `web_role_permission` */

DROP TABLE IF EXISTS `web_role_permission`;

CREATE TABLE `web_role_permission` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='web用户权限表';

/*Data for the table `web_role_permission` */

insert  into `web_role_permission`(`Id`,`role_id`,`menu_id`) values (1,6,1),(2,6,2),(3,6,3),(4,6,4),(5,6,5),(6,6,6),(7,6,7),(8,6,8),(9,6,9),(10,6,10),(11,6,11),(12,6,12),(13,6,13),(14,6,14),(15,6,15),(16,6,16),(17,6,17),(18,6,18),(19,6,19),(20,8,1),(21,8,2),(22,8,3),(23,8,4),(24,8,5),(25,8,6),(26,8,7),(27,8,8),(28,8,9),(29,8,10),(30,8,11),(31,8,12),(32,8,13),(33,8,14),(34,8,15),(35,8,16),(36,8,17),(37,8,18),(38,8,19),(39,3,1),(40,3,2),(41,3,3),(42,3,4),(43,3,5),(44,3,6),(45,3,7),(46,3,8),(47,3,9),(48,3,10),(49,3,11),(50,3,12),(51,3,13),(52,3,14),(53,3,15),(54,3,16),(55,3,17),(56,3,18),(57,3,19),(58,9,1),(59,9,8),(60,9,9),(61,9,10),(62,9,11),(63,9,12),(64,9,14),(65,9,15),(66,9,16),(67,9,17),(68,9,18),(69,9,19);

/*Table structure for table `web_user` */

DROP TABLE IF EXISTS `web_user`;

CREATE TABLE `web_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `userpass` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `lever` varchar(255) DEFAULT NULL COMMENT '级别（1管理员、2普通用户）',
  `status` int(11) DEFAULT '1' COMMENT '用户状态1启用2禁用',
  `salt` varchar(255) DEFAULT NULL,
  `creat_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `login_errors` int(11) DEFAULT '0' COMMENT '登入错误次数',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `web_user` */

insert  into `web_user`(`Id`,`username`,`userpass`,`nickname`,`phone`,`lever`,`status`,`salt`,`creat_time`,`login_errors`) values (1,'15701556037','01a1a22a6c17cafc32102357911edb48','赵旗','15701556037','8',1,'f902353066c4a8203a742a4978dc92f2','2020/1/6 11:08:42',0),(4,'15701556038','01a1a22a6c17cafc32102357911edb48','15701556037','15701556037','9',1,NULL,'2020-02-10 16:49:54',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
