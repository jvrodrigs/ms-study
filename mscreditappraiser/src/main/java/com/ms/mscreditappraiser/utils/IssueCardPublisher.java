package com.ms.mscreditappraiser.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.mscreditappraiser.model.Dto.DataRequestIssueCard;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssueCardPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssueCard;

    public void requestIssuanceReceive(DataRequestIssueCard data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueIssueCard.getName(), json);
    }

    private String convertIntoJson(DataRequestIssueCard data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var jsonConvert = mapper.writeValueAsString(data);
        return jsonConvert;
    }
}
