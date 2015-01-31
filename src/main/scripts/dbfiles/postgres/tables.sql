CREATE TABLE account
( id SERIAL
, username TEXT
, password CHAR(44)
, email TEXT
, role TEXT
, birthday DATE
, gender TEXT
, last_login DATE
, active_since DATE
, CONSTRAINT pk_Account_Id PRIMARY KEY(id)
, CONSTRAINT uq_Account_Username UNIQUE(username)
);

CREATE TABLE account_setting
( id SERIAL
, account_id INTEGER
, date_changed DATE
, recalculation_day TEXT
, activity_multiplier TEXT
, tdee_formula TEXT
, unit_system TEXT
, age INTEGER
, is_using_age BOOLEAN
, height DECIMAL
, CONSTRAINT pk_Account_Setting_Id PRIMARY KEY(id)
, CONSTRAINT uk_Account_Setting_Id_Changed UNIQUE(account_id, date_changed)
, CONSTRAINT fk_Account_Id_Setting FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE account_weight
( id SERIAL
, account_id INTEGER
, weigh_in_date DATE
, weight NUMERIC(5,2)
, CONSTRAINT pk_Account_Weight_Id PRIMARY KEY(id)
, CONSTRAINT uk_Account_Weight_Id_Weigh_Date UNIQUE(account_id, weigh_in_date)
, CONSTRAINT fk_Account_Id_Weight FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE brand
( id SERIAL
, name TEXT
, CONSTRAINT pk_Brand PRIMARY KEY(id)
, CONSTRAINT uq_Brand_Name UNIQUE(name)
);

CREATE TABLE ingredient
( id SERIAL
, name TEXT
, brand_id INTEGER
, category TEXT
, default_serving TEXT
, default_size NUMERIC(7,2)
, alternate_serving TEXT
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
( id SERIAL
, account_id INTEGER
, account_weight_id INTEGER
, intake_date DATE
, calorie_change INTEGER
, fat_multiplier NUMERIC(3, 2)
, carb_multiplier NUMERIC(3, 2)
, protein_multiplier NUMERIC(3, 2)
, CONSTRAINT pk_Daily_Intake PRIMARY KEY(id)
, CONSTRAINT uq_Daily_Intake UNIQUE(account_id, intake_date)
, CONSTRAINT fk_Daily_Intake_Weight FOREIGN KEY(account_weight_id) REFERENCES account_weight(id)
, CONSTRAINT fk_Daily_Intake_Account FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE meal
( id SERIAL
, name TEXT
, CONSTRAINT pk_Meal PRIMARY KEY(id)
);

CREATE TABLE meal_part
( id SERIAL
, meal_id INTEGER
, ingredient_id INTEGER
, ingredient_serving TEXT
, ingredient_size NUMERIC(7, 2)
, CONSTRAINT pk_Meal_Part PRIMARY KEY(id)
, CONSTRAINT fk_Meal_Part_Meal FOREIGN KEY(meal_id) REFERENCES meal(id)
, CONSTRAINT fk_Meal_part_Ingredient FOREIGN KEY(ingredient_id) REFERENCES ingredient(id)
);

CREATE TABLE daily_intake_meal
( id SERIAL
, meal_id INTEGER
, daily_intake_id INTEGER
, CONSTRAINT pk_Daily_Intake_Meal PRIMARY KEY(id)
, CONSTRAINT fk_Daily_Intake_Meal_Meal FOREIGN KEY(meal_id) REFERENCES meal(id)
, CONSTRAINT fk_Daily_Intake_meal_Daily_Intake FOREIGN KEY(daily_intake_id) REFERENCES daily_intake(id)
);
