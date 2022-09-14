package com.ms.mscards.application;

import com.ms.mscards.model.Card;
import com.ms.mscards.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardsService {

    private final CardRepository cardRepository;

    @Transactional
    public Card save(Card cards){
        return cardRepository.save(cards);
    }

    public List<Card> getCardsIncomeLowerEqual(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return cardRepository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
