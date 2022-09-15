package com.ms.mscards.application.Dto;

import com.ms.mscards.model.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limitReleased;

    public static CardsByClientResponse fromModel(ClienteCartao model) {
        return new CardsByClientResponse(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()
        );
    }
}
