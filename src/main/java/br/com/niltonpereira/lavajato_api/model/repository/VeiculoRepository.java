package br.com.niltonpereira.lavajato_api.model.repository;

import br.com.niltonpereira.lavajato_api.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    boolean existsByPlaca(String placa);
}
