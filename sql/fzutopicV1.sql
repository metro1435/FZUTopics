/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : fzutopic

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-04-25 15:18:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adminuser
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser` (
  `adminID` char(9) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(10) NOT NULL,
  `tel` char(11) NOT NULL,
  PRIMARY KEY (`adminID`),
  CONSTRAINT `adminuserCheckLength` CHECK (((length(`adminID`) = 9) and (length(`tel`) = 11)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentID` char(23) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `text` text NOT NULL,
  `likes` int NOT NULL DEFAULT '0',
  `unlikes` int NOT NULL DEFAULT '0',
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `isAnony` int NOT NULL,
  `posterID` char(9) NOT NULL,
  `topicID` char(23) NOT NULL,
  `isReply` int NOT NULL,
  `auditStatus` int NOT NULL,
  PRIMARY KEY (`commentID`),
  KEY `commentFKTopicIDCheck` (`topicID`),
  CONSTRAINT `commentFKTopicIDCheck` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE,
  CONSTRAINT `commentCheckCount` CHECK (((`likes` >= 0) and (`unlikes` >= 0))),
  CONSTRAINT `commentCheckLength` CHECK (((length(`commentID`) = 23) and (length(`posterID`) = 9))),
  CONSTRAINT `commentCheckStatus` CHECK (((`isAnony` in (0,1)) and (`isReply` in (0,1)) and (`auditStatus` in (0,1))))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for commentlikes
-- ----------------------------
DROP TABLE IF EXISTS `commentlikes`;
CREATE TABLE `commentlikes` (
  `userID` char(9) NOT NULL,
  `itemID` char(23) NOT NULL,
  `likedStatus` int NOT NULL,
  PRIMARY KEY (`userID`,`itemID`),
  CONSTRAINT `commentLikesFKuserIDCheck` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE,
  CONSTRAINT `commentlikesCkeckLength` CHECK ((length(`itemID`) = 23))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for courseinfo
-- ----------------------------
DROP TABLE IF EXISTS `courseinfo`;
CREATE TABLE `courseinfo` (
  `courseID` char(7) NOT NULL,
  `courseName` varchar(20) NOT NULL,
  `credits` double(3,0) NOT NULL,
  PRIMARY KEY (`courseID`),
  CONSTRAINT `courseInfoCheckLength` CHECK ((length(`courseID`) = 7))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher` (
  `courseID` char(7) NOT NULL,
  `teacherID` char(7) NOT NULL,
  PRIMARY KEY (`courseID`,`teacherID`),
  KEY `course-teacherFKteacherIDCheck` (`teacherID`),
  CONSTRAINT `course-teacherFKcourseIDCheck` FOREIGN KEY (`courseID`) REFERENCES `courseinfo` (`courseID`) ON DELETE CASCADE,
  CONSTRAINT `course-teacherFKteacherIDCheck` FOREIGN KEY (`teacherID`) REFERENCES `teacherinfo` (`teacherID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ctcomment
-- ----------------------------
DROP TABLE IF EXISTS `ctcomment`;
CREATE TABLE `ctcomment` (
  `commentID` char(23) NOT NULL,
  `text` text NOT NULL,
  `likes` int NOT NULL DEFAULT '0',
  `unlikes` int NOT NULL DEFAULT '0',
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `isAnony` int NOT NULL,
  `userID` char(9) NOT NULL,
  `commentItemID` char(7) NOT NULL,
  `auditStatus` int NOT NULL,
  PRIMARY KEY (`commentID`),
  KEY `CTCommentFKuserIDCheck` (`userID`),
  CONSTRAINT `CTCommentFKuserIDCheck` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `CTCommentCheckCount` CHECK (((`likes` >= 0) and (`unlikes` >= 0))),
  CONSTRAINT `CTCommentCheckLength` CHECK ((length(`commentID`) = 23)),
  CONSTRAINT `CTCommentCheckStatus` CHECK (((`isAnony` in (0,1)) and (`auditStatus` in (0,1))))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for favlist
-- ----------------------------
DROP TABLE IF EXISTS `favlist`;
CREATE TABLE `favlist` (
  `favlistID` char(23) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userID` char(9) NOT NULL,
  `name` varchar(10) NOT NULL,
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`favlistID`),
  KEY `favlistFKuserIDCheck` (`userID`),
  CONSTRAINT `favlistFKuserIDCheck` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE,
  CONSTRAINT `favlistCheckLength` CHECK ((length(`favlistID`) = 23))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for favlist_item
-- ----------------------------
DROP TABLE IF EXISTS `favlist_item`;
CREATE TABLE `favlist_item` (
  `favlistID` char(23) NOT NULL,
  `collectedID` char(24) NOT NULL,
  PRIMARY KEY (`favlistID`,`collectedID`),
  CONSTRAINT `favlist-itemFKfavlistIDCheck` FOREIGN KEY (`favlistID`) REFERENCES `favlist` (`favlistID`) ON DELETE CASCADE,
  CONSTRAINT `favlist-itemCkeckLength` CHECK ((length(`collectedID`) = 24))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `newsID` char(24) NOT NULL,
  `adminID` char(9) NOT NULL,
  `title` tinytext NOT NULL,
  `text` text NOT NULL,
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`newsID`),
  KEY `NewsForeignKeyCheck` (`adminID`),
  CONSTRAINT `NewsForeignKeyCheck` FOREIGN KEY (`adminID`) REFERENCES `adminuser` (`adminID`) ON DELETE CASCADE,
  CONSTRAINT `newsCheckLength` CHECK ((length(`newsID`) = 24))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `replyID` char(23) NOT NULL,
  `text` text NOT NULL,
  `likes` int NOT NULL DEFAULT '0',
  `unlikes` int NOT NULL DEFAULT '0',
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `isAnony` int NOT NULL,
  `userID` char(9) NOT NULL,
  `commentID` char(23) NOT NULL,
  `auditStatus` int NOT NULL,
  PRIMARY KEY (`replyID`),
  KEY `replyFKuserIDCheck` (`userID`),
  KEY `replyFKcommentIDCheck` (`commentID`),
  CONSTRAINT `replyFKcommentIDCheck` FOREIGN KEY (`commentID`) REFERENCES `comment` (`commentID`) ON DELETE CASCADE,
  CONSTRAINT `replyFKuserIDCheck` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE,
  CONSTRAINT `replyCheckCount` CHECK (((`likes` >= 0) and (`unlikes` >= 0))),
  CONSTRAINT `replyCheckLength` CHECK ((length(`replyID`) = 23)),
  CONSTRAINT `replyCheckStatus` CHECK (((`isAnony` in (0,1)) and (`auditStatus` in (0,1))))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagID` char(5) NOT NULL,
  `name` varchar(20) NOT NULL,
  `times` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`tagID`),
  CONSTRAINT `tagCheckCount` CHECK ((`times` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo` (
  `teacherID` char(7) NOT NULL,
  `teacherName` varchar(40) NOT NULL,
  `info` text,
  `tel` char(11) DEFAULT NULL,
  `QQ` varchar(10) DEFAULT NULL,
  `college` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`teacherID`),
  CONSTRAINT `teacherInfoCheckLength` CHECK (((length(`teacherID`) = 7) and (length(`tel`) = 11)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topicID` char(24) NOT NULL,
  `title` tinytext NOT NULL,
  `text` text NOT NULL,
  `likes` int NOT NULL DEFAULT '0',
  `unlikes` int NOT NULL DEFAULT '0',
  `userID` char(9) NOT NULL,
  `time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `isAnony` int NOT NULL,
  `views` int NOT NULL DEFAULT '0',
  `heats` int NOT NULL DEFAULT '0',
  `commentCount` int NOT NULL DEFAULT '0',
  `auditStatus` int NOT NULL,
  PRIMARY KEY (`topicID`),
  KEY `topicFKuserIDCheck` (`userID`),
  CONSTRAINT `topicFKuserIDCheck` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `check_count` CHECK (((`likes` >= 0) and (`unlikes` >= 0) and (`views` >= 0) and (`heats` >= 0) and (`commentCount` >= 0))),
  CONSTRAINT `check_ID` CHECK ((length(`topicID`) = 24)),
  CONSTRAINT `check_status` CHECK (((`isAnony` in (0,1)) and (`auditStatus` in (0,1))))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topiclikes
-- ----------------------------
DROP TABLE IF EXISTS `topiclikes`;
CREATE TABLE `topiclikes` (
  `userID` char(9) NOT NULL,
  `topicID` char(24) NOT NULL,
  `likedStatus` int NOT NULL,
  PRIMARY KEY (`userID`,`topicID`),
  KEY `topicLikesFKtopicIDCheck` (`topicID`),
  CONSTRAINT `topicLikesFKtopicIDCheck` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE,
  CONSTRAINT `topicLikesFKuserIDCheck` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topic_tag
-- ----------------------------
DROP TABLE IF EXISTS `topic_tag`;
CREATE TABLE `topic_tag` (
  `tagID` char(5) NOT NULL,
  `topicID` char(24) NOT NULL,
  PRIMARY KEY (`tagID`,`topicID`),
  KEY `topic-tagFKTopicIDCheck` (`topicID`),
  CONSTRAINT `topic-tagFKtagIDCheck` FOREIGN KEY (`tagID`) REFERENCES `tag` (`tagID`) ON DELETE CASCADE,
  CONSTRAINT `topic-tagFKTopicIDCheck` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` char(9) NOT NULL,
  `password` varchar(16) NOT NULL,
  `sex` char(1) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `college` varchar(20) NOT NULL,
  `major` varchar(20) NOT NULL,
  `icon` text,
  `exp` int NOT NULL,
  `level` int NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `check_length` CHECK ((length(`userID`) = 9)),
  CONSTRAINT `check_sex` CHECK ((`sex` in (_utf8mb4'男',_utf8mb4'女')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
