package br.com.niltonpereira.lavajato_api.dto;

public record VeiculoResponseDto(

        Long id,
        String placa,
        String marca,
        String modelo,
        String cor,
        Long clienteId
) {
}
