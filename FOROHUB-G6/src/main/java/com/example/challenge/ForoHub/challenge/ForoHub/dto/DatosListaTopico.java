package com.example.challenge.ForoHub.challenge.ForoHub.dto;

import com.example.challenge.ForoHub.challenge.ForoHub.model.Curso;
import com.example.challenge.ForoHub.challenge.ForoHub.model.Topico;

import java.time.LocalDateTime;

public record DatosListaTopico(Long id, String titulo, String mensaje, Curso curso, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
    public DatosListaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(), topico.getFechaCreacion(),topico.getFechaActualizacion());
    }
}
