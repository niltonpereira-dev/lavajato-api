package br.com.niltonpereira.lavajato_api.service;


import br.com.niltonpereira.lavajato_api.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


}
