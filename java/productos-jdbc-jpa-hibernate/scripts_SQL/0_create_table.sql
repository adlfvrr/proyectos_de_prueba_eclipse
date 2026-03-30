CREATE TABLE Productos (
    id SERIAL,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(18,2) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY(id)
);

SELECT * FROM Productos;