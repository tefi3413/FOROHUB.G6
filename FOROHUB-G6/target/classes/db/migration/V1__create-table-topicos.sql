CREATE TABLE topicos (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje VARCHAR(500) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP,
    fecha_actualizacion TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    curso VARCHAR(100) NOT NULL
);
