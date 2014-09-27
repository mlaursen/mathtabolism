CREATE TABLE account
( id CHAR(10)
, username VARCHAR(60)
, password CHAR(44)
, role VARCHAR(5)
, birthday DATE
, last_login DATE
, active_since DATE
, CONSTRAINT pk_Account_Id PRIMARY KEY(id)
, CONSTRAINT uq_Account_Username UNIQUE(username)
);

CREATE TABLE account_setting
( account_id CHAR(10)
, date_changed DATE
, recalculation_day VARCHAR(9) NOT NULL
, activity_multiplier VARCHAR(17) NOT NULL
, tdee_formula CHAR(15) NOT NULL
, age INTEGER
, CONSTRAINT pk_Account_Setting_Id PRIMARY KEY(account_id, date_changed)
, CONSTRAINT fk_Account_Id_Setting FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE account_weight
( account_id CHAR(10)
, weigh_in_date DATE
, weight NUMERIC(5,2)
, CONSTRAINT pk_Account_Weight PRIMARY KEY(account_id, weigh_in_date)
, CONSTRAINT fk_Account_Id_Weight FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE ingredient
( name VARCHAR(40)
, brand VARCHAR(40)
, category VARCHAR(12)
, default_serving VARCHAR(11)
, default_size NUMERIC(7,2)
, alternate_serving VARCHAR(11)
, alternate_size NUMERIC(7,2)
, calories NUMERIC(10,2)
, fat NUMERIC(5,2)
, carbohydrates NUMERIC(5,2)
, protein NUMERIC(5,2)
, CONSTRAINT pk_Ingredient PRIMARY KEY(name, brand)
);


CREATE TABLE sequence_table
( sequence_name VARCHAR(30) NOT NULL PRIMARY KEY
, sequence_value INTEGER UNSIGNED NOT NULL
);


delimiter //

create procedure sp_generate_key(in pSeqName varchar(30), out pNextVal int unsigned)
begin
	SELECT sequence_value
	INTO pNextVal
	FROM sequence_table
	WHERE sequence_name=pSeqName;

	if pNextVal is null then
		set pNextVal = 0;
		INSERT INTO sequence_table(sequence_name, sequence_value)
		VALUES(pSeqName, pNextVal);
	else
		set pNextVal = pNextVal + 1;
		UPDATE sequence_table
		SET sequence_value=pNextVal
		WHERE sequence_name=pSeqName;
	end if;	
end//
delimiter ;

