package com.ms.mscards.application.Dto;

import com.ms.mscards.model.CardBrand;
import com.ms.mscards.model.Card;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDtoSaveRequest {
    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal limit;

    public Card toModel(){
        return new Card(name, brand, income, limit);
    }
}
