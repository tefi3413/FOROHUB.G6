package com.example.challenge.ForoHub.challenge.ForoHub.dto;

import com.example.challenge.ForoHub.challenge.ForoHub.model.Curso;
import com.example.challenge.ForoHub.challenge.ForoHub.model.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(String titulo, String mensaje, LocalDateTime fechaCreacion, Curso curso) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getCurso());
    }
}
