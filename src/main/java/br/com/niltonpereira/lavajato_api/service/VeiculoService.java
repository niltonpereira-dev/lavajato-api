package br.com.niltonpereira.lavajato_api.service;

import br.com.niltonpereira.lavajato_api.dto.VeiculoRequestDto;
import br.com.niltonpereira.lavajato_api.dto.VeiculoResponseDto;
import br.com.niltonpereira.lavajato_api.exception.BadRequestException;
import br.com.niltonpereira.lavajato_api.exception.NotFoundException;
import br.com.niltonpereira.lavajato_api.model.entity.Cliente;
import br.com.niltonpereira.lavajato_api.model.entity.Veiculo;
import br.com.niltonpereira.lavajato_api.model.repository.ClienteRepository;
import br.com.niltonpereira.lavajato_api.model.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public VeiculoResponseDto cadastrar(VeiculoRequestDto veiculoRequestDto){

        if(veiculoRepository.existsByPlaca(veiculoRequestDto.placa())){
            throw new BadRequestException("Já existe um veículo cadastro com essa placa");
        }

        Cliente cliente = clienteRepository.findById(veiculoRequestDto.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        Veiculo veiculo = new Veiculo();

        veiculo.setPlaca(veiculoRequestDto.placa());
        veiculo.setMarca(veiculoRequestDto.marca());
        veiculo.setModelo(veiculoRequestDto.modelo());
        veiculo.setCor(veiculoRequestDto.cor());
        veiculo.setCliente(cliente);

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        return new VeiculoResponseDto(
                veiculoSalvo.getId(),
                veiculoSalvo.getPlaca(),
                veiculoSalvo.getMarca(),
                veiculoSalvo.getModelo(),
                veiculoSalvo.getCor(),
                veiculoSalvo.getCliente().getId()
        );
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDto> listar(){

        return veiculoRepository.findAll()
                .stream()
                .map(veiculo -> new VeiculoResponseDto(
                        veiculo.getId(),
                        veiculo.getPlaca(),
                        veiculo.getMarca(),
                        veiculo.getModelo(),
                        veiculo.getCor(),
                        veiculo.getCliente().getId()
                )).toList();
    }

    @Transactional(readOnly = true)
    public VeiculoResponseDto buscarPorId(Long id){

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado"));

        return new VeiculoResponseDto(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getCliente().getId()
        );
    }

    @Transactional
    public VeiculoResponseDto atualizar(Long id, VeiculoRequestDto veiculoRequestDto){

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado"));

        Cliente cliente = clienteRepository.findById(veiculoRequestDto.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        veiculo.setPlaca(veiculoRequestDto.placa());
        veiculo.setMarca(veiculoRequestDto.marca());
        veiculo.setModelo(veiculoRequestDto.modelo());
        veiculo.setCor(veiculoRequestDto.cor());
        veiculo.setCliente(cliente);

        Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);

        return new VeiculoResponseDto(
                veiculoAtualizado.getId(),
                veiculoAtualizado.getPlaca(),
                veiculoAtualizado.getMarca(),
                veiculoAtualizado.getModelo(),
                veiculoAtualizado.getCor(),
                veiculoAtualizado.getCliente().getId()
        );
    }

    @Transactional
    public void deletar(Long id){

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado"));

        veiculoRepository.delete(veiculo);
    }
}
