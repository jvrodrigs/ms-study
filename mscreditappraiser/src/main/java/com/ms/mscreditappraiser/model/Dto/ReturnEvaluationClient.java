package com.ms.mscreditappraiser.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnEvaluationClient {
    private List<CardApproved> cardApproveds;
}
