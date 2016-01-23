CREATE TABLE IF NOT EXISTS game.score (
   cust_id TEXT,
   level text,
   attempt INT,
   score INT,
   PRIMARY KEY ((cust_id, level), attempt)
);
