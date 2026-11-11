package br.com.niltonpereira.lavajato_api.dto;

import jakarta.validation.constraints.NotNull;

public record ItemAtendimentoRequestDto(

        @NotNull(message = "O Serviço é obrigatório")
        Long servicoId
) {
}
