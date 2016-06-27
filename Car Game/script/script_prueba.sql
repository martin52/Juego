/*
    Script para MySQL.
    Script de creación de la BD.
    Se crean las tablas y se insertan algunos registros.
	
 */

CREATE DATABASE IF NOT EXISTS prueba;

USE prueba;

SET FOREIGN_KEY_CHECKS = 0;
	DROP TABLE IF EXISTS TIPODOCUMENTO;
	DROP TABLE IF EXISTS DEPARTAMENTO;
	DROP TABLE IF EXISTS EMPLEADO;
SET FOREIGN_KEY_CHECKS = 1;
    
CREATE TABLE TIPODOCUMENTO (
    cod_tipodoc INT NOT NULL,
    tipodocdesc VARCHAR(20) NOT NULL
);
ALTER TABLE TIPODOCUMENTO ADD CONSTRAINT pk_tipodocumento PRIMARY KEY (cod_tipodoc);

CREATE TABLE DEPARTAMENTO (
    cod_departamento INT NOT NULL,
    departamentodesc VARCHAR(30) NOT NULL
);
ALTER TABLE DEPARTAMENTO ADD CONSTRAINT pk_departamento PRIMARY KEY (cod_departamento);

CREATE TABLE EMPLEADO (
    cod_empleado INT NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    cod_tipodoc INT NOT NULL,
    documento VARCHAR(11) NOT NULL,
    cod_departamento INT NULL
);
ALTER TABLE EMPLEADO ADD CONSTRAINT pk_empleado  PRIMARY KEY (cod_empleado);
ALTER TABLE EMPLEADO ADD CONSTRAINT fk_empleado_tipodocumento FOREIGN KEY (cod_tipodoc) REFERENCES TIPODOCUMENTO (cod_tipodoc);
ALTER TABLE EMPLEADO ADD CONSTRAINT fk_empleado_departamento FOREIGN KEY (cod_departamento) REFERENCES DEPARTAMENTO (cod_departamento);

/* Inserto datos en las tablas lkp. */     
INSERT INTO TIPODOCUMENTO(cod_tipodoc, tipodocdesc) VALUES(1, 'DNI');
INSERT INTO TIPODOCUMENTO(cod_tipodoc, tipodocdesc) VALUES(2, 'Libreta Cívica');
INSERT INTO TIPODOCUMENTO(cod_tipodoc, tipodocdesc) VALUES(3, 'Cedula de Identidad');
INSERT INTO TIPODOCUMENTO(cod_tipodoc, tipodocdesc) VALUES(4, 'Pasaporte');

INSERT INTO DEPARTAMENTO(cod_departamento, departamentodesc) VALUES(100, 'Administración');
INSERT INTO DEPARTAMENTO(cod_departamento, departamentodesc) VALUES(200, 'Sistemas');
INSERT INTO DEPARTAMENTO(cod_departamento, departamentodesc) VALUES(300, 'RRHH');
INSERT INTO DEPARTAMENTO(cod_departamento, departamentodesc) VALUES(400, 'Logística');
INSERT INTO DEPARTAMENTO(cod_departamento, departamentodesc) VALUES(500, 'Contabilidad');
INSERT INTO DEPARTAMENTO(cod_departamento, departamentodesc) VALUES(600, 'Facturación');
INSERT INTO DEPARTAMENTO(cod_departamento, departamentodesc) VALUES(700, 'Envíos');

DROP VIEW IF EXISTS VWEMPLEADO;
CREATE VIEW VWEMPLEADO AS
    SELECT 
        cod_empleado AS legajo,
        CONCAT(e.apellido, ', ', e.nombre) AS empleado,
        CONCAT(td.tipodocdesc, ' - ', e.documento) documento,
        CONCAT(e.cod_departamento,
                ' - ',
                d.departamentodesc) AS departamento
    FROM
        empleado e
            JOIN
        tipodocumento td ON e.cod_tipodoc = td.cod_tipodoc
            JOIN
        departamento d ON e.cod_departamento = d.cod_departamento;

delimiter //
DROP PROCEDURE IF EXISTS INSERTA_EMPLEADO //
CREATE PROCEDURE INSERTA_EMPLEADO(IN _apellido VARCHAR(50),
								IN _nombre varchar(50),
								IN _cod_tipodoc int,
								IN _documento varchar(11),
								IN _cod_departamento int)
BEGIN
DECLARE _ultCodEmpleado int;

SELECT (coalesce(max(cod_empleado), 0) + 1) into _ultCodEmpleado FROM EMPLEADO;

INSERT INTO EMPLEADO(cod_empleado, apellido, nombre, cod_tipodoc, documento, cod_departamento) 
VALUES(_ultCodEmpleado, _apellido, _nombre, _cod_tipodoc, _documento, _cod_departamento);
END //
