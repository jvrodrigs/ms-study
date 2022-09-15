package com.ms.mscards.application;

import com.ms.mscards.application.Dto.CardDtoSaveRequest;
import com.ms.mscards.application.Dto.CardsByClientResponse;
import com.ms.mscards.model.Cartao;
import com.ms.mscards.model.ClienteCartao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
@Slf4j
public class CardsController {

    private final CardsService service;
    private final ClienteCartaoService clienteCartaoService;
    @GetMapping
    public String status(){
        log.info("Verify code status microservice cards!");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CardDtoSaveRequest request){
        var newCard = request.toModel();
        var bodyCard = service.save(newCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyCard);
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCardsIncomeLess(@RequestParam("renda") Long income){
        List<Cartao> listResponse = service.getCardsIncomeLowerEqual(income);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByClientResponse>> getCardsByClient (@RequestParam("cpf") String cpf){
        List<ClienteCartao> listResponse = clienteCartaoService.listCardsByCpfClient(cpf);
        List<CardsByClientResponse> resultList = listResponse.stream().map(CardsByClientResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
