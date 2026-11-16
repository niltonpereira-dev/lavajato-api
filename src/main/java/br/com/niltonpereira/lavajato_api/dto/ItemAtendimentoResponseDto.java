package br.com.niltonpereira.lavajato_api.dto;

import java.math.BigDecimal;

public record ItemAtendimentoResponseDto(

        Long sevicoId,
        String nomeServico,
        BigDecimal valorCobrado
) {
}
