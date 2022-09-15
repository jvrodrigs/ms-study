package com.ms.mscards.application.Dto;

import com.ms.mscards.model.CardBrand;
import com.ms.mscards.model.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDtoSaveRequest {
    private String nome;
    private CardBrand bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
