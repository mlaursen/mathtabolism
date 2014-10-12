CREATE TABLE Account
( id CHAR(10)
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
( account_id CHAR(10)
, date_changed DATE
, recalculation_day VARCHAR2(9) NOT NULL
, activity_multiplier VARCHAR2(17) NOT NULL
, tdee_formula CHAR(15) NOT NULL
, age NUMBER
, CONSTRAINT pk_Account_Setting_Id PRIMARY KEY(account_id, date_changed)
, CONSTRAINT fk_Account_Id_Setting FOREIGN KEY(account_id) REFERENCES Account(id)
);

CREATE TABLE Account_Weight
( account_id CHAR(10)
, weigh_in_date DATE
, weight NUMBER(5,2)
, CONSTRAINT pk_Account_Weight PRIMARY KEY(account_id, weigh_in_date)
, CONSTRAINT fk_Account_Id_Weight FOREIGN KEY(account_id) REFERENCES Account(id)
);

CREATE TABLE Ingredient
( id CHAR(10)
, name VARCHAR2(40)
, brand VARCHAR2(40)
, category VARCHAR2(12)
, default_serving VARCHAR2(11)
, default_size NUMBER(7,2)
, alternate_serving VARCHAR2(11)
, alternate_size NUMBER(7,2)
, calories NUMBER(10,2)
, fat NUMBER(5,2)
, carbohydrates NUMBER(5,2)
, protein NUMBER(5,2)
, CONSTRAINT pk_Ingredient PRIMARY KEY(id)
, CONSTRAINT uq_Ingredient UNIQUE(name,brand)
);

CREATE TABLE Sequence_Table
( sequence_name VARCHAR2(30) NOT NULL PRIMARY KEY
, sequence_value NUMBER NOT NULL
);

CREATE OR REPLACE PROCEDURE sp_generate_key(pSeqName IN VARCHAR2, pNextVal OUT NUMBER)
IS
BEGIN
  SELECT sequence_value
  INTO pNextVal
  FROM Sequence_Table
  WHERE Sequence_Name=pSeqName;
  
  pNextVal := pNextVal + 1;
  UPDATE Sequence_Table
  SET sequence_value=pNextVal
  WHERE sequence_name=pSeqName;
  
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      pNextVal := 0;
      INSERT INTO Sequence_Table
      VALUES(pSeqName, pNextVal);
END;
/


