--borramos y creamos la table denuevo

drop table if exists productos;

--creamos nuevamente con atributos de herencia
CREATE TABLE Productos (
    id SERIAL,
    tipo VARCHAR(20) NOT NULL, --discriminador
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(18,2) NOT NULL,
    stock INT NOT NULL,
    peso DECIMAL(10,2), --producto fisico
    url_descarga VARCHAR(255), --producto digital
    PRIMARY KEY(id)
);

--verificamos la creación

select * from productos;