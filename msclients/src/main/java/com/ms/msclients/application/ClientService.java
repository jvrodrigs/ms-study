package com.ms.msclients.application;

import com.ms.msclients.model.Client;
import com.ms.msclients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client){
        return clientRepository.save(client);
    }

    public Optional<Client> getQueryingClientByCpf(String cpf){
        return clientRepository.findByCpf(cpf);
    }
}
