package com.ms.msclients.application.Dto;

import com.ms.msclients.model.Client;
import lombok.Data;

@Data
public class ClientDtoSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Client toModel(){
        return new Client(name, cpf, age);
    }
}
