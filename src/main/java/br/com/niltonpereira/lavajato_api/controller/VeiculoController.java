package br.com.niltonpereira.lavajato_api.controller;


import br.com.niltonpereira.lavajato_api.dto.VeiculoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.VeiculoResponseDto;
import br.com.niltonpereira.lavajato_api.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDto> cadastrar(@Valid
                                                        @RequestBody VeiculoRequestDto veiculoRequestDto){

        VeiculoResponseDto response = veiculoService.cadastrar(veiculoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDto>> listar(){

        return ResponseEntity.ok(veiculoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDto> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDto> atualizar(@Valid
                                                        @PathVariable Long id,
                                                        @RequestBody VeiculoRequestDto veiculoRequestDto){

        return ResponseEntity.ok(veiculoService.atualizar(id, veiculoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        veiculoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
