package com.ms.mscreditappraiser.model.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardApproved {
    private String card;
    private String brand;
    private BigDecimal limitApproved;
}
