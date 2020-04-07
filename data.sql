CREATE DATABASE  IF NOT EXISTS `demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `demo`;

--
-- Table structure for table `user``
--
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL COMMENT 'user id',
  `user_code` varchar(60) DEFAULT NULL COMMENT 'user code',
  `user_name` varchar(255) DEFAULT NULL COMMENT 'user name',
  `password` varchar(255) DEFAULT NULL COMMENT 'password',
  `state` varchar(1) DEFAULT NULL COMMENT 'A valid, X invalid, B not active',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='user';
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `sequences`
--
DROP TABLE IF EXISTS `sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequences` (
                                 `sequence_name` varchar(80) NOT NULL COMMENT '序列名称',
                                 `increment_by` int(11) NOT NULL COMMENT '序列的步长',
                                 `current_value` bigint(20) NOT NULL COMMENT '当前值',
                                 `min_value` bigint(20) DEFAULT NULL COMMENT '最小值',
                                 `max_value` bigint(20) DEFAULT NULL COMMENT '序列的最大值',
                                 `comments` varchar(200) DEFAULT NULL COMMENT '描述使用序列的表名称和列名称，方便维护'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定义系统用到的序列';
/*!40101 SET character_set_client = @saved_cs_client */;




/*!50003 DROP FUNCTION IF EXISTS `currval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `currval`(a_seq_name VARCHAR(55)) RETURNS bigint(20)
begin
    declare seq_val bigint;
    set seq_val = -1;
    if EXISTS(select 1 from sequences holdlock where sequence_name=a_seq_name)
    then
        select  current_value INTO seq_val from sequences where sequence_name = a_seq_name;
    else
        #set seq_val=0;
        #故意让之报错，因为不存在当前的seq_name
        #INSERT into sequences(sequence_name,increment_by,current_value,comments) VALUES(a_seq_name);
        SIGNAL SQLSTATE '42000' set MESSAGE_TEXT='error:1001,Query was empty,sequence name not found ';
    end if;
    return seq_val;
end ;;
DELIMITER ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `nextval`(a_seq_name VARCHAR(55)) RETURNS bigint(20)
begin
    declare seq_val bigint;
    declare max_val bigint;
    set seq_val = -1;
    if EXISTS(select 1 from sequences holdlock where sequence_name=a_seq_name)
    then
        select  current_value + increment_by,max_value INTO seq_val,max_val from sequences where sequence_name = a_seq_name;
        if seq_val>max_val
        then
            #故意让之报错，因为增加后的序列值大于最大序列值
            #INSERT into sequences(sequence_name,increment_by,current_value,comments) VALUES(a_seq_name);
            SIGNAL SQLSTATE '42000' set MESSAGE_TEXT='error:1000,sequence beyond the max value ';
        else
            update sequences set current_value = seq_val where sequence_name = a_seq_name;
        END IF;

    else
        #                      set seq_val = 1;
        #                      INSERT into sequences(sequence_name,increment_by,current_value,comments) VALUES(a_seq_name,seq_val,seq_val,'自动添加的序列');
        #故意让之报错，因为不存在当前的seq_name
        #INSERT into sequences(sequence_name,increment_by,current_value,comments) VALUES(a_seq_name);
        SIGNAL SQLSTATE '42000' set MESSAGE_TEXT='error:1001,Query was empty,sequence name not found ';
    end if;
    return seq_val;
end ;;
DELIMITER ;


--
-- Dumping data for table `tfm_sequences`
--

LOCK TABLES `sequences` WRITE;
/*!40000 ALTER TABLE `sequences` DISABLE KEYS */;
INSERT INTO `sequences` VALUES ('user_id_seq',1,0,1,999999999,'');
/*!40000 ALTER TABLE `sequences` ENABLE KEYS */;
UNLOCK TABLES;



insert ignore into user (user_id,user_code,user_name,password,state)
values((select nextval('user_id_seq') from dual), 'admin', 'Admin', '-6ptl2bvba4s9rhdb1uje4ns5f81b92ej','A');