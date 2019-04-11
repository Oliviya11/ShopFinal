drop database if exists ShopDB;
create database ShopDB;
use ShopDB;

-- Таблиця для сутностей типу "Постачальник"
drop table if exists Providers;
create table Providers (
	ProviderId integer primary key auto_increment,
    ProviderName varchar(50)
);

-- Таблиця для сутностей типу "Поставка"
drop table if exists Purveyances;
create table Purveyances (
	PurveyanceId integer primary key auto_increment,
    ProviderId integer,
    foreign key (ProviderId) references Providers(ProviderId) on update cascade on delete set null
);

-- Таблиця для  сутностей типу "Відділ"
drop table if exists Departments;
create table Departments (
	DepartmentId integer primary key auto_increment,
    DepartmentName varchar(25)
);

-- Таблиця для сутностей типу "Покупка"
drop table if exists Purchases;
create table Purchases (
	PurchaseId integer primary key auto_increment,
    PurchaseDate date
);

-- Таблиця для сутностей типу "Товар"
drop table if exists Goods;
create table Goods (
	GoodsId integer primary key auto_increment,
	GoodsName varchar(50),
    Provider varchar(50),
    Num integer,
    Minimum integer,
    DepartmentId integer,
    foreign key (DepartmentId) references Departments(DepartmentId) on update cascade on delete set null
);

-- Таблиця для зв'язків типу "ТоварЦіна". 
-- Описує зв'язок один до багатох між "Товар" та "Ціна товару"
-- Містить ціни для покупців актуальну в певну дату 
drop table if exists GoodsPrices;
create table GoodsPrices (
	PriceId integer primary key auto_increment,
	GoodsId integer,
    foreign key (GoodsId) references Goods(GoodsId) on update cascade on delete cascade,
    GoodsPricesDate date,
    Price decimal
);

-- Таблиця для зв'язків типу "ТоварПоставка"
-- Містить ціни, за якими магазин купує товари у постачальників та інше
drop table if exists GoodsPurveyances;
create table GoodsPurveyances (
	Num integer,
    Price decimal,
    GoodsId integer,
    PurveyanceId integer,
    primary key (GoodsId, PurveyanceId),
    foreign key (GoodsId) references Goods(GoodsId) on update cascade on delete cascade,
    foreign key (PurveyanceId) references Purveyances(PurveyanceId) on update cascade on delete cascade
);

-- Таблиця для зв'язків типу "ТоварПокупка"
-- Містить інформацію про те, які товари були у певній покупці
drop table if exists GoodsPurchases;
create table GoodsPurchases (
	GoodsId integer,
    PurchaseId integer,
    primary key (GoodsId, PurchaseId),
    foreign key (GoodsId) references Goods(GoodsId) on update cascade on delete cascade,
    foreign key (PurchaseId) references Purchases(PurchaseId) on update cascade on delete cascade,
    Num integer
);

-- Таблиця для сутностей типу "Працівник"
drop table if exists Employees;
create table Employees (
	EmployeeId integer primary key auto_increment,
    Surname varchar(25),
    Cost decimal,
    DepartmentId integer,
    foreign key (DepartmentId) references Departments(DepartmentId) on update cascade on delete cascade
);

-- Таюлиця для сутностей типу "Замовлення"
drop table if exists Orderings;
create table Orderings (
	OrderingId integer primary key auto_increment,
    OrderingDate date,
    ProviderId integer,
    foreign key (ProviderId) references Providers(ProviderId) on update cascade on delete set null,
    PurveyanceId integer,
    foreign key (PurveyanceId) references Purveyances(PurveyanceId) on update cascade on delete cascade,
    EmployeeId integer,
    foreign key (EmployeeId) references Employees(EmployeeId) on update cascade on delete set null
);

-- Таблиця для зв'язку типу "ТоварЗамовлення"
-- Містить інформацію про те, які товари були у замовленні
-- Замовлення надходить від магазина до постачальника
drop table if exists GoodsOrderings;
create table GoodsOrderings (
	GoodsId integer,
	OrderingId integer,
	primary key (GoodsId, OrderingId),
    foreign key (GoodsId) references Goods(GoodsId) on update cascade on delete cascade,
    foreign key (OrderingId) references Orderings(OrderingId) on update cascade on delete cascade,
    Num integer
);

-- Таблиця для сутностей типу "Сальдо"
-- Містить інформацію про сальдо в певну дату
-- Дані в цій таблиці має бути сформовано на основі даних з таблиці "CashFlows"
drop table if exists Balances;
create table Balances (
	BalanceId integer primary key auto_increment,
    BalanceDate date,
    Income decimal,
    Exes decimal,
    Remnant decimal
);

-- Таблиця для сутностей типу "Рух коштів" 
-- Сутність "Рух коштів" є бащовою для сутностей "Зарплата", "Премія", "Вартість" та "Оплата"
drop table if exists CashFlows;
create table CashFlows (
	CashFlowId integer primary key auto_increment,
    CashFlowDate date,
    Cost decimal,
    BalanceId integer,
    foreign key (BalanceId) references Balances(BalanceId) on update cascade on delete cascade
);

-- Таблиця для сутностей типу "Зарплата"
-- Кортежі таблиці містять один foreign key для зв'язку із базим кортежем таблиці "CashFlows",
-- а інший foreign key для - з  базим кортежем таблиці "Employees"
-- Таблиця містить інформацію про те, який праіцівник отримав зарплату
drop table if exists Salaries;
create table Salaries (
	CashFlowId integer,
    EmployeeId integer,
    primary key (CashFlowId, EmployeeId),
	foreign key (CashFlowId) references CashFlows(CashFlowId) on update cascade on delete cascade,
    foreign key (EmployeeId) references Employees(EmployeeId) on update cascade on delete cascade
);

-- Таблиця для сутностей типу "Премія"
-- Кортежі таблиці містять один foreign key для зв'язку із базим кортежем таблиці "CashFlows",
-- а інший foreign key для - з  базим кортежем таблиці "Employees"
-- Таблиця містить інформацію про те, який праіцівник отримав премію
drop table if exists Premiums;
create table Premiums (
	CashFlowId integer,
    EmployeeId integer,
    primary key (CashFlowId, EmployeeId),
    foreign key (CashFlowId) references CashFlows(CashFlowId) on update cascade on delete cascade,
    foreign key (EmployeeId) references Employees(EmployeeId) on update cascade on delete cascade
);

-- Таблиця для сутностей типу "Вартість" (покупки)
-- Кортежі таблиці містять один foreign key для зв'язку із базим кортежем таблиці "CashFlows",
-- а інший foreign key для - з  базим кортежем таблиці "Purchases"
-- Таблиця містить інформацію про те, яка покупка мала певну вартість
drop table if exists PurchaseBenefits;
create table PurchaseBenefits (
	CashFlowId integer,
    PurchaseId integer,
    primary key (CashFlowId, PurchaseId),
    foreign key (CashFlowId) references CashFlows(CashFlowId) on update cascade on delete cascade,
    foreign key (PurchaseId) references Purchases(PurchaseId) on update cascade on delete cascade
);

-- Таблиця для сутностей типу "Оплата" (покупки)
-- Кортежі таблиці містять один foreign key для зв'язку із базим кортежем таблиці "CashFlows",
-- а інший foreign key для - з  базим кортежем таблиці "Purveyances"
-- Таблиця містить інформацію про те, яку покупку оплатив магазин
drop table if exists PurveyancesCosts;
create table PurveyancesCosts (
	CashFlowId integer,
    PurveyanceId integer,
    primary key (CashFlowId, PurveyanceId),
    foreign key (CashFlowId) references CashFlows(CashFlowId) on update cascade on delete cascade,
    foreign key (PurveyanceId) references Purveyances(PurveyanceId) on update cascade on delete cascade
);

-- insert Providers (Пстачальники)
insert into Providers (ProviderName) values ('TTO-ФУД');
insert into Providers (ProviderName) values ('ТОВ Фабрика Здорово');
insert into Providers (ProviderName) values ('Сімейний бюджет');
insert into Providers (ProviderName) values ('ТМ Владам');
insert into Providers (ProviderName) values ('Агроника "ЗДОРОВА РОДИНА"');
insert into Providers (ProviderName) values ('VITOLIO"');
insert into Providers (ProviderName) values ('VIGILANTE"');
insert into Providers (ProviderName) values ('KALIMERA"');
insert into Providers (ProviderName) values ('PRIMO GUSTO"');
insert into Providers (ProviderName) values ('Асабай (Турція)"');
insert into Providers (ProviderName) values ("Ямчан (В'єтнам)");
insert into Providers (ProviderName) values ("ELEON");
insert into Providers (ProviderName) values ("JAMAR");
insert into Providers (ProviderName) values ("КАРПАТИ");
insert into Providers (ProviderName) values ("Морячка");
insert into Providers (ProviderName) values ("ТМ Клева");
insert into Providers (ProviderName) values ("ТМ КОЛУМБ");
insert into Providers (ProviderName) values ("ПОМІЧНИЦЯ");
insert into Providers (ProviderName) values ("Crystals Health");
insert into Providers (ProviderName) values ("HOZZI");
insert into Providers (ProviderName) values ("АЛЄС");


-- Дані для тестування

insert into Departments (DepartmentName) values ('Продукти харчування');
insert into Departments (DepartmentName) values ('Промтовари');

insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Аджика 80г', 'ТТО-ФУД', 9845, 1000, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Приправа Мисливська 55г', 'ТТО-ФУД', 9846, 800, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Сванська сіль 80г', 'ТТО-ФУД', 9848, 600, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Яловичина тушкована 525г', 'ТОВ Фабрика Здорово', 9801, 1500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Свинина тушкована 525г', 'ТОВ Фабрика Здорово', 9802, 1500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Свинина тушкована 325г', 'ТОВ Фабрика Здорово', 9809, 1500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Каша гречана з яловичиною 325г', 'ТОВ Фабрика Здорово', 9822, 1500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Каша перлова з яловичиною 325г', 'ТОВ Фабрика Здорово', 9824, 1400, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Каша рисова з яловичиною 325г', 'ТОВ Фабрика Здорово', 9825, 1400, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Яловичина по-домашньому 525г', 'ТОВ Фабрика Здорово', 9828, 1400, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Свинина по-домашньому 525г', 'ТОВ Фабрика Здорово', 9829, 1400, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Курка по-домашньому 525г', 'ТОВ Фабрика Здорово', 9831, 1400, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Яловичина по-домашньому 340г', 'ТОВ Фабрика Здорово', 9832, 1400, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Свинина по-домашньому 340г', 'ТОВ Фабрика Здорово', 9834, 1400, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Сік гарбузовий 1,0 л', 'ТМ Владам', 1077, 500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Сік гарбузово-морквяний 1,0 л', 'ТМ Владам', 1078, 500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
			values ('Сік морквяно-яблучний 1,0 л', 'ТМ Владам', 1080, 500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ('Томати натуральні у томатному соку', 'ТМ Владам', 1083, 500, 1);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ('Пакети д/сміття "Ваш бюджет" 60л, 15шт', 'ПОМІЧНИЦЯ', 671, 200, 2);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ('Мило господарське 72% 200гр', 'ПОМІЧНИЦЯ', 9005, 1000, 2);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ("Сіль для ванн М'ЯТА 500г", 'Crystals Health', 1912, 400, 2);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ("Сіль для ванн ЧАЙНЕ ДЕРЕВО 500г", 'Crystals Health', 1915, 400, 2);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ("Сіль для ванн ЕВКАЛІПТ 500г", 'Crystals Health', 1916, 400, 2);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ("Сіль для ванн з піною Blue 600г", 'Crystals Health', 1951, 400, 2);
insert into Goods (GoodsName, Provider, Num, Minimum, DepartmentId)
            values ("Сіль для ванн з піною Yellow 600г", 'Crystals Health', 1952, 400, 2);

-- Purchase 1
insert into Purchases (PurchaseDate) values("2018-07-12");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(19, 1, 30);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(20, 1, 400);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(21, 1, 50);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(22, 1, 10);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(23, 1, 50);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(24, 1, 12);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(25, 1, 10);
-- balance id 1
insert into Balances(BalanceDate, Income, Exes, Remnant) values ("2018-07-12", 0, 0, 0);
-- cash flow id 1
insert into CashFlows(CashFlowDate, Cost, BalanceId) values("2018-07-12", 60650, 1);
insert into PurchaseBenefits(CashFlowId, PurchaseId) values(1, 1);

-- Purchase 2
insert into Purchases (PurchaseDate) values("2018-07-13");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(1, 2, 30);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(2, 2, 130);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(3, 2, 60);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(4, 2, 12);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(5, 2, 100);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(6, 2, 110);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(7, 2, 90);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(8, 2, 11);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(9, 2, 52);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(10, 2, 28);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(11, 2, 11);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(12, 2, 25);
-- balance id 2
insert into Balances(BalanceDate, Income, Exes, Remnant) values ("2018-07-13", 0, 0, 0);
-- cash flow id 2
insert into CashFlows(CashFlowDate, Cost, BalanceId) values("2018-07-13", 100650, 2);
insert into PurchaseBenefits(CashFlowId, PurchaseId) values(2, 2);

-- Purchase 3
insert into Purchases (PurchaseDate) values("2018-07-14");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(1, 3, 100);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(2, 3, 400);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(3, 3, 50);
-- balance id 3
insert into Balances(BalanceDate, Income, Exes, Remnant) values ("2018-07-14", 0, 0, 0);
-- cash flow id 3
insert into CashFlows(CashFlowDate, Cost, BalanceId) values("2018-07-14", 59650, 1);
insert into PurchaseBenefits(CashFlowId, PurchaseId) values(3, 3);

-- Purchase 4
insert into Purchases (PurchaseDate) values("2018-07-14");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(22, 4, 100);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(23, 4, 50);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(24, 4, 110);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(25, 4, 20);
insert into PurchaseBenefits(CashFlowId, PurchaseId) values(3, 4);

-- Purchase 5
insert into Purchases (PurchaseDate) values("2018-07-15");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(5, 5, 120);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(4, 5, 360);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(8, 5, 190);
-- balance id 4
insert into Balances (BalanceDate, Income, Exes, Remnant) values ("2018-07-15", 0, 0, 0);
-- cash flow id 4
insert into CashFlows (CashFlowDate, Cost, BalanceId) values("2018-07-15", 73190, 4);
insert into PurchaseBenefits (CashFlowId, PurchaseId) values(4, 5);

-- Четвер листопад
-- Purchase 6 
insert into Purchases (PurchaseDate) values("2018-11-02");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(5, 6, 120);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(4, 6, 360);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(8, 6, 190);
-- balance id 5
insert into Balances (BalanceDate, Income, Exes, Remnant) values ("2018-11-01", 0, 0, 0);
-- cash flow id 5
insert into CashFlows (CashFlowDate, Cost, BalanceId) values("2018-11-01", 80000, 5);
insert into PurchaseBenefits (CashFlowId, PurchaseId) values(5, 6);

-- Purchase 7
insert into Purchases (PurchaseDate) values("2018-11-15");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(10, 7, 120);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(11, 7, 360);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(12, 7, 190);
-- balance id 6
insert into Balances (BalanceDate, Income, Exes, Remnant) values ("2018-11-15", 0, 0, 0);
-- cash flow id 6
insert into CashFlows (CashFlowDate, Cost, BalanceId) values("2018-11-15", 80000, 6);
insert into PurchaseBenefits (CashFlowId, PurchaseId) values(6, 7);

-- Purchase 8
insert into Purchases (PurchaseDate) values("2019-01-23");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(19, 8, 60);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(20, 8, 70);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(21, 8, 80);
-- balance id 7
insert into Balances (BalanceDate, Income, Exes, Remnant) values ("2019-01-23", 0, 0, 0);
-- cash flow id 7
insert into CashFlows (CashFlowDate, Cost, BalanceId) values("2019-01-23", 50000, 7);
insert into PurchaseBenefits (CashFlowId, PurchaseId) values(7, 8);

-- Purchase 9
insert into Purchases (PurchaseDate) values("2019-01-30");
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(1, 9, 80);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(2, 9, 10);
insert into GoodsPurchases (GoodsId, PurchaseId, Num) values(3, 9, 25);
-- balance id 8
insert into Balances (BalanceDate, Income, Exes, Remnant) values ("2019-01-30", 0, 0, 0);
-- cash flow id 8
insert into CashFlows (CashFlowDate, Cost, BalanceId) values("2019-01-30", 100000, 8);
insert into PurchaseBenefits (CashFlowId, PurchaseId) values(8, 9);

-- Goods prices
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(1, "2018-07-12", 54);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(1, "2018-07-13", 56);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(1, "2018-07-14", 60);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(1, "2018-07-15", 70);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(1, "2018-07-16", 72);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(2, "2018-07-12", 110);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(2, "2018-07-13", 112);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(2, "2018-07-14", 120);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(2, "2018-07-15", 121);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(2, "2018-07-16", 122);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(3, "2018-07-12", 113);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(3, "2018-07-13", 114);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(3, "2018-07-14", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(3, "2018-07-15", 116);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(3, "2018-07-16", 117);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(4, "2018-07-12", 68);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(4, "2018-07-13", 90);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(4, "2018-07-14", 95);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(4, "2018-07-15", 98);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(4, "2018-07-16", 100);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(5, "2018-07-12", 65);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(5, "2018-07-13", 78);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(5, "2018-07-14", 68);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(5, "2018-07-15", 80);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(5, "2018-07-16", 86);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(6, "2018-07-12", 65);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(6, "2018-07-13", 78);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(6, "2018-07-14", 68);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(6, "2018-07-15", 80);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(6, "2018-07-16", 86);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(7, "2018-07-12", 165);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(7, "2018-07-13", 178);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(7, "2018-07-14", 168);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(7, "2018-07-15", 180);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(7, "2018-07-16", 186);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(8, "2018-07-12", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(8, "2018-07-13", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(8, "2018-07-14", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(8, "2018-07-15", 110);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(8, "2018-07-16", 116);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(9, "2018-07-12", 125);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(9, "2018-07-13", 128);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(9, "2018-07-14", 128);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(9, "2018-07-15", 120);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(9, "2018-07-16", 126);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(10, "2018-07-12", 125);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(10, "2018-07-13", 128);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(10, "2018-07-14", 128);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(10, "2018-07-15", 120);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(10, "2018-07-16", 126);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(11, "2018-07-12", 65);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(11, "2018-07-13", 68);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(11, "2018-07-14", 69);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(11, "2018-07-15", 70);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(11, "2018-07-16", 74);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(12, "2018-07-12", 90);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(12, "2018-07-13", 91);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(12, "2018-07-14", 92);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(12, "2018-07-15", 93);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(12, "2018-07-16", 94);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(19, "2018-07-12", 70);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(19, "2018-07-13", 71);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(19, "2018-07-14", 72);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(19, "2018-07-15", 73);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(19, "2018-07-16", 74);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(20, "2018-07-12", 100);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(20, "2018-07-13", 111);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(20, "2018-07-14", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(20, "2018-07-15", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(20, "2018-07-16", 119);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(21, "2018-07-12", 100);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(21, "2018-07-13", 111);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(21, "2018-07-14", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(21, "2018-07-15", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(21, "2018-07-16", 119);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(22, "2018-07-12", 100);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(22, "2018-07-13", 111);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(22, "2018-07-14", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(22, "2018-07-15", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(22, "2018-07-16", 119);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(23, "2018-07-12", 100);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(23, "2018-07-13", 111);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(23, "2018-07-14", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(23, "2018-07-15", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(23, "2018-07-16", 119);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(24, "2018-07-12", 100);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(24, "2018-07-13", 111);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(24, "2018-07-14", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(24, "2018-07-15", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(24, "2018-07-16", 119);

insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(25, "2018-07-12", 100);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(25, "2018-07-13", 111);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(25, "2018-07-14", 115);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(25, "2018-07-15", 118);
insert into GoodsPrices (GoodsId, GoodsPricesDate, Price) values(25, "2018-07-16", 119);

-- purveyance id 1
insert into Purveyances (ProviderId) values (18);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (2000, 120, 19, 1);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1200, 112, 20, 1);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (900, 115, 21, 1);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 100, 25, 1);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (800, 100, 24, 1);
-- cash flow 9
insert into CashFlows (CashFlowDate, Cost, BalanceId) values ("2018-07-12", 40000, 1);
insert into PurveyancesCosts (CashFlowId, PurveyanceId) values (9, 1);

-- purveyance id 2
insert into Purveyances (ProviderId) values (1);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 120, 10, 2);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1200, 112, 12, 2);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (950, 115, 11, 2);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 97, 15, 2);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (800, 90, 13, 2);
-- cash flow 10
insert into CashFlows (CashFlowDate, Cost, BalanceId) values ("2018-07-13", 40000, 2);
insert into PurveyancesCosts (CashFlowId, PurveyanceId) values (10, 2);

-- purveyance id 3
insert into Purveyances (ProviderId) values (1);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 120, 10, 3);
-- cash flow 11
insert into CashFlows (CashFlowDate, Cost, BalanceId) values ("2018-07-15", 40000, 3);
insert into PurveyancesCosts (CashFlowId, PurveyanceId) values (11, 3);

-- purveyance id 4
insert into Purveyances (ProviderId) values (2);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 50, 10, 4);
-- cash flow 12
insert into CashFlows (CashFlowDate, Cost, BalanceId) values ("2018-11-01", 10000, 5);
insert into PurveyancesCosts (CashFlowId, PurveyanceId) values (12, 4);

-- purveyance id 5
insert into Purveyances (ProviderId) values (2);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 50, 10, 5);
-- cash flow 13
insert into CashFlows (CashFlowDate, Cost, BalanceId) values ("2018-11-08", 10000, 6);
insert into PurveyancesCosts (CashFlowId, PurveyanceId) values (13, 5);

-- purveyance id 6
insert into Purveyances (ProviderId) values (2);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 50, 10, 6);
-- cash flow 14
insert into CashFlows (CashFlowDate, Cost, BalanceId) values ("2018-11-08", 5000, 6);
insert into PurveyancesCosts (CashFlowId, PurveyanceId) values (14, 6);

-- purveyance id 7
insert into Purveyances (ProviderId) values (3);
insert into GoodsPurveyances (Num, Price, GoodsId, PurveyanceId) values (1000, 50, 10, 7);
-- balance id 9
insert into Balances (BalanceDate, Income, Exes, Remnant) values ("2019-01-23", 0, 0, 0);
-- cash flow 15
insert into CashFlows (CashFlowDate, Cost, BalanceId) values ("2019-01-23", 5000, 9);
insert into PurveyancesCosts (CashFlowId, PurveyanceId) values (15, 7);
