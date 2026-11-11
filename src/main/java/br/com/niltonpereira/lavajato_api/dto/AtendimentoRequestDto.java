package br.com.niltonpereira.lavajato_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AtendimentoRequestDto(

        @NotNull(message = "O veículo é Obrigatório")
        Long veiculoId,

        @NotEmpty(message = "Adicione pelo menos um serviço")
        List<@Valid ItemAtendimentoRequestDto> itens
) {
}
