ALTER TABLE ORDERS ADD COLUMN VERSION int;

ALTER TABLE CUSTOMERS ADD COLUMN CREATED timestamp;
ALTER TABLE CUSTOMERS ADD COLUMN UPDATED timestamp;
ALTER TABLE CUSTOMERS ADD COLUMN VERSION int;

ALTER TABLE PRODUCTS ADD COLUMN CREATED timestamp;
ALTER TABLE PRODUCTS ADD COLUMN UPDATED timestamp;
ALTER TABLE PRODUCTS ADD COLUMN VERSION int;