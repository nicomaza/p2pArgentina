-- Ejemplo con valores básicos
INSERT INTO userp2p (firstname, lastname, birthday, verified, mail, phone, facebook, register_day, lastlog)
VALUES ('John', 'Doe', '1990-01-01', true, 'john.doe@example.com', 1234567890, 'johndoe', '2024-03-21', '2024-03-21 12:00:00');

-- Ejemplo con valores nulos
INSERT INTO userp2p (firstname, lastname, birthday, verified, mail, phone, facebook, register_day, lastlog)
VALUES (NULL, NULL, NULL, false, NULL, NULL, NULL, NULL, NULL);

-- Ejemplo con valores para un usuario verificado y otro no verificado
INSERT INTO userp2p (firstname, lastname, birthday, verified, mail, phone, facebook, register_day, lastlog)
VALUES ('Alice', 'Smith', '1985-05-15', true, 'alice.smith@example.com', 9876543210, 'alicesmith', '2024-03-20', '2024-03-20 10:30:00'),
       ('Bob', 'Johnson', '1978-09-25', false, 'bob.johnson@example.com', 9876123456, 'bobjohnson', '2024-03-19', '2024-03-19 08:45:00');

-- Ejemplo con valores para usuarios con fechas de registro y últimos registros diferentes
INSERT INTO userp2p (firstname, lastname, birthday, verified, mail, phone, facebook, register_day, lastlog)
VALUES ('Emma', 'Wilson', '1982-07-10', true, 'emma.wilson@example.com', 1234509876, 'emmawilson', '2024-03-18', '2024-03-18 15:20:00'),
       ('David', 'Brown', '1995-11-30', true, 'david.brown@example.com', 1234098765, 'davidbrown', '2024-03-17', '2024-03-17 18:10:00');

-- Ejemplo con valores para usuarios con diferentes detalles
INSERT INTO userp2p (firstname, lastname, birthday, verified, mail, phone, facebook, register_day, lastlog)
VALUES ('Sophia', 'Jones', '1970-03-05', true, 'sophia.jones@example.com', 1234876509, 'sophiajones', '2024-03-16', '2024-03-16 14:00:00'),
       ('Michael', 'Davis', '1988-12-20', false, 'michael.davis@example.com', 1234656789, 'michaeldavis', '2024-03-15', '2024-03-15 16:45:00');


