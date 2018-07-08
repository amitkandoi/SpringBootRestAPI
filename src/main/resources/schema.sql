CREATE TABLE "SPRINGBOOTTEST"."SUBJECT" (
    "ID"     NUMBER,
    "NAME"   VARCHAR2(100 BYTE),
    "TYPE"   VARCHAR2(20 BYTE),
    PRIMARY KEY ( "ID" )
);

CREATE TABLE "SPRINGBOOTTEST"."STUDENT" (
    "ID"           NUMBER,
    "NAME"         VARCHAR2(100 BYTE),
    "ADDRESS"      VARCHAR2(100 BYTE),
    "EMAIL"        VARCHAR2(100 BYTE),
    "SUBJECT_ID"   NUMBER
        NOT NULL ENABLE,
    CONSTRAINT "STUDENT_PK" PRIMARY KEY ( "ID","SUBJECT_ID" )
);


CREATE SEQUENCE  "SPRINGBOOTTEST"."HIBERNATE_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;