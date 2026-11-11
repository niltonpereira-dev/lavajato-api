package br.com.niltonpereira.lavajato_api.dto;

import java.math.BigDecimal;

public record ServicoResponseDto(

        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        Boolean ativo
) {
}
