-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: chemist
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dosage`
--

DROP TABLE IF EXISTS `dosage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dosage` (
  `d_dosage_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id дозировки',
  `d_dosage_size` decimal(10,2) NOT NULL COMMENT 'Численное измерение дозировки',
  `d_dosage_unit` varchar(10) NOT NULL COMMENT 'Наименование единицы измерения  в дозировке',
  PRIMARY KEY (`d_dosage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='Таблица  описывает дозировку леварства. Состоит из численного измерения лекарста и названия единиц измерения ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dosage`
--

LOCK TABLES `dosage` WRITE;
/*!40000 ALTER TABLE `dosage` DISABLE KEYS */;
INSERT INTO `dosage` VALUES (1,250.00,'mg'),(2,500.00,'mg'),(3,875.00,'mg'),(4,1000.00,'mg'),(5,457.00,'mg'),(6,2.00,'mg'),(7,5.00,'mg'),(8,400.00,'mg'),(9,125.00,'mg'),(10,2.00,'%'),(11,300.00,'mg'),(12,5.00,'%'),(13,0.10,'%'),(14,0.05,'%'),(15,50.00,'mg'),(16,100.00,'mg'),(17,120.00,'mg'),(18,30.00,'mg'),(19,25.00,'mg'),(20,10.00,'mg');
/*!40000 ALTER TABLE `dosage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `m_medicine_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_name` varchar(45) NOT NULL COMMENT 'Название лекарства',
  `m_price` decimal(10,2) unsigned NOT NULL COMMENT 'Цена единицы лекарства',
  `m_quantity_packages` smallint(5) unsigned NOT NULL COMMENT 'Количество упаковок в наличии в аптеке',
  `m_is_need_prescrip` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Определяется нужен ли рецепт для приобретения данного лекарства',
  `m_quantity_in_pack` smallint(6) DEFAULT NULL,
  `rf_release_form_id` tinyint(4) NOT NULL,
  `p_producer_id` int(11) NOT NULL,
  `d_dosage_id` int(11) DEFAULT NULL,
  `m_analog_id` int(11) DEFAULT NULL,
  `m_unit_in_package` enum('pcs','g','ml') DEFAULT 'pcs',
  `m_is_deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`m_medicine_id`),
  KEY `fk_medicine_release_form1_idx` (`rf_release_form_id`),
  KEY `fk_medicine_producer1_idx` (`p_producer_id`),
  KEY `fk_medicine_dosage1_idx` (`d_dosage_id`),
  KEY `fk_medicine_medicine1_idx` (`m_analog_id`),
  CONSTRAINT `fk_medicine_dosage1` FOREIGN KEY (`d_dosage_id`) REFERENCES `dosage` (`d_dosage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_medicine1` FOREIGN KEY (`m_analog_id`) REFERENCES `medicine` (`m_medicine_id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_producer1` FOREIGN KEY (`p_producer_id`) REFERENCES `producer` (`p_producer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicine_release_form1` FOREIGN KEY (`rf_release_form_id`) REFERENCES `release_form` (`rf_release_form_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (1,'АМОКЛАВ',5.02,20,'\0',18,1,11,2,NULL,'pcs','\0'),(2,'АМОКЛАВ',14.05,15,'\0',14,1,11,3,NULL,'pcs','\0'),(3,'АМОКСИКЛАВ',9.02,17,'\0',15,1,6,1,NULL,'pcs',''),(4,'АМОКСИКЛАВ',12.30,15,'\0',15,1,6,2,1,'pcs','\0'),(5,'АМОКСИКЛАВ',13.30,15,'\0',14,1,6,3,2,'pcs',''),(6,'АМОКСИКЛАВ',10.37,16,'\0',50,11,6,5,NULL,'g','\0'),(8,'СПАЗМАЛГОН',3.80,15,'\0',10,13,13,6,NULL,'ml','\0'),(9,'СПАЗМАЛГОН',5.56,14,'\0',10,13,13,7,NULL,'ml','\0'),(10,'СПАЗМАЛГОН',3.05,11,'\0',10,1,13,NULL,NULL,'pcs','\0'),(11,'СПАЗМАЛГОН',5.34,16,'\0',20,1,13,NULL,NULL,'pcs','\0'),(12,'СПАЗМАТОН',1.65,12,'\0',20,1,9,NULL,NULL,'pcs','\0'),(13,'СУПРАКС',33.75,21,'',6,7,4,8,NULL,'pcs','\0'),(14,'КЛАРИТРОМИЦИН',9.34,17,'',14,1,11,1,16,'pcs','\0'),(15,'КЛАРИТРОМИЦИН',2.18,16,'',10,1,9,1,16,'pcs','\0'),(16,'ФРОМИЛИД',11.12,15,'',14,1,2,1,14,'pcs','\0'),(17,'ФРОМИЛИД',18.05,12,'',60,14,2,9,NULL,'pcs','\0'),(18,'ВИЛЬПРАФЕН',19.80,15,'',10,1,14,2,NULL,'pcs','\0'),(19,'ВИЛЬПРАФЕН',22.15,15,'',10,12,14,4,NULL,'pcs','\0'),(20,'ТРОКСЕВАЗИН',6.26,11,'\0',40,6,12,10,NULL,'g','\0'),(21,'ТРОКСЕВАЗИН',13.93,11,'\0',100,6,12,10,23,'g','\0'),(22,'ТРОКСЕВАЗИН',7.56,11,'\0',50,7,12,11,NULL,'pcs','\0'),(23,'ТРОКСОВАЗОЛ',1.88,18,'\0',50,6,15,NULL,21,'g','\0'),(24,'БЕПАНТЕН',9.55,14,'\0',30,6,8,12,27,'g','\0'),(25,'БЕПАНТЕН',9.43,0,'\0',30,5,8,12,26,'g','\0'),(26,'ДЕКСПАНТЕН',4.96,14,'\0',30,5,15,12,25,'g','\0'),(27,'ДЕКСПАНТЕНОЛ Е',4.49,14,'\0',30,6,15,12,24,'g','\0'),(28,'НАФАЗОЛИН',0.58,20,'\0',10,15,15,13,NULL,'ml','\0'),(29,'НАФАЗОЛИН',0.39,26,'\0',10,15,15,14,NULL,'ml','\0'),(30,'АЗИТРОМИЦИН',9.69,3,'',6,1,11,2,NULL,'pcs','\0'),(31,'СУМАЛЕК',7.32,19,'',6,1,15,2,30,'pcs','\0'),(32,'ЛИНЕКС',6.31,16,'\0',16,7,6,NULL,NULL,'pcs','\0'),(35,'ВЕРОШПИРОН',5.03,10,'\0',20,1,4,NULL,36,'pcs','\0'),(36,'СПИРОНОЛАКТОН-ФТ',4.16,15,'\0',30,1,15,NULL,35,'pcs','\0'),(37,'ВЕРОШПИРОН',14.85,20,'\0',30,7,4,16,NULL,'pcs','\0'),(38,'ВЕРОШПИРОН',10.60,14,'\0',30,7,4,15,NULL,'pcs','\0'),(39,'ГРОПРИНОСИН',25.20,20,'\0',50,1,4,15,41,'pcs','\0'),(40,'ИММУНОЗИН',5.70,16,'\0',20,1,15,15,39,'pcs','\0'),(41,'ИММУНОЗИН',11.42,19,'\0',50,1,15,15,39,'pcs','\0'),(42,'ПАРАЦЕТАМОЛ',0.90,0,'\0',20,16,9,2,NULL,'pcs','\0'),(43,'ПАРАЦЕТАМОЛ',0.34,28,'\0',10,16,9,2,NULL,'pcs','\0'),(44,'ПАРАЦЕТАМОЛ',2.34,30,'\0',100,17,9,17,45,'ml','\0'),(45,'ЭФФЕРАЛГАН',8.28,22,'\0',90,17,16,18,44,'ml','\0'),(46,'КАНЕФРОН',15.15,18,'\0',60,1,17,NULL,NULL,'pcs','\0'),(47,'КАНЕФРОН',12.00,13,'\0',100,3,17,NULL,NULL,'ml','\0'),(48,'АЦЕЦЕКС',1.15,10,'\0',10,11,15,16,49,'g','\0'),(49,'АЦЦ',5.55,10,'\0',20,11,13,16,48,'g','\0'),(52,'ГЛИЦИН',5.41,10,'\0',50,16,18,16,NULL,'pcs','\0'),(53,'ГЛИЦИН ФОРТЕ',3.01,17,'\0',21,16,19,11,NULL,'pcs','\0'),(54,'ИНГАЛИПТ-ФТ',2.88,15,'\0',30,18,15,NULL,NULL,'ml','\0'),(55,'ГРОПРИНОСИН',23.60,22,'\0',50,16,21,2,NULL,'pcs','\0'),(56,'ФУРАМАГ',9.45,24,'\0',30,7,22,19,NULL,'pcs','\0'),(57,'АФЛУБИН',10.80,10,'\0',50,3,14,NULL,NULL,'ml','\0'),(58,'ДЮФАСТОН',23.47,20,'',20,1,24,20,NULL,'pcs','\0');
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `o_order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Номер заказа',
  `u_user_id` int(11) NOT NULL COMMENT 'ID клиента',
  `o_date_created` date NOT NULL COMMENT 'Дата создание заказа',
  `o_status` enum('in processing','approved','paid','ready to receive','received','canceled') NOT NULL DEFAULT 'in processing' COMMENT 'Состояние заказа( в обработке, одобрен, отменен, получен, готов  к получению)\nПри создании заказа статус "в обработке".\nПосле проверки фармацевтом количества, дозировки и рецепта -статус "одобрен".\nПосле оплаты "Оплачен".\nПосле сборки "Готов к получению".\nПосле получения "Получен".\nПри отмене заказа "Отменен"',
  `o_total_sum` decimal(10,2) NOT NULL,
  PRIMARY KEY (`o_order_id`),
  KEY `fk_order_user1_idx` (`u_user_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`u_user_id`) REFERENCES `user` (`u_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,26,'2017-10-26','ready to receive',16.70),(2,2,'2017-10-26','in processing',19.38),(3,26,'2017-10-26','paid',54.24),(4,13,'2017-10-20','ready to receive',23.90),(5,14,'2017-10-26','in processing',31.61),(6,15,'2017-10-28','in processing',33.09),(7,16,'2017-10-27','in processing',21.21),(8,26,'2017-10-25','ready to receive',43.17),(9,18,'2017-10-25','in processing',29.47),(10,19,'2017-10-25','in processing',19.80),(11,20,'2017-10-27','ready to receive',47.33),(12,26,'2017-10-25','in processing',19.22),(14,26,'2018-02-02','paid',22.73),(15,26,'2018-02-02','paid',8.04),(17,26,'2018-02-02','paid',4.50),(18,26,'2018-02-02','paid',9.43),(23,26,'2018-02-08','paid',12.30),(24,26,'2018-02-10','paid',1.84);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `od_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id одной записи в заказе',
  `m_medicine_id` int(11) NOT NULL COMMENT 'Id лекарства',
  `od_quantity` int(11) NOT NULL COMMENT 'Количество в заказе',
  `od_amount` decimal(10,2) NOT NULL COMMENT 'Стоимость необходимого количество лекарства',
  `o_order_id` int(11) NOT NULL,
  `od_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`od_record_id`),
  KEY `fk_order_detail_medicine1_idx` (`m_medicine_id`),
  KEY `fk_order_detail_order1_idx` (`o_order_id`),
  CONSTRAINT `fk_order_detail_medicine1` FOREIGN KEY (`m_medicine_id`) REFERENCES `medicine` (`m_medicine_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_detail_order1` FOREIGN KEY (`o_order_id`) REFERENCES `order` (`o_order_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,14,1,9.34,1,9.34),(2,9,1,5.56,1,5.56),(3,42,2,1.80,1,0.90),(4,30,2,19.38,2,9.69),(6,19,1,22.15,5,22.15),(7,24,1,9.46,5,9.46),(10,13,1,33.75,11,33.75),(11,31,1,7.32,11,7.32),(12,20,1,6.26,11,6.26),(13,13,1,33.75,3,33.75),(14,11,1,5.34,3,5.34),(15,46,1,15.15,3,15.15),(16,15,1,2.18,4,2.18),(17,16,1,11.12,4,11.12),(18,38,1,10.60,4,10.60),(19,49,1,5.55,6,5.55),(20,44,1,2.34,6,2.34),(21,39,1,25.20,6,25.20),(22,30,1,9.69,7,9.69),(23,15,1,2.18,7,2.18),(24,14,1,9.34,7,9.34),(25,17,1,18.05,8,18.05),(26,20,1,6.26,8,6.26),(27,25,2,18.86,8,9.43),(28,19,1,22.15,9,22.15),(29,31,1,7.32,9,7.32),(30,18,1,19.80,10,19.80),(31,2,1,14.05,12,14.05),(32,27,1,4.49,12,4.49),(33,43,2,0.68,12,0.34),(36,5,1,13.30,14,13.30),(37,25,1,9.43,14,9.43),(38,42,3,2.70,15,0.90),(39,11,1,5.34,15,5.34),(41,42,5,4.50,17,0.90),(42,25,1,9.43,18,9.43),(46,4,1,12.30,23,12.30),(47,28,2,1.16,24,0.58),(48,43,2,0.68,24,0.34);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription` (
  `p_prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_user_id` int(11) NOT NULL COMMENT 'Id клиента',
  `p_date_begin` date NOT NULL COMMENT 'Дата начала использования рецепта',
  `p_date_end` date NOT NULL COMMENT 'Дата окончания использования рецепта',
  `u_doctor_id` int(11) NOT NULL,
  `p_status` enum('active','inactive','used','extend') NOT NULL DEFAULT 'active',
  PRIMARY KEY (`p_prescription_id`),
  KEY `fk_recipe_user1_idx` (`u_user_id`),
  KEY `fk_recipe_user2_idx` (`u_doctor_id`),
  CONSTRAINT `fk_recipe_user1` FOREIGN KEY (`u_user_id`) REFERENCES `user` (`u_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_user2` FOREIGN KEY (`u_doctor_id`) REFERENCES `user` (`u_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (1,1,'2017-10-10','2017-10-30',10,'inactive'),(2,2,'2017-10-15','2017-11-15',10,'inactive'),(3,26,'2017-10-15','2018-02-23',10,'active'),(4,13,'2017-10-16','2017-11-15',10,'inactive'),(5,14,'2017-10-16','2017-11-03',10,'inactive'),(6,15,'2017-10-17','2017-11-07',10,'inactive'),(7,16,'2017-10-11','2017-11-06',10,'inactive'),(8,26,'2018-01-03','2018-01-31',4,'extend'),(9,19,'2017-10-20','2017-11-09',4,'inactive'),(10,26,'2017-12-05','2018-02-21',10,'active'),(11,17,'2017-10-21','2017-11-11',4,'inactive'),(12,16,'2017-10-22','2017-11-11',4,'inactive'),(13,15,'2017-10-23','2017-11-13',4,'inactive'),(14,14,'2017-10-24','2017-11-14',4,'inactive'),(15,16,'2017-10-20','2017-11-06',11,'inactive'),(16,15,'2017-10-20','2017-11-07',11,'inactive'),(17,18,'2017-10-21','2017-11-08',11,'inactive'),(18,12,'2017-10-21','2017-11-09',11,'inactive'),(19,12,'2017-10-22','2017-11-10',11,'inactive'),(20,13,'2017-10-23','2017-11-11',11,'inactive'),(21,1,'2017-10-24','2017-11-12',11,'inactive'),(22,2,'2017-10-25','2017-11-13',11,'inactive'),(23,1,'2017-10-21','2017-11-06',4,'inactive'),(24,2,'2017-10-21','2017-11-06',4,'inactive'),(25,26,'2018-01-28','2018-02-28',10,'active'),(26,26,'2018-02-04','2018-02-14',10,'used'),(27,37,'2018-02-04','2018-02-21',10,'active');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_detail`
--

DROP TABLE IF EXISTS `prescription_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription_detail` (
  `pd_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id одной записи в рецепте',
  `pd_quantity_pack` int(11) NOT NULL COMMENT 'Количество упаковок в рецепте',
  `p_prescription_id` int(11) NOT NULL,
  `m_medicine_id` int(11) NOT NULL,
  `pd_status` enum('active','inactive','used','extend') DEFAULT 'inactive',
  PRIMARY KEY (`pd_record_id`),
  KEY `fk_recipe_detail_recipe1_idx` (`p_prescription_id`),
  KEY `fk_recipe_detail_medicine1_idx` (`m_medicine_id`),
  CONSTRAINT `fk_recipe_detail_medicine1` FOREIGN KEY (`m_medicine_id`) REFERENCES `medicine` (`m_medicine_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_detail_recipe1` FOREIGN KEY (`p_prescription_id`) REFERENCES `prescription` (`p_prescription_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_detail`
--

LOCK TABLES `prescription_detail` WRITE;
/*!40000 ALTER TABLE `prescription_detail` DISABLE KEYS */;
INSERT INTO `prescription_detail` VALUES (1,2,1,14,'inactive'),(2,1,2,30,'inactive'),(3,1,2,31,'inactive'),(4,1,3,13,'active'),(5,2,4,15,'inactive'),(6,2,4,16,'inactive'),(7,2,5,18,'inactive'),(8,2,6,19,'inactive'),(9,1,7,30,'inactive'),(10,1,8,31,'extend'),(11,1,8,13,'active'),(12,1,9,18,'inactive'),(13,2,10,19,'active'),(14,1,11,17,'inactive'),(15,1,12,15,'inactive'),(16,2,13,16,'inactive'),(17,1,13,18,'inactive'),(18,1,14,19,'inactive'),(19,2,15,14,'inactive'),(20,1,16,30,'inactive'),(21,1,17,31,'inactive'),(22,1,18,13,'inactive'),(23,1,10,30,'used'),(24,1,26,17,'used'),(25,1,27,30,'active');
/*!40000 ALTER TABLE `prescription_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producer` (
  `p_producer_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(45) NOT NULL COMMENT 'Наименование производителя',
  PRIMARY KEY (`p_producer_id`),
  UNIQUE KEY `p_name_UNIQUE` (`p_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producer`
--

LOCK TABLES `producer` WRITE;
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` VALUES (1,'Bionorica Германия'),(2,'KRKA Словения'),(3,'Natur Produkt Pharma Sp.z.o.o Польша'),(8,'Байер Консьюмер Швейцария'),(12,'Балканфарма-Дупница АД Болгария'),(17,'Бионорика Германия'),(18,'Биотики Россия'),(16,'БМС Франция'),(4,'Гедеон Рихтер Венгрия'),(21,'Гедеон Рихтер Польша'),(13,'Гексал Германия '),(5,'ГСК Фармасьютикалс'),(6,'Лек Словения'),(7,'Лекфарм Беларусь'),(9,'ОАО БЗМП Беларусь'),(22,'Олайнфарм Латвия'),(10,'Польфа Варшава Польша'),(20,'Рекитт бенкизер Великобритания'),(23,'Рихард Биттнер'),(14,'Теммлер  Германия'),(11,'Фармлэнд Беларусь'),(15,'Фармтехнология Беларусь'),(24,'Эбботт Нидерланды'),(19,'Эвалар Россия');
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `release_form`
--

DROP TABLE IF EXISTS `release_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `release_form` (
  `rf_release_form_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `rf_name` varchar(20) NOT NULL COMMENT 'Наименование формы выпуска (например драже, раствор)',
  PRIMARY KEY (`rf_release_form_id`),
  UNIQUE KEY `rf_name_UNIQUE` (`rf_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Таблица содержит варианты форм выпуска лекарств';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release_form`
--

LOCK TABLES `release_form` WRITE;
/*!40000 ALTER TABLE `release_form` DISABLE KEYS */;
INSERT INTO `release_form` VALUES (9,'аэрозоль'),(2,'драже'),(3,'капли внутр'),(4,'капли глазные'),(15,'капли назал'),(7,'капсулы'),(6,'крем'),(5,'мазь'),(11,'порошок внутр'),(13,'р-р инъекц'),(17,'сироп'),(18,'спрей'),(8,'суппозирии ваг'),(10,'суппозитории рект'),(14,'сусп внутр'),(1,'табетки п/о'),(12,'табетки раств'),(16,'таблетки');
/*!40000 ALTER TABLE `release_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `u_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(25) NOT NULL COMMENT 'Имя пользователя',
  `u_surname` varchar(30) NOT NULL COMMENT 'Фамилия пользователя',
  `u_login` varchar(45) NOT NULL COMMENT 'Логин пользователя (уникальный)',
  `u_password` varchar(45) NOT NULL,
  `u_account` decimal(10,2) unsigned DEFAULT NULL COMMENT 'Сумма на счете ,с которого будет производиться оплата (необходимо для клиента)',
  `u_role` enum('client','pharmacist','doctor') NOT NULL COMMENT 'Роль пользователя системы фармацевт или клиент',
  `u_phone` varchar(12) NOT NULL COMMENT 'Телефон пользователя',
  PRIMARY KEY (`u_user_id`),
  UNIQUE KEY `login_UNIQUE` (`u_login`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='Содержит данные о пользователях системы: имя, фамилия, логин, пароль, тип роли,телефон. Для пользователя с ролью- доктор дополнительно имеется ссылка  на таблицу специализация. У других пользователей значение в данном столбце null. Для пользователя с ролью клиент дополнительно имеется значение в поле account. Для остальных пользователей значение в этом поле равно null.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Влад','Сильный','vlad@vlad.by','влад',100.00,'client','375335782147'),(2,'Ольга','Гутар','oly@mail.ru','ольга',50.00,'client','375332478557'),(3,'Михаил','Железняк','mish@mail.ru','миша',NULL,'pharmacist','375336565654'),(4,'Ирина','Карытько','ira@mail.ru','ирина',NULL,'doctor','375331478265'),(8,'Анна','Севрук','ann@ann.by','dcb24cd41f003532c02a1bf307d049d2',NULL,'pharmacist','375336565843'),(9,'Тамара','Иванова','toma@gmail.com','тома',NULL,'pharmacist','375331472589'),(10,'Елена','Витовт','lena@lena.by','6f1ca672751e8b169321f9e6f287cf0c',NULL,'doctor','375331478693'),(11,'Сергей','Ларионов','sergey@tut.by','серый',NULL,'doctor','375332478596'),(12,'Марина','Капитонова','marina@tut.by','марина',80.00,'client','375337892485'),(13,'Светлана','Иванова','sveta@tut.by','света',40.00,'client','375337483152'),(14,'Юрий','Михальчук','ura@tut.by','юра',90.00,'client','375332581474'),(15,'Алексей','Пивоварчик','lesha@tut.by','леха',70.00,'client','375331111111'),(16,'Дарья','Шилова','dasha@dasha.by','даша',101.00,'client','375332222222'),(17,'Анастасия','Савчук','alex@tut.by','сашка',120.00,'client','375333333333'),(18,'Наталья','Игнатюк','nata@tut.by','ната',80.00,'client','375334444444'),(19,'Наталья','Вранчук','natasha@tut.by','наташка',75.00,'client','375335555555'),(20,'Павел','Пынзырь','pasha@pash.by','паша',98.00,'client','375336666666'),(26,'Леша','Лев','lev@lev.by','12c31b6b9cd585d716f5f35c8572d216',371.33,'client','375331234567'),(27,'ЯН','ЯНОВИЧ','A1aaaa','a5dd1a8a6f38a991392072f8cecb208e',50.00,'client','375291234736'),(32,'ANN','ANN','ann@asn.by','dcb24cd41f003532c02a1bf307d049d2',40.00,'client','375333333333'),(33,'Сергей','Сергеев','sery@tut.by','f6b97732bc61e695e300936c59c8ba35',50.00,'client','375295631648'),(34,'Glen','Smith','glen@gmail.com','5f33da5a449a37e7156941f79d488e97',50.00,'client','375296667788'),(35,'Антон','Иванов','anton@tut.by','e7f58e21ce31ac9a0b724e0a373a6835',111.00,'client','375292565241'),(36,'Марк','Попов','mark@mail.ru','24af64656b80862d2f5208918a7783f6',88.00,'client','375292581474'),(37,'Vita','Shark','vita@gmail.com','be2a8e6413f4e97ea23ffc83bf425507',66.00,'client','375447418956');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-10 19:24:26
