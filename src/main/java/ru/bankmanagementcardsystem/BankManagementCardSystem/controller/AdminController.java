package ru.bankmanagementcardsystem.BankManagementCardSystem.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.Card;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.StatusCard;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.CardRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.repository.UserRepository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.service.ServiceManagementCard;

import java.util.List;

@RestController
@RequestMapping("/adm")
public class AdminController {

    private final ServiceManagementCard service;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public AdminController(ServiceManagementCard service, UserRepository userRepository, CardRepository cardRepository) {
        this.service = service;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @PostMapping("/createcard")
    public ResponseEntity<?> getAddCard(@RequestBody String numberCard) {
        if (cardRepository.findByName(numberCard).get().equals(numberCard)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            service.getAddCard(numberCard);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/blockcard")
    public ResponseEntity<?> getBlockCard(@RequestBody String numberCard) {
        if (cardRepository.findByName(numberCard).get().getStatusCard() == StatusCard.BLOCK) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            service.getBlockCard(numberCard);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/activatecard")
    public ResponseEntity<?> getActivateCard(@RequestBody String numberCard) {
        if (cardRepository.findByName(numberCard).get().getStatusCard() == StatusCard.ACTIVE) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            service.getActivateCard(numberCard);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deletecard")
    public ResponseEntity<?> getDeleteCard(@RequestBody String numberCard) {
        if (cardRepository.findByName(numberCard).isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            service.getDeleteCard(numberCard);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addtouser")
    public ResponseEntity<?> getAddCardToClient(@RequestParam("nameClient") String nameClient,
                                                @RequestParam("numberCard") String numberCard) {
        if (userRepository.findByUsername(nameClient).get().getCards().equals(cardRepository.findByName(numberCard))) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            service.getAddCardToClient(nameClient, numberCard);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deletetouser")
    public ResponseEntity<?> getDeleteCardToClient(@RequestParam("nameClient") String nameClient,
                                                @RequestParam("numberCard") String numberCard) {
        if (!userRepository.findByUsername(nameClient).get().getCards().equals(cardRepository.findByName(numberCard))) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            service.getDeleteCardToClient(nameClient, numberCard);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allcard")
    public ResponseEntity<List<Card>> getAllCard(){
        List<Card> list = service.getAllCard().stream().toList();
        return ResponseEntity.ok(list);
    }

}
