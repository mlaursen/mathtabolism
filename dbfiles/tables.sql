CREATE TABLE Account
( id NUMBER
, username VARCHAR2(60)
, password CHAR(44)
, role VARCHAR2(5)
, birthday DATE
, last_login DATE
, active_since DATE
, CONSTRAINT pk_Account_Id PRIMARY KEY(id)
, CONSTRAINT uq_Account_Username UNIQUE(username)
);

CREATE SEQUENCE ACCOUNT_ID_SEQ;
COMMIT;
