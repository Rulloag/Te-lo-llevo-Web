CREATE TABLE transportes (
     id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
     patente VARCHAR NOT NULL,
     anio INTEGER NOT NULL,
     marca VARCHAR NOT NULL,
     modelo VARCHAR NOT NULL,
     CONSTRAINT pk_transporte PRIMARY KEY (id)
);

CREATE TABLE solicitudes (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    codigo_seguimiento VARCHAR NOT NULL,
    numero_de_orden VARCHAR NOT  NULL,
    origen VARCHAR NOT NULL,
    direccion_origen VARCHAR NOT NULL,
    destino VARCHAR NOT NULL,
    direccio_destinon VARCHAR NOT NULL,
    descripcion VARCHAR NOT NULL,
    estado VARCHAR NOT NULL,
    transporte_id INTEGER,
    CONSTRAINT pk_solicitud PRIMARY KEY (id),
    CONSTRAINT fk_transporte_id FOREIGN KEY (transporte_id) REFERENCES transportes (id)
);