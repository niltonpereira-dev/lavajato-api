
package br.com.niltonpereira.lavajato_api.dto;

import br.com.niltonpereira.lavajato_api.model.entity.StatusAtendimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AtendimentoResponseDto(

        Long id,
        Long veiculoId,
        LocalDateTime dataAtendimento,
        BigDecimal valorTotal,
        StatusAtendimento status,
        List<ItemAtendimentoResponseDto> itens

) {
}
