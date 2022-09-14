package com.ms.mscards.application.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {
    private String nome;
    private String brand;
    private BigDecimal limitReleased;
}
