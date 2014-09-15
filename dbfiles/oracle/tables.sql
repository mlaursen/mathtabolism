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

CREATE TABLE Account_Setting
( id NUMBER
, account_id NUMBER
, recalculation_day VARCHAR2(9) NOT NULL
, activity_multiplier VARCHAR2(17) NOT NULL,
, tdee_formula CHAR(15) NOT NULL,
, date_changed DATE NOT NULL,
, CONSTRAINT pk_Account_Setting_Id PRIMARY KEY(id)
, CONSTRAINT fk_Account_Id FOREIGN KEY(account_id) REFERENCES Account(id)
);

CREATE SEQUENCE ACCOUNT_ID_SEQ;
CREATE SEQUENCE ACCOUNT_SETTING_ID_SEQ;
