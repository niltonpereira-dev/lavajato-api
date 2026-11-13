package br.com.niltonpereira.lavajato_api.controller;


import br.com.niltonpereira.lavajato_api.dto.ItemAtendimentoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.ItemAtendimentoResponseDto;
import br.com.niltonpereira.lavajato_api.service.ItemAtendimetoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-atendimento")
public class ItemAtendimentoController {

    private final ItemAtendimetoService itemAtendimetoService;

    public ItemAtendimentoController(ItemAtendimetoService itemAtendimetoService) {
        this.itemAtendimetoService = itemAtendimetoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemAtendimentoResponseDto>> listarTodos(){

        return ResponseEntity.ok(itemAtendimetoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemAtendimentoResponseDto> buscarPorId(@PathVariable Long id){

        return ResponseEntity.ok(itemAtendimetoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        itemAtendimetoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}
