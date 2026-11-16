
package br.com.niltonpereira.lavajato_api.controller;

import br.com.niltonpereira.lavajato_api.dto.AtendimentoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.AtendimentoResponseDto;
import br.com.niltonpereira.lavajato_api.dto.AtualizarStatusAtendimentoDto;
import br.com.niltonpereira.lavajato_api.service.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping
    public ResponseEntity<AtendimentoResponseDto> cadastrar(
            @Valid @RequestBody AtendimentoRequestDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(atendimentoService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDto>> listarTodos() {
        return ResponseEntity.ok(atendimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDto> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(atendimentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtendimentoRequestDto dto
    ) {
        return ResponseEntity.ok(atendimentoService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AtendimentoResponseDto> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusAtendimentoDto dto
    ) {
        return ResponseEntity.ok(
                atendimentoService.atualizarStatus(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atendimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
