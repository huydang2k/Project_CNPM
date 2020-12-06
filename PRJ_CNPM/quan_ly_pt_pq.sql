CREATE DATABASE  IF NOT EXISTS `quan_ly_pt_pq` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quan_ly_pt_pq`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: quan_ly_pt_pq
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ds_phat_qua`
--

DROP TABLE IF EXISTS `ds_phat_qua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ds_phat_qua` (
  `maDS` int NOT NULL,
  `suKien` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayTao` date DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  `tongChiPhi` double DEFAULT NULL,
  PRIMARY KEY (`maDS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_phat_qua`
--

LOCK TABLES `ds_phat_qua` WRITE;
/*!40000 ALTER TABLE `ds_phat_qua` DISABLE KEYS */;
INSERT INTO `ds_phat_qua` VALUES (1,'Tết Thiếu nhi 2019','2019-05-20',2,1800000),(2,'Trung thu 2019','2019-08-01',2,1200000),(3,'Giáng sinh 2019','2019-12-15',2,900000),(4,'Tết thiếu nhi 2020','2020-05-21',2,2400000),(5,'Trung thu 2020','2020-08-03',2,2400000),(6,'Giáng sinh 2020','2020-12-01',1,2700000);
/*!40000 ALTER TABLE `ds_phat_qua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ds_phat_thuong`
--

DROP TABLE IF EXISTS `ds_phat_thuong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ds_phat_thuong` (
  `maDS` int NOT NULL,
  `suKien` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayTao` date DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  `tongChiPhi` double DEFAULT NULL,
  PRIMARY KEY (`maDS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_phat_thuong`
--

LOCK TABLES `ds_phat_thuong` WRITE;
/*!40000 ALTER TABLE `ds_phat_thuong` DISABLE KEYS */;
INSERT INTO `ds_phat_thuong` VALUES (1,'Tổng kết kì I năm học 2019-2020','2019-12-20',2,3700000),(2,'Tổng kết kì II năm học 2019-2020','2020-05-01',1,3400000),(3,'Tổng kết kì I năm học 2020-2021','2020-12-01',0,0);
/*!40000 ALTER TABLE `ds_phat_thuong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duoc_nhan_qua`
--

DROP TABLE IF EXISTS `duoc_nhan_qua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `duoc_nhan_qua` (
  `maDS` int NOT NULL,
  `idNhanKhau` int NOT NULL,
  `phanQua` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `mucQua` double DEFAULT NULL,
  `duocXacNhan` bit(1) DEFAULT NULL,
  PRIMARY KEY (`maDS`,`idNhanKhau`),
  KEY `fk_nhanqua_nk` (`idNhanKhau`),
  CONSTRAINT `fk_nhanqua_dsqua` FOREIGN KEY (`maDS`) REFERENCES `ds_phat_qua` (`maDS`),
  CONSTRAINT `fk_nhanqua_nk` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duoc_nhan_qua`
--

LOCK TABLES `duoc_nhan_qua` WRITE;
/*!40000 ALTER TABLE `duoc_nhan_qua` DISABLE KEYS */;
INSERT INTO `duoc_nhan_qua` VALUES (1,35,'Đồ chơi',300000,_binary ''),(1,36,'Đồ chơi',300000,_binary ''),(1,39,'Đồ chơi',300000,_binary ''),(1,40,'Đồ chơi',300000,_binary ''),(1,41,'Đồ chơi',300000,_binary ''),(1,42,'Đồ chơi',300000,_binary ''),(2,35,'Bánh trung thu',200000,_binary ''),(2,36,'Bánh trung thu',200000,_binary ''),(2,39,'Bánh trung thu',200000,_binary ''),(2,40,'Bánh trung thu',200000,_binary ''),(2,41,'Bánh trung thu',200000,_binary ''),(2,42,'Bánh trung thu',200000,_binary ''),(3,35,'Gấu bông, đồ chơi',150000,_binary ''),(3,36,'Gấu bông, đồ chơi',150000,_binary ''),(3,39,'Gấu bông, đồ chơi',150000,_binary ''),(3,40,'Gấu bông, đồ chơi',150000,_binary ''),(3,41,'Gấu bông, đồ chơi',150000,_binary ''),(3,42,'Gấu bông, đồ chơi',150000,_binary ''),(4,35,'Cặp sách, ba lô',400000,_binary ''),(4,36,'Cặp sách, ba lô',400000,_binary ''),(4,39,'Cặp sách, ba lô',400000,_binary ''),(4,40,'Cặp sách, ba lô',400000,_binary ''),(4,41,'Cặp sách, ba lô',400000,_binary ''),(4,42,'Cặp sách, ba lô',400000,_binary ''),(5,35,'Sách, truyện',400000,_binary ''),(5,36,'Sách, truyện',400000,_binary ''),(5,39,'Sách, truyện',400000,_binary ''),(5,40,'Sách, truyện',400000,_binary ''),(5,41,'Sách, truyện',400000,_binary ''),(5,42,'Sách, truyện',400000,_binary ''),(6,35,'Đồ chơi',450000,_binary ''),(6,36,'Đồ chơi',450000,_binary ''),(6,39,'Đồ chơi',450000,_binary ''),(6,40,'Đồ chơi',450000,_binary '\0'),(6,41,'Đồ chơi',450000,_binary '\0'),(6,42,'Đồ chơi',450000,_binary '\0');
/*!40000 ALTER TABLE `duoc_nhan_qua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duoc_nhan_thuong`
--

DROP TABLE IF EXISTS `duoc_nhan_thuong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `duoc_nhan_thuong` (
  `maDS` int NOT NULL,
  `maHS` int NOT NULL,
  `thanhTich` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `xepLoai` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `mucThuong` double DEFAULT NULL,
  `duocXacNhan` bit(1) DEFAULT NULL,
  PRIMARY KEY (`maDS`,`maHS`),
  KEY `fk_nhanqua_hs` (`maHS`),
  CONSTRAINT `fk_nhanqua_hs` FOREIGN KEY (`maHS`) REFERENCES `hoc_sinh` (`maHS`),
  CONSTRAINT `fk_nhanthuong_dsthuong` FOREIGN KEY (`maDS`) REFERENCES `ds_phat_thuong` (`maDS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duoc_nhan_thuong`
--

LOCK TABLES `duoc_nhan_thuong` WRITE;
/*!40000 ALTER TABLE `duoc_nhan_thuong` DISABLE KEYS */;
INSERT INTO `duoc_nhan_thuong` VALUES (1,1,'Học sinh giỏi','A',1000000,_binary ''),(1,2,'Học sinh giỏi','A',1000000,_binary ''),(1,3,'Học sinh giỏi','A',1000000,_binary ''),(1,4,'Học sinh khá','B',700000,_binary ''),(2,1,'Học sinh khá','B',700000,_binary ''),(2,2,'Học sinh giỏi','A',1000000,_binary ''),(2,3,'Học sinh giỏi','A',1000000,_binary ''),(2,4,'Học sinh giỏi','B',700000,_binary '\0');
/*!40000 ALTER TABLE `duoc_nhan_thuong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ho_khau`
--

DROP TABLE IF EXISTS `ho_khau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ho_khau` (
  `ID` int NOT NULL,
  `maHoKhau` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `idChuHo` int DEFAULT NULL,
  `maKhuVuc` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayLap` date DEFAULT NULL,
  `ngayChuyenDi` date DEFAULT NULL,
  `lyDoChuyen` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `nguoiThucHien` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `idChuHo` (`idChuHo`),
  CONSTRAINT `ho_khau_ibfk_1` FOREIGN KEY (`idChuHo`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ho_khau`
--

LOCK TABLES `ho_khau` WRITE;
/*!40000 ALTER TABLE `ho_khau` DISABLE KEYS */;
INSERT INTO `ho_khau` VALUES (13,'TQB002',28,'HN03','Số 2 Tạ Quang Bửu, quần Hai Bà Trưng, Hà Nội','2019-12-08',NULL,NULL,NULL),(14,'TQB001',26,'HN03','Số 1 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','2019-12-08',NULL,NULL,NULL),(15,'TQB003',29,'HN03','Số 3 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','2019-12-08',NULL,NULL,NULL),(16,'TQB004',33,'HN03','Số 4 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','2019-12-08',NULL,NULL,NULL);
/*!40000 ALTER TABLE `ho_khau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_sinh`
--

DROP TABLE IF EXISTS `hoc_sinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_sinh` (
  `maHS` int NOT NULL,
  `idNhanKhau` int NOT NULL,
  `hocVan` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maHS`),
  KEY `fk_hocsinh_nhankhau` (`idNhanKhau`),
  CONSTRAINT `fk_hocsinh_nhankhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_sinh`
--

LOCK TABLES `hoc_sinh` WRITE;
/*!40000 ALTER TABLE `hoc_sinh` DISABLE KEYS */;
INSERT INTO `hoc_sinh` VALUES (1,35,'6/12 chính quy'),(2,36,'1/12 chính quy'),(3,41,'10/12 chính quy'),(4,42,'8/12 chính quy');
/*!40000 ALTER TABLE `hoc_sinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_khau`
--

DROP TABLE IF EXISTS `nhan_khau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_khau` (
  `ID` int NOT NULL,
  `maNhanKhau` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `hoTen` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `namSinh` date DEFAULT NULL,
  `gioiTinh` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `diaChiHienNay` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `trinhDoHocVan` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `TrinhDoChuyenMon` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngheNghiep` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `noiLamViec` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_khau`
--

LOCK TABLES `nhan_khau` WRITE;
/*!40000 ALTER TABLE `nhan_khau` DISABLE KEYS */;
INSERT INTO `nhan_khau` VALUES (26,NULL,'Trịnh Văn An','1990-12-07','Nam','Số 1 Tạ Quang Bưu, Hai Bà Trưng, Hà Nội','12/12 chính quy','Thạc sĩ','Giáo Viên','Trường THCS Chu Văn An'),(27,NULL,'Trần Thanh Duyên','1997-12-23','Nữ','Số 2 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12 chính quy','Thạc sĩ','Nhân viên văn phòng','Công ty ABC'),(28,NULL,'Nguyễn Minh Quân','1995-05-31','Nam','Số 2 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12 chính quy','Thạc sĩ','Kỹ sư','Viettel'),(29,NULL,'Nguyễn Tiến Dũng','1964-06-03','Nam','Số 4 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12 chính quy','Kỹ sư','Phó giám đốc','Công ty EXE'),(30,NULL,'Vũ Mỹ Linh','1965-12-06','Nữ','Số 3 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12','Cử Nhân','Nội trợ','Tại nhà'),(31,NULL,'Nguyễn Tiến Đạt','1990-09-09','Nam','Số 3 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12 chính quy','Kỹ sư','Kỹ sư điện','Công ty điện EVN'),(32,NULL,'Nguyễn Trà My','1997-12-12','Nữ','Số 3 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12 chính quy','Thạc sĩ','Luật sư','Văn phòng luật sư 123'),(33,NULL,'Trần Văn Nam','1980-07-09','Nam','Số 4 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12 chính quy','Tiến sĩ','Giảng viên đại học','Đại học Bách khoa Hà Nội'),(34,NULL,'Nguyễn Minh Tuyết','1985-09-02','Nữ','Số 4 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','12/12 chính quy','Thạc sĩ','Bác sĩ','Bệnh viện quốc tế HJK'),(35,NULL,'Trần Trung Kiên','2008-12-25','Nam','Số 4 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','6/12 chính quy','Không','Học sinh','Trường THCS Chu Văn An'),(36,NULL,'Trần Thúy Ngọc','2013-01-15','Nữ','Số 4 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','1/12 chính quy','Không','Học sinh','Trường tiểu học Chu Văn An'),(37,NULL,'Lý Văn Công','1945-06-04','Nam','Số 5 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','10/12 chính quy','Không','Về hưu','Không'),(38,NULL,'Bùi Thị Hà','1948-02-03','Nữ','Số 5 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','10/12','Không','Nội trợ','Tại nhà'),(39,NULL,'Nguyễn Minh Thành','2019-02-02','Nam','Số 2 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','Không','Không','Chưa đi học','Không'),(40,NULL,'Nguyễn Thanh Hiền','2019-02-02','Nữ','Số 2 Tạ Quang Bửu, quận Hai Bà Trưng, Hà Nội','Không','Không','Chưa đi học','Không'),(41,NULL,'Trịnh Văn Toàn','2005-05-20','Nam','Số 1 Tạ Quang Bưu, Hai Bà Trưng, Hà Nội','10/12 chính quy','Không','Học sinh','Trường THPT Tạ Quang Bửu'),(42,NULL,'Trịnh Minh Anh','2007-06-20','Nữ','Số 1 Tạ Quang Bưu, Hai Bà Trưng, Hà Nội','8/12 chính quy','Không','Học sinh','Trường PT Vinschool');
/*!40000 ALTER TABLE `nhan_khau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanh_vien_cua_ho`
--

DROP TABLE IF EXISTS `thanh_vien_cua_ho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanh_vien_cua_ho` (
  `idNhanKhau` int NOT NULL,
  `idHoKhau` int NOT NULL,
  `quanHeVoiChuHo` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idNhanKhau`,`idHoKhau`),
  KEY `idHoKhau` (`idHoKhau`),
  CONSTRAINT `thanh_vien_cua_ho_ibfk_1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhan_khau` (`ID`),
  CONSTRAINT `thanh_vien_cua_ho_ibfk_2` FOREIGN KEY (`idHoKhau`) REFERENCES `ho_khau` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thanh_vien_cua_ho`
--

LOCK TABLES `thanh_vien_cua_ho` WRITE;
/*!40000 ALTER TABLE `thanh_vien_cua_ho` DISABLE KEYS */;
INSERT INTO `thanh_vien_cua_ho` VALUES (26,14,'Chủ hộ'),(27,13,'Vợ'),(29,15,'Chủ hộ'),(30,15,'Vợ'),(31,15,'Con trai'),(32,15,'Con gái'),(33,16,'Chủ hộ'),(34,16,'Vợ'),(35,16,'Con trai'),(36,16,'Con gái'),(39,13,'Con trai'),(40,13,'Con gái'),(41,14,'Con trai'),(42,14,'Con gái');
/*!40000 ALTER TABLE `thanh_vien_cua_ho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','1');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'quan_ly_pt_pq'
--

--
-- Dumping routines for database 'quan_ly_pt_pq'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-06 18:08:13
