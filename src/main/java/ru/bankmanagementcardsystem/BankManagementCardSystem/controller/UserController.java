package ru.bankmanagementcardsystem.BankManagementCardSystem.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.Card;
import ru.bankmanagementcardsystem.BankManagementCardSystem.service.ServiceManagementCard;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final ServiceManagementCard service;

    public UserController(ServiceManagementCard service) {
        this.service = service;
    }


    @GetMapping("/page")
    public Page<Card> getAllCardsToUser(@RequestParam int page,
                                        @RequestParam int size,
                                        @RequestParam String sortBy,
                                        @RequestParam String direction) {
        return service.getAllCardsToUser(page, size, sortBy, direction);
    }

    @GetMapping("/user/card/all")
    public ResponseEntity<List<Card>> getUserCard(Authentication authentication) {
        return ResponseEntity.ok(service.getUserCard(authentication).stream().toList());
    }

    @PostMapping("/block/card")
    public ResponseEntity<?> getBlockCard(@RequestParam String numberCard,
                                          Authentication authentication) {
        service.getBlockCard(numberCard, authentication);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/card/traffic")
    public ResponseEntity<?> getTrafficCash(@RequestParam String numberOutput,
                                            @RequestParam String numberInput,
                                            @RequestParam Double sum) {
        service.getTrafficCash(numberOutput, numberInput, sum);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/card/balance/{numberCard}")
    public ResponseEntity<Double> getBalance(@PathVariable("numberCard") String numberCard){
        return ResponseEntity.ok(service.getBalance(numberCard));
    }
}

