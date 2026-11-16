
package br.com.niltonpereira.lavajato_api.dto;

import br.com.niltonpereira.lavajato_api.model.entity.StatusAtendimento;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusAtendimentoDto(

        @NotNull(message = "O status é obrigatório")
        StatusAtendimento status

) {
}
