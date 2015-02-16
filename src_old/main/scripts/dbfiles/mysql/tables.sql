CREATE TABLE account
( id CHAR(10)
, username VARCHAR(60)
, password CHAR(44)
, email VARCHAR(120)
, role VARCHAR(5)
, birthday DATE
, gender VARCHAR(6)
, last_login DATE
, active_since DATE
, CONSTRAINT pk_Account_Id PRIMARY KEY(id)
, CONSTRAINT uq_Account_Username UNIQUE(username)
);

CREATE TABLE account_setting
( id CHAR(10)
, account_id CHAR(10)
, date_changed DATE
, recalculation_day VARCHAR(9)
, activity_multiplier VARCHAR(17)
, tdee_formula CHAR(15)
, unit_system VARCHAR(8)
, age INTEGER
, use_age INT(1)
, height DECIMAL
, CONSTRAINT pk_Account_Setting_Id PRIMARY KEY(id)
, CONSTRAINT uk_Account_Setting_Id_Changed UNIQUE(account_id, date_changed)
, CONSTRAINT fk_Account_Id_Setting FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE account_weight
( id CHAR(10)
, account_id CHAR(10)
, weigh_in_date DATE
, weight NUMERIC(5,2)
, CONSTRAINT pk_Account_Weight_Id PRIMARY KEY(id)
, CONSTRAINT uk_Account_Weight_Id_Weigh_Date UNIQUE KEY(account_id, weigh_in_date)
, CONSTRAINT fk_Account_Id_Weight FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE brand
( id CHAR(10)
, name VARCHAR(40)
, CONSTRAINT pk_Brand PRIMARY KEY(id)
, CONSTRAINT uq_Brand_Name UNIQUE(name)
);

CREATE TABLE ingredient
( id CHAR(10)
, name VARCHAR(40)
, brand_id CHAR(10)
, category VARCHAR(12)
, default_serving VARCHAR(11)
, default_size NUMERIC(7,2)
, alternate_serving VARCHAR(11)
, alternate_size NUMERIC(7,2)
, calories NUMERIC(10,2)
, fat NUMERIC(5,2)
, carbohydrates NUMERIC(5,2)
, protein NUMERIC(5,2)
, CONSTRAINT pk_Ingredient PRIMARY KEY(id)
, CONSTRAINT fk_Ingredient_Brand FOREIGN KEY(brand_id) REFERENCES brand(id)
, CONSTRAINT uq_Ingredient UNIQUE(name, brand_id)
);

CREATE TABLE daily_intake
( id CHAR(10)
, account_id CHAR(10)
, account_weight_id CHAR(10)
, intake_date DATE
, calorie_change INT
, fat_multiplier NUMERIC(3, 2)
, carb_multiplier NUMERIC(3, 2)
, protein_multiplier NUMERIC(3, 2)
, CONSTRAINT pk_Daily_Intake PRIMARY KEY(id)
, CONSTRAINT uq_Daily_Intake UNIQUE(account_id, intake_date)
, CONSTRAINT fk_Daily_Intake_Weight FOREIGN KEY(account_weight_id) REFERENCES account_weight(id)
, CONSTRAINT fk_Daily_Intake_Account FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE meal
( id CHAR(10)
, name VARCHAR(40)
, CONSTRAINT pk_Meal PRIMARY KEY(id)
);

CREATE TABLE meal_part
( id CHAR(10)
, meal_id CHAR(10)
, ingredient_id CHAR(10)
, ingredient_serving VARCHAR(11)
, ingredient_size NUMERIC(7, 2)
, CONSTRAINT pk_Meal_Part PRIMARY KEY(id)
, CONSTRAINT fk_Meal_Part_Meal FOREIGN KEY(meal_id) REFERENCES meal(id)
, CONSTRAINT fk_Meal_part_Ingredient FOREIGN KEY(ingredient_id) REFERENCES ingredient(id)
);

CREATE TABLE daily_intake_meal
( id CHAR(10)
, meal_id CHAR(10)
, daily_intake_id CHAR(10)
, CONSTRAINT pk_Daily_Intake_Meal PRIMARY KEY(id)
, CONSTRAINT fk_Daily_Intake_Meal_Meal FOREIGN KEY(meal_id) REFERENCES meal(id)
, CONSTRAINT fk_Daily_Intake_meal_Daily_Intake FOREIGN KEY(daily_intake_id) REFERENCES daily_intake(id)
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

