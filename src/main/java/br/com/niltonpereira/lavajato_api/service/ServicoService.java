package br.com.niltonpereira.lavajato_api.service;

import br.com.niltonpereira.lavajato_api.dto.ServicoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.ServicoResponseDto;
import br.com.niltonpereira.lavajato_api.exception.BadRequestException;
import br.com.niltonpereira.lavajato_api.exception.NotFoundException;
import br.com.niltonpereira.lavajato_api.model.entity.Servico;
import br.com.niltonpereira.lavajato_api.model.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public ServicoResponseDto cadastrar( ServicoRequestDto servicoRequestDto){

        if(servicoRepository.existsByNomeIgnoreCase(servicoRequestDto.nome())){
            throw new BadRequestException("Já existe um serviço cadadstrado com esse nome.");
        }

        Servico servico = new Servico();

        servico.setNome(servicoRequestDto.nome());
        servico.setDescricao(servicoRequestDto.descricao());
        servico.setValor(servicoRequestDto.valor());
        servico.setAtivo(servicoRequestDto.ativo() == null ? true : servicoRequestDto.ativo());

        Servico servicoSalvo = servicoRepository.save(servico);

        return new ServicoResponseDto(
                servicoSalvo.getId(),
                servicoSalvo.getNome(),
                servicoSalvo.getDescricao(),
                servicoSalvo.getValor(),
                servicoSalvo.getAtivo()
        );
    }

    @Transactional(readOnly = true)
    public List<ServicoResponseDto> listarTodos(){

        return servicoRepository.findAll()
                .stream()
                .map(servico -> new ServicoResponseDto(
                        servico.getId(),
                        servico.getNome(),
                        servico.getDescricao(),
                        servico.getValor(),
                        servico.getAtivo()
                )).toList();
    }

    @Transactional(readOnly = true)
    public ServicoResponseDto buscarPorId(Long id){

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        return new ServicoResponseDto(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getValor(),
                servico.getAtivo()
        );
    }

    @Transactional
    public ServicoResponseDto atualizar(Long id, ServicoRequestDto servicoRequestDto){

        if(servicoRepository.existsByNomeIgnoreCaseAndIdNot(servicoRequestDto.nome(), id)){
            throw new BadRequestException("Já existe outro serviço cadastrado com esse nome.");
        }

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        servico.setNome(servicoRequestDto.nome());
        servico.setDescricao(servicoRequestDto.descricao());
        servico.setValor(servicoRequestDto.valor());

        if (servicoRequestDto.ativo() != null){
            servico.setAtivo(servicoRequestDto.ativo());
        }

        Servico servicoAtualizado = servicoRepository.save(servico);

        return new ServicoResponseDto(
                servicoAtualizado.getId(),
                servicoAtualizado.getNome(),
                servicoAtualizado.getDescricao(),
                servicoAtualizado.getValor(),
                servicoAtualizado.getAtivo()
        );
    }

    @Transactional
    public void deletar(Long id){

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        servicoRepository.delete(servico);
    }
}
