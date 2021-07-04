INSERT INTO ROLE(ID, NAME)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO USER(ID, NAME, EMAIL, PASSWORD, ROLE_ID)
VALUES (1, 'kamal', 'kamal@db.com', 'valecha', 1),
       (2, 'sandeep', 'sandeep@db.com', 'nare', 2);

INSERT INTO TRADE(ID, VERSION, COUNTERPARTY_ID, BOOK_ID, MATURITY, CREATED, EXPIRED)
VALUES ('T1', 1, 'CP-1', 'B1', '2021-07-02', '2021-07-02', 0)
