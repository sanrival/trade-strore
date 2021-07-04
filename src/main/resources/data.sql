INSERT INTO ROLE(ID, NAME)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO USER(ID, NAME, EMAIL, PASSWORD, ROLE_ID)
VALUES (1, 'kamal', 'kamal@db.com', '$2a$10$v/PBP2Wnyj5UHae7fOzVYOyuOXD/UomXs/K9/iGdRPt17os1A0gpi', 1),
       (2, 'sandeep', 'sandeep@db.com', '$2a$10$q6cGC8jLBAjW2UNXC0tBxO4ZXA/LDky7LMQDTMljSGPZhO7NNihf2', 2),
       (3, 'test', 'test@db.com', '$2a$10$0bRyLss/Bhj1FmtN79sdDORT7bgN5L8HkQ3Qf9iYcYmGaq5Pmv1Ha', 2);

INSERT INTO TRADE(ID, VERSION, COUNTERPARTY_ID, BOOK_ID, MATURITY, CREATED, EXPIRED)
VALUES ('T1', 1, 'CP-1', 'B1', '2021-07-02', '2021-07-02', 0)
