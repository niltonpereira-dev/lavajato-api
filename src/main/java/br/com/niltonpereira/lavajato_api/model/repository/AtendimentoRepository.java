package br.com.niltonpereira.lavajato_api.model.repository;

import br.com.niltonpereira.lavajato_api.model.entity.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
