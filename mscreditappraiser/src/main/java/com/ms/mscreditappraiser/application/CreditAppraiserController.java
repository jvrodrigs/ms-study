package com.ms.mscreditappraiser.application;

import com.ms.mscreditappraiser.model.Dto.DateEvaluation;
import com.ms.mscreditappraiser.model.Dto.ReturnEvaluationClient;
import com.ms.mscreditappraiser.model.SituationClient;
import com.ms.mscreditappraiser.utils.exception.ErrorInCommunicationMsException;
import com.ms.mscreditappraiser.utils.exception.StatusCodeNotFoundClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
@Slf4j
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status(){
        log.info("Status code test ms credit appraiser!");
        return "ok";
    }

    @GetMapping(value = "situation-client", params = "cpf")
    public ResponseEntity consultSituationByClient(@RequestParam("cpf") String cpf) {
        try {
            SituationClient situationClient = creditAppraiserService.getSituationClient(cpf);
            return ResponseEntity.ok(situationClient);
        } catch (StatusCodeNotFoundClientException e){
            return ResponseEntity.notFound().build();
        } catch (ErrorInCommunicationMsException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatusCode())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity consultEvaluationCredit(@RequestBody DateEvaluation data){
        try {
            ReturnEvaluationClient returnEvaluationClient = creditAppraiserService
                    .consultEvaluationCreditClient(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(returnEvaluationClient);
        } catch (StatusCodeNotFoundClientException e){
            return ResponseEntity.notFound().build();
        } catch (ErrorInCommunicationMsException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatusCode())).body(e.getMessage());
        }
    }
}
