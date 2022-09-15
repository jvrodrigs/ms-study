package com.ms.mscreditappraiser.utils;

import com.ms.mscreditappraiser.model.Dto.Card;
import com.ms.mscreditappraiser.model.Dto.CardClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards" ,path = "/cards")
public interface CardsPerClientResource {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CardClient>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<Card>> getCardsIncomeLess(@RequestParam("renda") Long income);
}
