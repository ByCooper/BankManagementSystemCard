package ru.bankmanagementcardsystem.BankManagementCardSystem.service.impl;

import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.Card;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.StatusCard;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.User;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.CardRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.UserRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.service.ServiceManagementCard;

import java.util.Collection;
import java.util.Set;

public class ServiceManagementCardImpl implements ServiceManagementCard {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public ServiceManagementCardImpl(CardRepository cardRepository, UserRepository userRepository, EntityManager entityManager) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public void getAddCard(String numberCard) {
        Card card = Card.builder()
                .numberCard(numberCard)
                .build();
        cardRepository.save(card);
    }

    @Override
    public void getBlockCard(String numberCard) {
        Card card = entityManager.find(Card.class, numberCard);
        if(card != null) {
            card = Card.builder(card)
                    .statusCard(StatusCard.BLOCK)
                    .build();
        } else {
            throw new RuntimeException("Card not found " + card);
        }
    }

    @Override
    public void getActivateCard(String numberCard) {
        Card card = entityManager.find(Card.class, numberCard);
        if(card != null) {
            card = Card.builder(card)
                    .statusCard(StatusCard.ACTIVE)
                    .build();
        } else {
            throw new RuntimeException("Card not found " + card);
        }
    }

    @Override
    public void getDeleteCard(String numberCard) {
        Card card = entityManager.find(Card.class, numberCard);
        if(card != null){
            entityManager.remove(card);
        } else {
            throw new RuntimeException("Card not found " + card);
        }

    }

    @Override
    public void getAddCardToClient(String nameClient, String numberCard) {
        User user = entityManager.find(User.class, nameClient);
        Card card = entityManager.find(Card.class, numberCard);
        if(user != null) {
            user.getCards().add(card);
        } else {
            throw new RuntimeException("User not found " + user);
        }
        if(card != null) {
            card = Card.builder(card)
                    .cardholder(user)
                    .build();
        } else {
            throw new RuntimeException("Card not found " + card);
        }
    }

    @Override
    public void getDeleteCardToClient(String nameClient, String numberCard) {
        Card card = entityManager.find(Card.class, numberCard);
        User user = entityManager.find(User.class, nameClient);
        user = User.builder(user)
                .cards(deleteCard(user.getCards(), card))
                .build();

    }


    @Override
    public Collection<Card> getAllCard() {
        return cardRepository.findAll();
    }

    private Set<Card> deleteCard(Set<Card> cards, Card card){
        cards.remove(card);
        return cards;
    }

    @Override
    public Collection<Card> getUserCard(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName()).get().getCards();
    }

    @Override
    public void getBlockCard(String numberCard, Authentication authentication) {
        Card card = entityManager.find(Card.class, numberCard);
        card = Card.builder(card)
                .statusCard(StatusCard.BLOCK)
                .build();
    }

    @Override
    public void getTrafficCash(String numberOutput, String numberInput, double sum) {
        Card cardOut = entityManager.find(Card.class, numberOutput);
        Card cardIn = entityManager.find(Card.class, numberInput);
        cardOut = Card.builder(cardOut)
                .cardBalance(cardOut.getCardBalance() - sum)
                .build();
        cardIn = Card.builder(cardIn)
                .cardBalance(cardIn.getCardBalance() + sum)
                .build();
    }

    @Override
    public Double getBalance(String numberCard) {
        return cardRepository.findByName(numberCard).get().getCardBalance();
    }

    @Override
    public Page<Card> getAllCardsToUser(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return cardRepository.findAll(pageable);
    }
}
