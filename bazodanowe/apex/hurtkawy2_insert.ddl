Insert into sprowadzane values (default,'Anglia');
Insert into sprowadzane values (default,'Brazylia');
Insert into sprowadzane values (default,'Chiny');

Insert into kawa values (default,1,'Tchibo',12.56,1000);
Insert into kawa values (default,2,'Jacobs',16.86,2000);
Insert into kawa values (default,3,'McCafe',14.77,1500);
Insert into kawa values (default,2,'Woseba',11.54,1250);

Insert into kodpromocyjny values (default,'ALPHA',0.2);


Insert into klient values (default,'Marek','Kurczak','12345678912',to_date('71/12/17','RR/MM/DD'));
Insert into klient values (default,'Jolanta','Frankowska','09876543217',to_date('82/01/11','RR/MM/DD'));


INSERT INTO rola VALUES(default, 'Admin');
INSERT INTO rola VALUES(default, 'User');

INSERT INTO konto VALUES(1,1,'admin','admin');
INSERT INTO konto VALUES(2,2,'user','user');

INSERT INTO zamowienie VALUES(default,1,9,1,20,900,to_date('95/05/06','RR/MM/DD'));
