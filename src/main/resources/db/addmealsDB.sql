DELETE FROM meals;

ALTER SEQUENCE meal_seq RESTART WITH 100000;

INSERT INTO meals (description, calories, userid)
    VALUES ('Ягодный смузи', 150, 100000);
INSERT INTO meals (description, calories, userid)
    VALUES ('Котлета по-киевски', 400, 100001);
INSERT INTO meals (description, calories, userid)
    VALUES ('Желток яйца носорога', 400, 100000);
INSERT INTO meals (description, calories, userid)
    VALUES ('Борщ', 200, 100001);
