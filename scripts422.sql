CREATE TABLE auto
(
    id SERIAL PRIMARY KEY,
    brand text NOT NULL,
    model text NOT NULL,
    cost NUMERIC (9,2)
);
CREATE TABLE human
(
    id SERIAL PRIMARY KEY,
    name  text NOT NULL,
    age integer CHECK (age > 0) NOT NULL,
    license BOOLEAN default false,
    auto_id integer REFERENCES auto (id)
);

INSERT INTO auto (id,brand, model, cost) VALUES (1,'toyota', 'adas',3456777);
INSERT INTO auto (id,brand,model,cost) VALUES (2,'peugeot', 'sdfs', 2987000);
INSERT INTO auto (id,brand,model,cost) VALUES (3,'chevrolet', 'aaa', 2987000);
INSERT INTO auto (id,brand,model,cost) VALUES (4,'opel', 'ffff', 2987000);
INSERT INTO human (id, name, age, license, auto_id) VALUES (1,'Агаонов Иван', 24, 'true', 1);
INSERT INTO human (id,name,age,license,auto_id) VALUES (2,'euewiwueu',35,'true',2);
INSERT INTO human (id,name,age,license,auto_id) VALUES (3,'kgjkfsj sfs',30,'true',1);
INSERT INTO human (id,name,age,license,auto_id) VALUES (4,'ываы ыыа ',33,'true',3);
INSERT INTO human (id,name,age,license,auto_id) VALUES (5,'ыаыа sfs',30,'true',3);
INSERT INTO human (id,name,age,license,auto_id) VALUES (6,'зжопр sfs',30,'true',4);

SELECT * FROM auto;
SELECT * FROM human;
SELECT human.id,human.name,auto.brand,auto.cost FROM human INNER JOIN auto ON human.auto_id=auto.id;
SELECT human.id,human.name,auto.brand,auto.cost FROM human INNER JOIN auto ON human.auto_id=auto.id WHERE auto.cost > 3000000;




