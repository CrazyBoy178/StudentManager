/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 12/01/2024 19:51:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_choose
-- ----------------------------
DROP TABLE IF EXISTS `tbl_choose`;
CREATE TABLE `tbl_choose`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `sid` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_user_course`(`uid`, `sid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `tbl_choose_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tbl_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_choose_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `tbl_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_college
-- ----------------------------
DROP TABLE IF EXISTS `tbl_college`;
CREATE TABLE `tbl_college`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `collegename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `collegename`(`collegename`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_grade
-- ----------------------------
DROP TABLE IF EXISTS `tbl_grade`;
CREATE TABLE `tbl_grade`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NULL DEFAULT NULL,
  `sid` int(0) NULL DEFAULT NULL,
  `grade` float(10, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  CONSTRAINT `tbl_grade_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tbl_choose` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_grade_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `tbl_choose` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_status
-- ----------------------------
DROP TABLE IF EXISTS `tbl_status`;
CREATE TABLE `tbl_status`  (
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` int(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_subject
-- ----------------------------
DROP TABLE IF EXISTS `tbl_subject`;
CREATE TABLE `tbl_subject`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `subname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `credit` float NOT NULL,
  `college` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teacher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tbl_subject_ibfk_1`(`college`) USING BTREE,
  INDEX `teacher`(`teacher`) USING BTREE,
  CONSTRAINT `tbl_subject_ibfk_1` FOREIGN KEY (`college`) REFERENCES `tbl_college` (`collegename`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_subject_ibfk_2` FOREIGN KEY (`teacher`) REFERENCES `tbl_user` (`name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE,
  INDEX `tbl_user_ibfk_1`(`status`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`status`) REFERENCES `tbl_status` (`status`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_choose_info
-- ----------------------------
DROP VIEW IF EXISTS `v_choose_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_choose_info` AS select `tbl_choose`.`id` AS `id`,`tbl_choose`.`uid` AS `uid`,`tbl_choose`.`sid` AS `sid`,`tbl_subject`.`subname` AS `subname`,`tbl_user`.`username` AS `username`,`tbl_user`.`name` AS `name` from ((`tbl_choose` join `tbl_subject` on(((`tbl_choose`.`sid` = `tbl_subject`.`id`) and (`tbl_choose`.`sid` = `tbl_subject`.`id`) and (`tbl_choose`.`sid` = `tbl_subject`.`id`) and (`tbl_choose`.`sid` = `tbl_subject`.`id`)))) join `tbl_user` on(((`tbl_choose`.`uid` = `tbl_user`.`id`) and (`tbl_choose`.`uid` = `tbl_user`.`id`))));

-- ----------------------------
-- View structure for v_grade_info
-- ----------------------------
DROP VIEW IF EXISTS `v_grade_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_grade_info` AS select `tbl_subject`.`subname` AS `subname`,`tbl_subject`.`credit` AS `credit`,`tbl_subject`.`college` AS `college`,`tbl_subject`.`teacher` AS `teacher`,`tbl_grade`.`id` AS `id`,`tbl_grade`.`uid` AS `uid`,`tbl_grade`.`sid` AS `sid`,`tbl_grade`.`grade` AS `grade`,`tbl_user`.`name` AS `name` from ((`tbl_user` join `tbl_grade` on((`tbl_user`.`id` = `tbl_grade`.`uid`))) join `tbl_subject` on((`tbl_subject`.`id` = `tbl_grade`.`sid`)));

-- ----------------------------
-- View structure for v_subject_info
-- ----------------------------
DROP VIEW IF EXISTS `v_subject_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_subject_info` AS select `tbl_choose`.`id` AS `id`,`tbl_choose`.`uid` AS `uid`,`tbl_choose`.`sid` AS `sid`,`tbl_subject`.`subname` AS `subname`,`tbl_subject`.`credit` AS `credit`,`tbl_subject`.`college` AS `college` from ((`tbl_choose` join `tbl_subject` on(((`tbl_choose`.`sid` = `tbl_subject`.`id`) and (`tbl_choose`.`sid` = `tbl_subject`.`id`)))) join `tbl_user` on(((`tbl_choose`.`uid` = `tbl_user`.`id`) and (`tbl_choose`.`uid` = `tbl_user`.`id`))));

-- ----------------------------
-- View structure for v_teacher
-- ----------------------------
DROP VIEW IF EXISTS `v_teacher`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_teacher` AS select `tbl_subject`.`subname` AS `subname`,`tbl_user`.`id` AS `id`,`tbl_subject`.`teacher` AS `teacher` from (`tbl_subject` join `tbl_user` on(((`tbl_subject`.`teacher` = `tbl_user`.`name`) and (`tbl_subject`.`teacher` = `tbl_user`.`name`))));

-- ----------------------------
-- View structure for v_tgrade_info
-- ----------------------------
DROP VIEW IF EXISTS `v_tgrade_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_tgrade_info` AS select `tbl_user`.`id` AS `tid`,`v_grade_info`.`subname` AS `subname`,`v_grade_info`.`credit` AS `credit`,`v_grade_info`.`college` AS `college`,`v_grade_info`.`teacher` AS `teacher`,`v_grade_info`.`id` AS `id`,`v_grade_info`.`uid` AS `uid`,`v_grade_info`.`sid` AS `sid`,`v_grade_info`.`grade` AS `grade`,`v_grade_info`.`name` AS `name` from (`tbl_user` join `v_grade_info` on((`tbl_user`.`name` = `v_grade_info`.`teacher`)));

-- ----------------------------
-- Triggers structure for table tbl_choose
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_into_grade_trigger`;
delimiter ;;
CREATE TRIGGER `insert_into_grade_trigger` AFTER INSERT ON `tbl_choose` FOR EACH ROW BEGIN
    -- 向 tbl_grade 表插入相同的数据
    INSERT INTO tbl_grade (uid, sid)
    VALUES (NEW.uid, NEW.sid);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tbl_choose
-- ----------------------------
DROP TRIGGER IF EXISTS `delete_from_grade_trigger`;
delimiter ;;
CREATE TRIGGER `delete_from_grade_trigger` BEFORE DELETE ON `tbl_choose` FOR EACH ROW BEGIN
    -- 删除 tbl_grade 表中匹配 uid 和 sid 的记录
    DELETE FROM tbl_grade WHERE uid = OLD.uid AND sid = OLD.sid;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tbl_grade
-- ----------------------------
DROP TRIGGER IF EXISTS `limit_grade`;
delimiter ;;
CREATE TRIGGER `limit_grade` BEFORE INSERT ON `tbl_grade` FOR EACH ROW BEGIN
    IF NEW.grade > 100 THEN
        SET NEW.grade = 100;
    ELSEIF NEW.grade < 0 THEN
        SET NEW.grade = 0;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tbl_grade
-- ----------------------------
DROP TRIGGER IF EXISTS `limit_grade_update`;
delimiter ;;
CREATE TRIGGER `limit_grade_update` BEFORE UPDATE ON `tbl_grade` FOR EACH ROW BEGIN
    IF NEW.grade > 100 THEN
        SET NEW.grade = 100;
    ELSEIF NEW.grade < 0 THEN
        SET NEW.grade = 0;
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
