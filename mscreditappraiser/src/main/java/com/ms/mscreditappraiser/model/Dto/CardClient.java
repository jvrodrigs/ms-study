package com.ms.mscreditappraiser.model.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardClient {
    private String name;
    private String brand;
    private BigDecimal limitReleased;
}
