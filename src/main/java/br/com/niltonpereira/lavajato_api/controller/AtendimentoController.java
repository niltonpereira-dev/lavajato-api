package br.com.niltonpereira.lavajato_api.controller;


import br.com.niltonpereira.lavajato_api.dto.AtendimentoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.AtendimentoResponseDto;
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
    public ResponseEntity<AtendimentoResponseDto> cadastrar(@RequestBody @Valid AtendimentoRequestDto atendimentoRequestDto){

        AtendimentoResponseDto response =  atendimentoService.cadastrar(atendimentoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDto>> listarTodos(){

        return ResponseEntity.ok(atendimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDto> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(atendimentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDto> atualizar(@PathVariable Long id,
                                                            @RequestBody @Valid AtendimentoRequestDto atendimentoRequestDto){

        return ResponseEntity.ok(atendimentoService.atualizar(id, atendimentoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        atendimentoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
