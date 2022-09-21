package com.ms.mscreditappraiser.application;

import com.ms.mscreditappraiser.model.Dto.*;
import com.ms.mscreditappraiser.model.SituationClient;
import com.ms.mscreditappraiser.utils.CardsPerClientResource;
import com.ms.mscreditappraiser.utils.ClientResourceClient;
import com.ms.mscreditappraiser.utils.IssueCardPublisher;
import com.ms.mscreditappraiser.utils.exception.ErrorInCommunicationMsException;
import com.ms.mscreditappraiser.utils.exception.ErrorRequestQueueCardException;
import com.ms.mscreditappraiser.utils.exception.StatusCodeNotFoundClientException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final ClientResourceClient clientResource;
    private final CardsPerClientResource cardsPerClientResource;
    private final IssueCardPublisher issueCardPublisher;

    public SituationClient getSituationClient(String cpf) throws StatusCodeNotFoundClientException, ErrorInCommunicationMsException {
        try{
            ResponseEntity<DataClient> dataClientResponse = clientResource.getQueryCpf(cpf);
            ResponseEntity<List<CardClient>> cardsClientResponse = cardsPerClientResource.getCardsByClient(cpf);

            return SituationClient
                    .builder()
                    .client(dataClientResponse.getBody())
                    .cards(cardsClientResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e){
            int statusCode = e.status();
            if (HttpStatus.NOT_FOUND.value() == statusCode){
                throw new StatusCodeNotFoundClientException();
            }
            throw new ErrorInCommunicationMsException(e.getMessage(), statusCode);
        }
    }

    public ReturnEvaluationClient consultEvaluationCreditClient(String cpf, Long income) throws
            StatusCodeNotFoundClientException, ErrorInCommunicationMsException{
        try{
            ResponseEntity<DataClient> dataClientResponse = clientResource.getQueryCpf(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsPerClientResource.getCardsIncomeLess(income);

            List<Card> cards = cardsResponse.getBody();
            var listCardsApproved = cards.stream().map(c -> {
                CardApproved approved = new CardApproved();
                DataClient dataClient = dataClientResponse.getBody();

                BigDecimal limitBasic = c.getLimiteBasico();
                BigDecimal ageClient = BigDecimal.valueOf(dataClient.getAge());

                var factor = ageClient.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = factor.multiply(limitBasic);

                approved.setCard(c.getNome());
                approved.setBrand(c.getBandeira());
                approved.setLimitApproved(limitApproved);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnEvaluationClient(listCardsApproved);
        } catch (FeignException.FeignClientException e){
            int statusCode = e.status();
            if (HttpStatus.NOT_FOUND.value() == statusCode){
                throw new StatusCodeNotFoundClientException();
            }
            throw new ErrorInCommunicationMsException(e.getMessage(), statusCode);
        }
    }

    public ProtocolRequestCard requestCardIssuance(DataRequestIssueCard data){
        try{
            issueCardPublisher.requestIssuanceReceive(data);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolRequestCard(protocol);
        } catch (Exception e){
            throw new ErrorRequestQueueCardException(e.getMessage());
        }
    }
}
