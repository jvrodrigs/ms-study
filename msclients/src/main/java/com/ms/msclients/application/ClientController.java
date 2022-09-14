package com.ms.msclients.application;

import com.ms.msclients.application.Dto.ClientDtoSaveRequest;
import com.ms.msclients.model.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String status(){
        log.info("Status do microservice de cliente.");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientDtoSaveRequest request){
        var newClient = request.toModel();
        clientService.save(newClient);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(newClient.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity getQueryCpf(@RequestParam("cpf") String cpf){
        var getClientByCpf = clientService.getQueryingClientByCpf(cpf);

        if (getClientByCpf.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(getClientByCpf);
    }
}
