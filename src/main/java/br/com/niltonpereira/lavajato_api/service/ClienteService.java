package br.com.niltonpereira.lavajato_api.service;


import br.com.niltonpereira.lavajato_api.dto.ClienteRequestDto;
import br.com.niltonpereira.lavajato_api.dto.ClienteResponseDto;
import br.com.niltonpereira.lavajato_api.exception.NotFoundException;
import br.com.niltonpereira.lavajato_api.model.entity.Cliente;
import br.com.niltonpereira.lavajato_api.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteResponseDto cadastrar(ClienteRequestDto clienteRequestDto){

        Cliente cliente = new Cliente();

        cliente.setNome(clienteRequestDto.nome());
        cliente.setTelefone(clienteRequestDto.telefone());
        cliente.setEmail(clienteRequestDto.email());

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDto(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getTelefone(),
                clienteSalvo.getEmail(),
                clienteSalvo.getDataCadastro()
        );
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDto> listarTodos(){

        return clienteRepository.findAll()
                .stream()
                .map(cliente -> new ClienteResponseDto(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getTelefone(),
                        cliente.getEmail(),
                        cliente.getDataCadastro()
                ))
                .toList();
    }

    @Transactional
    public ClienteResponseDto atualizar(Long id, ClienteRequestDto clienteRequestDto){

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        cliente.setNome(clienteRequestDto.nome());
        cliente.setTelefone(clienteRequestDto.telefone());
        cliente.setEmail(clienteRequestDto.email());

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return new ClienteResponseDto(
                clienteAtualizado.getId(),
                clienteAtualizado.getNome(),
                clienteAtualizado.getTelefone(),
                clienteAtualizado.getEmail(),
                clienteAtualizado.getDataCadastro()
        );
    }

    public void deletar(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        clienteRepository.delete(cliente);
    }
}
