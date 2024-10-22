--------------------------------------------------------
--  파일이 생성됨 - 월요일-5월-20-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence BATCH_JOB_EXECUTION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."BATCH_JOB_EXECUTION_SEQ"  MINVALUE 0 MAXVALUE 9223372036854775807 INCREMENT BY 1 START WITH 300 CACHE 20 ORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence BATCH_JOB_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."BATCH_JOB_SEQ"  MINVALUE 0 MAXVALUE 9223372036854775807 INCREMENT BY 1 START WITH 160 CACHE 20 ORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence BATCH_STEP_EXECUTION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."BATCH_STEP_EXECUTION_SEQ"  MINVALUE 0 MAXVALUE 9223372036854775807 INCREMENT BY 1 START WITH 200 CACHE 20 ORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence CONTAINER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."CONTAINER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence FOOD_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."FOOD_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 46551 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence FTYPE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."FTYPE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence MEMBER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."MEMBER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 7001 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence MEMBER_TOKEN_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."MEMBER_TOKEN_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 8051 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NOTIFICATION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."NOTIFICATION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 7001 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence REFRESH_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."REFRESH_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence REFRIGERATOR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SSAFY"."REFRIGERATOR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 1001 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table BATCH_JOB_EXECUTION
--------------------------------------------------------

  CREATE TABLE "SSAFY"."BATCH_JOB_EXECUTION" 
   (	"JOB_EXECUTION_ID" NUMBER(19,0), 
	"VERSION" NUMBER(19,0), 
	"JOB_INSTANCE_ID" NUMBER(19,0), 
	"CREATE_TIME" TIMESTAMP (9), 
	"START_TIME" TIMESTAMP (9) DEFAULT NULL, 
	"END_TIME" TIMESTAMP (9) DEFAULT NULL, 
	"STATUS" VARCHAR2(10 CHAR), 
	"EXIT_CODE" VARCHAR2(2500 CHAR), 
	"EXIT_MESSAGE" VARCHAR2(2500 CHAR), 
	"LAST_UPDATED" TIMESTAMP (9)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table BATCH_JOB_EXECUTION_CONTEXT
--------------------------------------------------------

  CREATE TABLE "SSAFY"."BATCH_JOB_EXECUTION_CONTEXT" 
   (	"JOB_EXECUTION_ID" NUMBER(19,0), 
	"SHORT_CONTEXT" VARCHAR2(2500 CHAR), 
	"SERIALIZED_CONTEXT" CLOB
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" 
 LOB ("SERIALIZED_CONTEXT") STORE AS SECUREFILE (
  TABLESPACE "SSAFY" ENABLE STORAGE IN ROW 4000 CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES 
  STORAGE(INITIAL 262144 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table BATCH_JOB_EXECUTION_PARAMS
--------------------------------------------------------

  CREATE TABLE "SSAFY"."BATCH_JOB_EXECUTION_PARAMS" 
   (	"JOB_EXECUTION_ID" NUMBER(19,0), 
	"PARAMETER_NAME" VARCHAR2(100 CHAR), 
	"PARAMETER_TYPE" VARCHAR2(100 CHAR), 
	"PARAMETER_VALUE" VARCHAR2(2500 CHAR), 
	"IDENTIFYING" CHAR(1 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table BATCH_JOB_INSTANCE
--------------------------------------------------------

  CREATE TABLE "SSAFY"."BATCH_JOB_INSTANCE" 
   (	"JOB_INSTANCE_ID" NUMBER(19,0), 
	"VERSION" NUMBER(19,0), 
	"JOB_NAME" VARCHAR2(100 CHAR), 
	"JOB_KEY" VARCHAR2(32 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table BATCH_STEP_EXECUTION
--------------------------------------------------------

  CREATE TABLE "SSAFY"."BATCH_STEP_EXECUTION" 
   (	"STEP_EXECUTION_ID" NUMBER(19,0), 
	"VERSION" NUMBER(19,0), 
	"STEP_NAME" VARCHAR2(100 CHAR), 
	"JOB_EXECUTION_ID" NUMBER(19,0), 
	"CREATE_TIME" TIMESTAMP (9), 
	"START_TIME" TIMESTAMP (9) DEFAULT NULL, 
	"END_TIME" TIMESTAMP (9) DEFAULT NULL, 
	"STATUS" VARCHAR2(10 CHAR), 
	"COMMIT_COUNT" NUMBER(19,0), 
	"READ_COUNT" NUMBER(19,0), 
	"FILTER_COUNT" NUMBER(19,0), 
	"WRITE_COUNT" NUMBER(19,0), 
	"READ_SKIP_COUNT" NUMBER(19,0), 
	"WRITE_SKIP_COUNT" NUMBER(19,0), 
	"PROCESS_SKIP_COUNT" NUMBER(19,0), 
	"ROLLBACK_COUNT" NUMBER(19,0), 
	"EXIT_CODE" VARCHAR2(2500 CHAR), 
	"EXIT_MESSAGE" VARCHAR2(2500 CHAR), 
	"LAST_UPDATED" TIMESTAMP (9)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table BATCH_STEP_EXECUTION_CONTEXT
--------------------------------------------------------

  CREATE TABLE "SSAFY"."BATCH_STEP_EXECUTION_CONTEXT" 
   (	"STEP_EXECUTION_ID" NUMBER(19,0), 
	"SHORT_CONTEXT" VARCHAR2(2500 CHAR), 
	"SERIALIZED_CONTEXT" CLOB
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" 
 LOB ("SERIALIZED_CONTEXT") STORE AS SECUREFILE (
  TABLESPACE "SSAFY" ENABLE STORAGE IN ROW 4000 CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES 
  STORAGE(INITIAL 262144 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table CONTAINER
--------------------------------------------------------

  CREATE TABLE "SSAFY"."CONTAINER" 
   (	"FOOD_ID" NUMBER, 
	"QR_ID" VARCHAR2(40 BYTE), 
	"TEMPERATURE" NUMBER(3,1), 
	"HUMIDITY" NUMBER(4,1), 
	"PH" NUMBER(5,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table FOOD
--------------------------------------------------------

  CREATE TABLE "SSAFY"."FOOD" 
   (	"FOOD_ID" NUMBER, 
	"REFRIGERATOR_ID" NUMBER, 
	"NAME" VARCHAR2(100 BYTE), 
	"EXPIRATION_DATE" TIMESTAMP (6), 
	"REFRESH_ID" NUMBER, 
	"FTYPE_ID" NUMBER DEFAULT 3, 
	"DEL_YN" NUMBER(1,0) DEFAULT 0, 
	"REG_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"REG_USER_SEQ" NUMBER, 
	"MOD_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"MOD_USER_SEQ" NUMBER, 
	"MISS_REGISTERED" NUMBER(1,0) DEFAULT 0, 
	"IMAGE" VARCHAR2(500 BYTE), 
	"PREV_REFRESH" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table FTYPE
--------------------------------------------------------

  CREATE TABLE "SSAFY"."FTYPE" 
   (	"FTYPE_ID" NUMBER, 
	"NAME" VARCHAR2(10 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table MEMBER
--------------------------------------------------------

  CREATE TABLE "SSAFY"."MEMBER" 
   (	"MEMBER_ID" NUMBER, 
	"ID" VARCHAR2(24 BYTE), 
	"PASSWORD" VARCHAR2(100 BYTE), 
	"REFRIGERATOR_ID" NUMBER, 
	"DEL_YN" NUMBER(1,0) DEFAULT 0, 
	"REG_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"REG_USER_SEQ" NUMBER, 
	"MOD_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"MOD_USER_SEQ" NUMBER, 
	"REFRESH_TOKEN" VARCHAR2(1000 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table MEMBER_TOKEN
--------------------------------------------------------

  CREATE TABLE "SSAFY"."MEMBER_TOKEN" 
   (	"MEMBER_TOKEN_ID" NUMBER, 
	"MEMBER_ID" NUMBER, 
	"TOKEN" VARCHAR2(300 BYTE), 
	"DEL_YN" NUMBER(1,0) DEFAULT 0, 
	"REG_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"REG_USER_SEQ" NUMBER, 
	"MOD_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"MOD_USER_SEQ" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table NOTIFICATION
--------------------------------------------------------

  CREATE TABLE "SSAFY"."NOTIFICATION" 
   (	"NOTIFICATION_ID" NUMBER, 
	"REFRIGERATOR_ID" NUMBER, 
	"CATEGORY" VARCHAR2(10 BYTE), 
	"MESSAGE" VARCHAR2(300 BYTE), 
	"DEL_YN" NUMBER(1,0) DEFAULT 0, 
	"REG_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"REG_USER_SEQ" NUMBER, 
	"MOD_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"MOD_USER_SEQ" NUMBER, 
	"TITLE" VARCHAR2(300 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table REFRESH
--------------------------------------------------------

  CREATE TABLE "SSAFY"."REFRESH" 
   (	"REFRESH_ID" NUMBER, 
	"NAME" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Table REFRIGERATOR
--------------------------------------------------------

  CREATE TABLE "SSAFY"."REFRIGERATOR" 
   (	"REFRIGERATOR_ID" NUMBER, 
	"DEL_YN" NUMBER(1,0) DEFAULT 0, 
	"REG_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"REG_USER_SEQ" NUMBER, 
	"MOD_DTTM" TIMESTAMP (6) DEFAULT SYSDATE, 
	"MOD_USER_SEQ" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008808
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008808" ON "SSAFY"."BATCH_JOB_EXECUTION" ("JOB_EXECUTION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008828
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008828" ON "SSAFY"."BATCH_JOB_EXECUTION_CONTEXT" ("JOB_EXECUTION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008803
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008803" ON "SSAFY"."BATCH_JOB_INSTANCE" ("JOB_INSTANCE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index JOB_INST_UN
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."JOB_INST_UN" ON "SSAFY"."BATCH_JOB_INSTANCE" ("JOB_NAME", "JOB_KEY") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008820
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008820" ON "SSAFY"."BATCH_STEP_EXECUTION" ("STEP_EXECUTION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008824
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008824" ON "SSAFY"."BATCH_STEP_EXECUTION_CONTEXT" ("STEP_EXECUTION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008427
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008427" ON "SSAFY"."CONTAINER" ("FOOD_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008414
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008414" ON "SSAFY"."FOOD" ("FOOD_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008406
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008406" ON "SSAFY"."FTYPE" ("FTYPE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008325
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008325" ON "SSAFY"."MEMBER" ("MEMBER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008359
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008359" ON "SSAFY"."MEMBER_TOKEN" ("MEMBER_TOKEN_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008436
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008436" ON "SSAFY"."NOTIFICATION" ("NOTIFICATION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008404
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008404" ON "SSAFY"."REFRESH" ("REFRESH_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  DDL for Index SYS_C008318
--------------------------------------------------------

  CREATE UNIQUE INDEX "SSAFY"."SYS_C008318" ON "SSAFY"."REFRIGERATOR" ("REFRIGERATOR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY" ;
--------------------------------------------------------
--  Constraints for Table BATCH_JOB_EXECUTION
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION" MODIFY ("JOB_EXECUTION_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION" MODIFY ("JOB_INSTANCE_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION" MODIFY ("CREATE_TIME" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION" ADD PRIMARY KEY ("JOB_EXECUTION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BATCH_JOB_EXECUTION_CONTEXT
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_CONTEXT" MODIFY ("JOB_EXECUTION_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_CONTEXT" MODIFY ("SHORT_CONTEXT" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_CONTEXT" ADD PRIMARY KEY ("JOB_EXECUTION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BATCH_JOB_EXECUTION_PARAMS
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_PARAMS" MODIFY ("JOB_EXECUTION_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_PARAMS" MODIFY ("PARAMETER_NAME" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_PARAMS" MODIFY ("PARAMETER_TYPE" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_PARAMS" MODIFY ("IDENTIFYING" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BATCH_JOB_INSTANCE
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_JOB_INSTANCE" MODIFY ("JOB_INSTANCE_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_INSTANCE" MODIFY ("JOB_NAME" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_INSTANCE" MODIFY ("JOB_KEY" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_JOB_INSTANCE" ADD PRIMARY KEY ("JOB_INSTANCE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
  ALTER TABLE "SSAFY"."BATCH_JOB_INSTANCE" ADD CONSTRAINT "JOB_INST_UN" UNIQUE ("JOB_NAME", "JOB_KEY")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BATCH_STEP_EXECUTION
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION" MODIFY ("STEP_EXECUTION_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION" MODIFY ("VERSION" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION" MODIFY ("STEP_NAME" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION" MODIFY ("JOB_EXECUTION_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION" MODIFY ("CREATE_TIME" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION" ADD PRIMARY KEY ("STEP_EXECUTION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BATCH_STEP_EXECUTION_CONTEXT
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION_CONTEXT" MODIFY ("STEP_EXECUTION_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION_CONTEXT" MODIFY ("SHORT_CONTEXT" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION_CONTEXT" ADD PRIMARY KEY ("STEP_EXECUTION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CONTAINER
--------------------------------------------------------

  ALTER TABLE "SSAFY"."CONTAINER" MODIFY ("FOOD_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."CONTAINER" MODIFY ("QR_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."CONTAINER" ADD PRIMARY KEY ("FOOD_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FOOD
--------------------------------------------------------

  ALTER TABLE "SSAFY"."FOOD" MODIFY ("FOOD_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FOOD" MODIFY ("REFRIGERATOR_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FOOD" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FOOD" MODIFY ("FTYPE_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FOOD" MODIFY ("DEL_YN" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FOOD" MODIFY ("REG_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FOOD" MODIFY ("MOD_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FOOD" ADD PRIMARY KEY ("FOOD_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FTYPE
--------------------------------------------------------

  ALTER TABLE "SSAFY"."FTYPE" MODIFY ("FTYPE_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."FTYPE" ADD PRIMARY KEY ("FTYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MEMBER
--------------------------------------------------------

  ALTER TABLE "SSAFY"."MEMBER" MODIFY ("MEMBER_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER" MODIFY ("DEL_YN" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER" MODIFY ("REG_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER" MODIFY ("MOD_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER" ADD PRIMARY KEY ("MEMBER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MEMBER_TOKEN
--------------------------------------------------------

  ALTER TABLE "SSAFY"."MEMBER_TOKEN" MODIFY ("MEMBER_TOKEN_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER_TOKEN" MODIFY ("MEMBER_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER_TOKEN" MODIFY ("TOKEN" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER_TOKEN" MODIFY ("DEL_YN" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER_TOKEN" MODIFY ("REG_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER_TOKEN" MODIFY ("MOD_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."MEMBER_TOKEN" ADD PRIMARY KEY ("MEMBER_TOKEN_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table NOTIFICATION
--------------------------------------------------------

  ALTER TABLE "SSAFY"."NOTIFICATION" MODIFY ("NOTIFICATION_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."NOTIFICATION" MODIFY ("REFRIGERATOR_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."NOTIFICATION" MODIFY ("CATEGORY" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."NOTIFICATION" MODIFY ("MESSAGE" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."NOTIFICATION" MODIFY ("DEL_YN" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."NOTIFICATION" MODIFY ("REG_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."NOTIFICATION" MODIFY ("MOD_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."NOTIFICATION" ADD PRIMARY KEY ("NOTIFICATION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table REFRESH
--------------------------------------------------------

  ALTER TABLE "SSAFY"."REFRESH" MODIFY ("REFRESH_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."REFRESH" ADD PRIMARY KEY ("REFRESH_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Constraints for Table REFRIGERATOR
--------------------------------------------------------

  ALTER TABLE "SSAFY"."REFRIGERATOR" MODIFY ("REFRIGERATOR_ID" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."REFRIGERATOR" MODIFY ("DEL_YN" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."REFRIGERATOR" MODIFY ("REG_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."REFRIGERATOR" MODIFY ("MOD_USER_SEQ" NOT NULL ENABLE);
  ALTER TABLE "SSAFY"."REFRIGERATOR" ADD PRIMARY KEY ("REFRIGERATOR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SSAFY"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BATCH_JOB_EXECUTION
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION" ADD CONSTRAINT "JOB_INST_EXEC_FK" FOREIGN KEY ("JOB_INSTANCE_ID")
	  REFERENCES "SSAFY"."BATCH_JOB_INSTANCE" ("JOB_INSTANCE_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BATCH_JOB_EXECUTION_CONTEXT
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_CONTEXT" ADD CONSTRAINT "JOB_EXEC_CTX_FK" FOREIGN KEY ("JOB_EXECUTION_ID")
	  REFERENCES "SSAFY"."BATCH_JOB_EXECUTION" ("JOB_EXECUTION_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BATCH_JOB_EXECUTION_PARAMS
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_JOB_EXECUTION_PARAMS" ADD CONSTRAINT "JOB_EXEC_PARAMS_FK" FOREIGN KEY ("JOB_EXECUTION_ID")
	  REFERENCES "SSAFY"."BATCH_JOB_EXECUTION" ("JOB_EXECUTION_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BATCH_STEP_EXECUTION
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION" ADD CONSTRAINT "JOB_EXEC_STEP_FK" FOREIGN KEY ("JOB_EXECUTION_ID")
	  REFERENCES "SSAFY"."BATCH_JOB_EXECUTION" ("JOB_EXECUTION_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BATCH_STEP_EXECUTION_CONTEXT
--------------------------------------------------------

  ALTER TABLE "SSAFY"."BATCH_STEP_EXECUTION_CONTEXT" ADD CONSTRAINT "STEP_EXEC_CTX_FK" FOREIGN KEY ("STEP_EXECUTION_ID")
	  REFERENCES "SSAFY"."BATCH_STEP_EXECUTION" ("STEP_EXECUTION_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CONTAINER
--------------------------------------------------------

  ALTER TABLE "SSAFY"."CONTAINER" ADD CONSTRAINT "FK_CONTAINER_FOOD" FOREIGN KEY ("FOOD_ID")
	  REFERENCES "SSAFY"."CONTAINER" ("FOOD_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FOOD
--------------------------------------------------------

  ALTER TABLE "SSAFY"."FOOD" ADD CONSTRAINT "FK_FOOD_REFRIGERATOR1" FOREIGN KEY ("REFRIGERATOR_ID")
	  REFERENCES "SSAFY"."REFRIGERATOR" ("REFRIGERATOR_ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "SSAFY"."FOOD" ADD CONSTRAINT "FK_FOOD_REFRESH" FOREIGN KEY ("REFRESH_ID")
	  REFERENCES "SSAFY"."REFRESH" ("REFRESH_ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "SSAFY"."FOOD" ADD CONSTRAINT "FK_FOOD_FTYPE" FOREIGN KEY ("FTYPE_ID")
	  REFERENCES "SSAFY"."FTYPE" ("FTYPE_ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "SSAFY"."FOOD" ADD CONSTRAINT "FK_FOOD_PREVREFRESH" FOREIGN KEY ("PREV_REFRESH")
	  REFERENCES "SSAFY"."REFRESH" ("REFRESH_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MEMBER
--------------------------------------------------------

  ALTER TABLE "SSAFY"."MEMBER" ADD CONSTRAINT "FK_MEMBER_REFRIGERATOR" FOREIGN KEY ("REFRIGERATOR_ID")
	  REFERENCES "SSAFY"."REFRIGERATOR" ("REFRIGERATOR_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MEMBER_TOKEN
--------------------------------------------------------

  ALTER TABLE "SSAFY"."MEMBER_TOKEN" ADD CONSTRAINT "FK_MEMBER_TOKEN_MEMBER1" FOREIGN KEY ("MEMBER_ID")
	  REFERENCES "SSAFY"."MEMBER" ("MEMBER_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table NOTIFICATION
--------------------------------------------------------

  ALTER TABLE "SSAFY"."NOTIFICATION" ADD CONSTRAINT "FK_NOTIFICATION_REFRIGERATOR" FOREIGN KEY ("REFRIGERATOR_ID")
	  REFERENCES "SSAFY"."REFRIGERATOR" ("REFRIGERATOR_ID") ON DELETE CASCADE ENABLE;
