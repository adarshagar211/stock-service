
CREATE TABLE IF NOT EXISTS STOCKS_TBL (
  ID SERIAL PRIMARY KEY ,
  CURRENT_PRICE DOUBLE PRECISION NOT NULL default 0.00, 
  LAST_UPDATE DATE DEFAULT CURRENT_TIMESTAMP , 
  NAME VARCHAR(255) NOT NULL
);
 