package br.com.niltonpereira.lavajato_api.controller;


import br.com.niltonpereira.lavajato_api.dto.ClienteRequestDto;
import br.com.niltonpereira.lavajato_api.dto.ClienteResponseDto;
import br.com.niltonpereira.lavajato_api.model.entity.Cliente;
import br.com.niltonpereira.lavajato_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> cadastrar(@Valid @RequestBody ClienteRequestDto clienteRequestDto){
        ClienteResponseDto clienteCadastrado = clienteService.cadastrar(clienteRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarTodos(){
        List<ClienteResponseDto> clientes = clienteService.listarTodos();

        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid ClienteRequestDto clienteRequestDto){

        ClienteResponseDto cliente = clienteService.atualizar(id, clienteRequestDto);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
