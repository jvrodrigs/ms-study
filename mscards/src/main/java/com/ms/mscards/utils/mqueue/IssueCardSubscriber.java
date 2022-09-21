package com.ms.mscards.utils.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.mscards.model.Cartao;
import com.ms.mscards.model.ClienteCartao;
import com.ms.mscards.model.dto.DataRequestIssueCard;
import com.ms.mscards.repository.CardRepository;
import com.ms.mscards.repository.ClienteCartaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class IssueCardSubscriber {

    private final CardRepository cardRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.create-cards}")
    public void requestIssuanceReceive(@Payload String payload){
       try{
           log.info("Message received from the queue RabbitMQ!");
           var mapper = new ObjectMapper();
           DataRequestIssueCard dataRequestIssueCard = mapper
                   .readValue(payload, DataRequestIssueCard.class);
           Cartao card = cardRepository.findById(dataRequestIssueCard.getIdCard()).orElseThrow();
           ClienteCartao clientCard = new ClienteCartao();
           clientCard.setCartao(card);
           clientCard.setCpf(dataRequestIssueCard.getCpf());
           clientCard.setLimite(dataRequestIssueCard.getLimitAccess());

           clienteCartaoRepository.save(clientCard);
       } catch (Exception e){
            e.printStackTrace();
       }
    }


}
