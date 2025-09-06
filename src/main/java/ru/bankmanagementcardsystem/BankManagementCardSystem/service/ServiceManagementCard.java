package ru.bankmanagementcardsystem.BankManagementCardSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.Card;

import java.util.Collection;

public interface ServiceManagementCard {

    void getAddCard(String numberCard);
    void getBlockCard(String numberCard);
    void getActivateCard(String numberCard);
    void getDeleteCard(String numberCard);
    Collection<Card> getAllCard();

    void getAddCardToClient(String nameClient, String numberCard);
    void getDeleteCardToClient(String nameClient, String numberCard);
    Collection<Card> getUserCard(Authentication authentication);
    void getBlockCard(String numberCard, Authentication authentication);
    void getTrafficCash(String numberOutput, String numberInput, double sum);
    Double getBalance(String numberCard);
    Page<Card> getAllCardsToUser(int page, int size, String sortBy, String direction);
}
