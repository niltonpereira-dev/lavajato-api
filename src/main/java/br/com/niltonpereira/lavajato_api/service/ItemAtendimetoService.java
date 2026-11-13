package br.com.niltonpereira.lavajato_api.service;


import br.com.niltonpereira.lavajato_api.dto.ItemAtendimentoResponseDto;
import br.com.niltonpereira.lavajato_api.exception.NotFoundException;
import br.com.niltonpereira.lavajato_api.model.entity.ItemAtendimento;
import br.com.niltonpereira.lavajato_api.model.repository.ItemAtendimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemAtendimetoService {

    private final ItemAtendimentoRepository itemAtendimentoRepository;

    public ItemAtendimetoService(ItemAtendimentoRepository atendimentoRepository) {
        this.itemAtendimentoRepository = atendimentoRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemAtendimentoResponseDto> listarTodos(){

        return itemAtendimentoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ItemAtendimentoResponseDto buscarPorId(Long id){

        ItemAtendimento item = itemAtendimentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item de atendimento não encontrado"));

        return toResponse(item);
    }

    @Transactional
    public void deletar(Long id){

        ItemAtendimento item = itemAtendimentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item de atendimento não encontrado"));

        itemAtendimentoRepository.delete(item);
    }

    private ItemAtendimentoResponseDto toResponse(ItemAtendimento itemAtendimento){

        return new ItemAtendimentoResponseDto(
                itemAtendimento.getServico().getId(),
                itemAtendimento.getServico().getNome(),
                itemAtendimento.getValorCobrado()
        );
    }
}
