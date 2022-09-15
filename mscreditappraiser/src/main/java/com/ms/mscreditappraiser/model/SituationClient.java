package com.ms.mscreditappraiser.model;

import com.ms.mscreditappraiser.model.Dto.CardClient;
import com.ms.mscreditappraiser.model.Dto.DataClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituationClient {
    private DataClient client;
    private List<CardClient> cards;
}
