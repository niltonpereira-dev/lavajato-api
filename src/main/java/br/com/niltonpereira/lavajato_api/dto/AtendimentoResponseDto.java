package br.com.niltonpereira.lavajato_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AtendimentoResponseDto(
        Long id,
        Long veiculoId,
        LocalDateTime dataAtendidmento,
        BigDecimal valorTotal,
        List<ItemAtendimentoResponseDto> itens
) {
}
