package br.com.niltonpereira.lavajato_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VeiculoRequestDto(

        @NotBlank(message = "A placa é obrigatória")
        @Size(max = 100, message = "A placa deve ter o máximo 100 caracteres")
        String placa,

        @NotBlank(message = "A marca é obrigatoria")
        @Size(max = 50, message = "A marca deve ter no máximo 50")
        String marca,

        @NotBlank(message = "O modelo é obrigatório")
        @Size(max = 50, message = "O modelo deve ter no máximo 50 caracteres")
        String modelo,

        @NotBlank(message = "O modelo é obrigatório")
        @Size(max = 50, message = "A cor deve ter no máximo 50 caracteres")
        String cor,

        @NotNull(message = "O cliente é obrigatório")
        Long clienteId

){

}
