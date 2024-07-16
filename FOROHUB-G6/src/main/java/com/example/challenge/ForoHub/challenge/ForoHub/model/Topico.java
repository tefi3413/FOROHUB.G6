package com.example.challenge.ForoHub.challenge.ForoHub.model;

import com.example.challenge.ForoHub.challenge.ForoHub.dto.DatosActualizarTopico;
import com.example.challenge.ForoHub.challenge.ForoHub.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    @CreationTimestamp
    private LocalDateTime fechaActualizacion;


    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.curso = datosRegistroTopico.curso();
    }

    public void actualizarInformacion(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null || datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
            this.titulo = datosActualizarTopico.titulo();
            this.fechaActualizacion = LocalDateTime.now();
            this.curso = datosActualizarTopico.curso();
        }
    }

    public Long getId() {
    return id;
}

public String getTitulo() {
    return titulo;
}

public String getMensaje() {
    return mensaje;
}

public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
}

public LocalDateTime getFechaActualizacion() {
    return fechaActualizacion;
}

public Curso getCurso() {
    return curso;
}
}
