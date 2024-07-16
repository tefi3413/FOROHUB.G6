package com.example.challenge.ForoHub.challenge.ForoHub.dto;

import com.example.challenge.ForoHub.challenge.ForoHub.model.Curso;


public record DatosActualizarTopico(Long id,
                                    String titulo,
                                    String mensaje,
                                    Curso curso
) {
}
