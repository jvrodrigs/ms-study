package com.ms.mscards.application;

import com.ms.mscards.model.ClienteCartao;
import com.ms.mscards.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCardsByCpfClient(String cpf){
        return repository.findByCpf(cpf);
    }
}
