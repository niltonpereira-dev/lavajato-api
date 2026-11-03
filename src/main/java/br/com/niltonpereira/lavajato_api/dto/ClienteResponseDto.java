package br.com.niltonpereira.lavajato_api.dto;

import java.time.LocalDateTime;

public record ClienteResponseDto(

        Long id,
        String nome,
        String telefone,
        String email,
        LocalDateTime dataCasdatro
) {
}
