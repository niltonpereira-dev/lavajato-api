package br.com.niltonpereira.lavajato_api.controller;

import br.com.niltonpereira.lavajato_api.dto.ServicoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.ServicoResponseDto;
import br.com.niltonpereira.lavajato_api.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDto> cadastrar(@Valid
                                                        @RequestBody ServicoRequestDto servicoRequestDto){

        ServicoResponseDto servico = servicoService.cadastrar(servicoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(servico);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDto>> listarTodos(){

        return ResponseEntity.ok(servicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> atualizar(@Valid
                                                        @PathVariable Long id,
                                                        @RequestBody ServicoRequestDto servicoRequestDto){
        return ResponseEntity.ok(servicoService.atualizar(id, servicoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        servicoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
