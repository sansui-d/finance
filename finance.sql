# Host: localhost  (Version 5.7.23)
# Date: 2021-01-08 15:08:47
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "account"
#

CREATE TABLE `account` (
  `actId` int(11) NOT NULL AUTO_INCREMENT,
  `actName` varchar(255) DEFAULT '',
  `actPass` varchar(255) NOT NULL DEFAULT '',
  `houseHolder` int(11) DEFAULT '0',
  `surplus` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`actId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

#
# Data for table "account"
#

INSERT INTO `account` VALUES (1,'ez','abc123',1,'799910'),(2,'liqing','123',2,'2800'),(3,'boot','3423',16,'1000'),(4,'sun','324',8,'1000'),(5,'ali','435345',9,'1000'),(6,'shizi','4355',10,'2000'),(7,'qiangqiang','234',11,'2000'),(8,'bibibibi','525',12,'2000'),(9,'4234','435',13,'2000'),(10,'435','324',14,'3000'),(11,'32342','45346',15,'3000'),(12,'qwe','qwe',17,'3000'),(13,'abc','abc',18,'3000'),(14,'999','999',11,'999'),(21,'bbb','aaa',24,'90'),(22,'abc123','abc123',26,'99900');

#
# Structure for table "admin"
#

CREATE TABLE `admin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `loginId` varchar(255) NOT NULL DEFAULT '',
  `loginPass` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (1,'admin1','123','123'),(2,'admin2','321','321');

#
# Structure for table "budget"
#

CREATE TABLE `budget` (
  `bId` int(11) NOT NULL AUTO_INCREMENT,
  `actId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

#
# Data for table "budget"
#

INSERT INTO `budget` VALUES (1,1,1,'吃喝','100','2021年1月5日'),(2,1,1,'护肤','100','2021年1月5日'),(3,1,1,'衣物','100','2021年1月5日'),(4,1,1,'娱乐','100','2021年1月5日'),(5,1,1,'吃喝','200','2021年1月5日'),(6,1,1,'衣物','100','2021年1月5日'),(7,1,1,'吃喝','10000','2021年1月5日'),(11,21,24,'娱乐','100','2021年1月6日'),(12,21,24,'护肤','10','2021年1月6日'),(13,22,26,'吃喝','100','2021年1月6日'),(14,22,26,'护肤','100','2021年1月6日'),(15,22,26,'衣物','100','2021年1月6日'),(16,22,26,'娱乐','100','2021年1月6日');

#
# Structure for table "debt"
#

CREATE TABLE `debt` (
  `debtId` int(11) NOT NULL AUTO_INCREMENT,
  `actId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `debtName` varchar(255) DEFAULT NULL,
  `debtMoney` varchar(255) DEFAULT NULL,
  `debtTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`debtId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

#
# Data for table "debt"
#

INSERT INTO `debt` VALUES (1,1,1,'保险','养老保险','10000','2030年12月25日'),(2,2,2,'贷款','东东银行','100000','2021年12月25日'),(10,1,1,'保险','意外保险','1000','2022年1月3日'),(12,1,1,'保险','意外保险','1000','2022年1月4日'),(13,1,1,'保险','意外保险','1000','2022年1月4日'),(14,1,1,'保险','意外保险','1000','2022年1月4日'),(15,1,1,'保险','意外保险','1000','2022年1月4日'),(26,1,1,'贷款','东东银行','100000','2022年1月5日'),(27,22,26,'贷款','东东银行','100000','2022年1月6日'),(28,22,26,'保险','意外保险','1000','2022年1月6日');

#
# Structure for table "income"
#

CREATE TABLE `income` (
  `inId` int(11) NOT NULL AUTO_INCREMENT,
  `actId` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) DEFAULT NULL,
  `money` varchar(255) NOT NULL DEFAULT '0',
  `type` varchar(255) DEFAULT NULL,
  `inTime` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`inId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

#
# Data for table "income"
#

INSERT INTO `income` VALUES (1,1,1,'100','兼职','2020年12月11日'),(2,2,2,'200','兼职','2020年12月11日'),(3,1,3,'100','兼职','2020年12月22日'),(4,1,1,'111','兼职','2020年12月22日'),(5,1,1,'100','兼职','2020年12月22日'),(6,1,1,'145','工资','2020年12月22日'),(10,1,3,'143','工资','2020年12月22日'),(11,1,1,'100','工资','2020年12月23日'),(12,1,1,'155','兼职','2020年12月23日'),(13,1,1,'10','工资','2020年12月23日'),(14,1,1,'66','工资','2020年12月23日'),(15,1,1,'2','兼职','2020年12月23日'),(17,1,1,'10','工资','2020年12月23日'),(18,1,1,'100','兼职','2020年12月24日'),(19,1,1,'1','工资','2020年12月24日'),(20,1,1,'100','兼职','2020年12月25日'),(21,1,1,'4','兼职','2020年12月25日'),(22,2,2,'100','工资','2020年12月25日'),(23,1,1,'100','工资','2021年1月4日'),(24,1,1,'100','兼职','2021年1月4日'),(25,1,1,'10','工资','2021年1月4日'),(26,1,1,'100','工资','2021年1月4日'),(27,1,1,'100','工资','2021年1月4日'),(28,1,1,'100','兼职','2021年1月5日'),(29,21,24,'100','兼职','2021年1月6日'),(30,22,26,'1000','兼职','2021年1月6日'),(31,1,1,'100','兼职','2021年1月7日');

#
# Structure for table "insure"
#

CREATE TABLE `insure` (
  `sureId` int(11) NOT NULL AUTO_INCREMENT,
  `sureTitle` varchar(255) DEFAULT NULL,
  `sureContent` varchar(255) DEFAULT NULL,
  `sureMoney` varchar(255) DEFAULT NULL,
  `sureTime` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sureId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "insure"
#

INSERT INTO `insure` VALUES (1,'意外保险','意外事故保险公司按赔偿一半金额','1000',365,'保险'),(2,'养老保险','退休后保险公司每月发放1000元','10000',3650,'保险');

#
# Structure for table "loan"
#

CREATE TABLE `loan` (
  `loanId` int(11) NOT NULL AUTO_INCREMENT,
  `loanName` varchar(255) DEFAULT NULL,
  `loanContent` varchar(255) DEFAULT NULL,
  `loanMoney` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `loanTime` int(11) DEFAULT NULL,
  PRIMARY KEY (`loanId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "loan"
#

INSERT INTO `loan` VALUES (1,'东东银行','东东银行贷款',100000,'贷款',365),(2,'西西银行','西西银行贷款',200000,'贷款',365);

#
# Structure for table "pay"
#

CREATE TABLE `pay` (
  `payId` int(11) NOT NULL AUTO_INCREMENT,
  `actId` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) DEFAULT NULL,
  `money` varchar(255) NOT NULL DEFAULT '0',
  `type` varchar(255) DEFAULT NULL,
  `outTime` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`payId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

#
# Data for table "pay"
#

INSERT INTO `pay` VALUES (1,1,1,'124','吃喝','2020年12月11日'),(2,2,2,'133','护肤','2020年12月11日'),(4,1,1,'200','吃喝','2020年12月24日'),(5,1,1,'2','吃喝','2020年12月24日'),(6,1,1,'100','吃喝','2020年12月24日'),(7,1,1,'20','吃喝','2020年12月25日'),(8,1,1,'30','衣物','2020年12月25日'),(9,1,1,'10','吃喝','2021年1月1日'),(10,1,1,'100','吃喝','2021年1月4日'),(11,1,1,'100','娱乐','2021年1月4日'),(12,1,1,'5688','娱乐','2021年1月4日'),(13,1,1,'100','吃喝','2021年1月4日'),(14,1,1,'100','护肤','2021年1月4日'),(15,1,1,'100','护肤','2021年1月5日'),(16,21,24,'10','护肤','2021年1月6日'),(17,1,1,'100','吃喝','2021年1月6日'),(18,22,26,'100','吃喝','2021年1月6日'),(19,1,1,'100','护肤','2021年1月7日');

#
# Structure for table "user"
#

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `loginId` varchar(255) NOT NULL DEFAULT '',
  `loginPass` varchar(255) DEFAULT '123456',
  `sex` varchar(255) DEFAULT '男',
  `phone` varchar(255) DEFAULT '123456',
  `time` varchar(255) NOT NULL DEFAULT '',
  `actId` int(11) DEFAULT NULL COMMENT '家庭账户id',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'伊泽瑞尔','ez111','123','女','13222','2020年12月11日',1),(2,'李青','a100','123456','男','110','2020年12月11日',2),(3,'安妮','1232','213213','女','444','2020年12月11日',1),(4,'卡兹克','hjk657','123','男','889888','2020年12月11日',1),(5,'雷克萨','aaa','123456','女','10086','2020年12月11日',1),(6,'德莱厄斯','bnm789','213','男','324','2020年12月11日',2),(8,'孙悟空','sun000','21321','女','1923','2020年12月11日',4),(9,'阿狸','ali467','3213','女','10313','2020年12月11日',5),(10,'雷恩加尔','qwe0000','123456','女','142321','2020年12月11日',6),(11,'小强','qiang6666','123','男','123','2020年12月11日',14),(12,'哔哩哔哩','bilibili','54','男','100111','2020年12月11日',8),(13,'layui','pppppp','000','女','44','2020年12月11日',9),(14,'elementui','vbnm56789','111','男','55','2020年12月11日',10),(15,'vue','io67890','222','女','54','2020年12月11日',11),(16,'bootstrap','888888','324','男','45','2020年12月11日',3),(17,'echarts','666','11144','女','56','2020年12月11日',12),(18,'axios','fg9999','123788','男','4554','2020年12月14日',13),(19,'springboot','bb00','123788','女','56','2020年12月14日',2),(20,'uniapp','987654','345','女','45','2020年12月14日',3),(21,'baidu','546546','5465464','男','56','2020年12月14日',4),(22,'海绵宝宝','asd789','435345','女','123456789','2020年12月14日',10),(23,'派大星','zxc123','4324234','女','12580','2020年12月14日',5),(24,'黑鸭','aaaa','1111','男','123','2021年1月5日',21),(25,'user8721','1','1','男','123','2021年1月6日',1),(26,'小黑','abc','abc','女','123456','2021年1月6日',22);
