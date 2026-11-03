package br.com.niltonpereira.lavajato_api.model.repository;

import br.com.niltonpereira.lavajato_api.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
