package br.com.niltonpereira.lavajato_api.dto;

import java.math.BigDecimal;

public record ItemAtendimentoResponseDto(

        Long SevicoId,
        String nomeServico,
        BigDecimal valorCobrado
) {
}
