package br.com.niltonpereira.lavajato_api.service;

import br.com.niltonpereira.lavajato_api.dto.AtendimentoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.AtendimentoResponseDto;
import br.com.niltonpereira.lavajato_api.dto.ItemAtendimentoResponseDto;
import br.com.niltonpereira.lavajato_api.exception.BadRequestException;
import br.com.niltonpereira.lavajato_api.exception.NotFoundException;
import br.com.niltonpereira.lavajato_api.model.entity.Atendimento;
import br.com.niltonpereira.lavajato_api.model.entity.ItemAtendimento;
import br.com.niltonpereira.lavajato_api.model.entity.Servico;
import br.com.niltonpereira.lavajato_api.model.entity.Veiculo;
import br.com.niltonpereira.lavajato_api.model.repository.AtendimentoRepository;
import br.com.niltonpereira.lavajato_api.model.repository.ServicoRepository;
import br.com.niltonpereira.lavajato_api.model.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final VeiculoRepository veiculoRepository;
    private final ServicoRepository servicoRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository,
                              VeiculoRepository veiculoRepository, ServicoRepository servicoRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.veiculoRepository = veiculoRepository;
        this.servicoRepository = servicoRepository;
    }


    @Transactional
    public AtendimentoResponseDto cadastrar(AtendimentoRequestDto atendimentoRequestDto){

        Veiculo veiculo = veiculoRepository.findById(atendimentoRequestDto.veiculoId())
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado"));

        Atendimento atendimento = new Atendimento();
        atendimento.setVeiculo(veiculo);

        BigDecimal valorTotal = BigDecimal.ZERO;

        List<ItemAtendimento> itens = new ArrayList<>();

        Set<Long> servicosAdicionados = new HashSet<>();

        for (var itemDto : atendimentoRequestDto.itens()){
            if(!servicosAdicionados.add(itemDto.servicoId())){
                throw new BadRequestException("O mesmo serviço não pode ser adicionado duas vezes");
            }

            Servico servico = servicoRepository.findById(itemDto.servicoId())
                    .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

            if(Boolean.FALSE.equals(servico.getAtivo())){
                throw new BadRequestException("Não é possivel adicionar um serviço inativo");
            }

            ItemAtendimento item = new ItemAtendimento();

            item.setAtendimento(atendimento);
            item.setServico(servico);
            item.setValorCobrado(servico.getValor());

            itens.add(item);

            valorTotal = valorTotal.add(servico.getValor());
        }

        atendimento.setItens(itens);
        atendimento.setValorTotal(valorTotal);

        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);

        return toResponse(atendimentoSalvo);




    }

    @Transactional(readOnly = true)
    public List<AtendimentoResponseDto> listarTodos(){
        return atendimentoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AtendimentoResponseDto buscarPorId(Long id){

        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Atendimento não encontrado"));

        return toResponse(atendimento);
    }
    private AtendimentoResponseDto toResponse (Atendimento atendimento){

        List<ItemAtendimentoResponseDto> itens = atendimento.getItens()
                .stream()
                .map(item -> new ItemAtendimentoResponseDto(
                        item.getServico().getId(),
                        item.getServico().getNome(),
                        item.getValorCobrado()
                ))
                .toList();

        return new AtendimentoResponseDto(
                atendimento.getId(),
                atendimento.getVeiculo().getId(),
                atendimento.getDataAtendimento(),
                atendimento.getValorTotal(),
                itens
        );
    }

    @Transactional
    public AtendimentoResponseDto atualizar(Long id,
                                            AtendimentoRequestDto atendimentoRequestDto){

        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Atendimento não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(atendimentoRequestDto.veiculoId())
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado"));

        BigDecimal valorTotal = BigDecimal.ZERO;

        List<ItemAtendimento> itens = new ArrayList<>();

        Set<Long> servicosAdicionados = new HashSet<>();

        for (var itemDto : atendimentoRequestDto.itens()){

            if(!servicosAdicionados.add(itemDto.servicoId())){
                throw new BadRequestException("O mesmo serviço não pode ser adicionado duas vezes");
            }

            Servico servico = servicoRepository.findById(itemDto.servicoId())
                    .orElseThrow(() -> new NotFoundException("Não é possivel adicionar um serviço inativo"));

            if(Boolean.FALSE.equals(servico.getAtivo())){
                throw new BadRequestException("Não é possivel adicionar um serviço inativo");
            }

            ItemAtendimento item = new ItemAtendimento();

            item.setAtendimento(atendimento);
            item.setServico(servico);
            item.setValorCobrado(servico.getValor());

            itens.add(item);

            valorTotal = valorTotal.add(servico.getValor());

            atendimento.setVeiculo(veiculo);
            atendimento.setItens(itens);
            atendimento.setValorTotal(valorTotal);

            Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);

            return toResponse(atendimentoSalvo);

        }
    }

    @Transactional
    public void deletar(Long id){

        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Atendimento não encontrado"));

        atendimentoRepository.delete(atendimento);
    }
}
