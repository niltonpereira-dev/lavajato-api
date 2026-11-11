package br.com.niltonpereira.lavajato_api.model.repository;

import br.com.niltonpereira.lavajato_api.model.entity.ItemAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemAtendimentoRepository extends JpaRepository<ItemAtendimento, Long> {
}
