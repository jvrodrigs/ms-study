package com.ms.mscards.model.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class DataRequestIssueCard {

    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal limitAccess;

}
