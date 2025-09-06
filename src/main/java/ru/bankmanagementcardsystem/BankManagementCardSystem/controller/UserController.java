package ru.bankmanagementcardsystem.BankManagementCardSystem.controller;


//import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
//import ru.banksystemcard.management.model.Card;
//import ru.banksystemcard.management.service.UserServiceManagementCard;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

//    private final UserServiceManagementCard serviceCard;
//
//    public UserController(UserServiceManagementCard serviceCard) {
//        this.serviceCard = serviceCard;
//    }

    /**
     * Test storage
     * @param page
     * @param size
     * @param sortBy
     * @param direction
     * @return
     */

    private final List<String> TEST = Stream.of(
            new String("cardOne1"),
            new String("cardTwo2"),
            new String("cardThree3")
    ).toList();

//    @GetMapping("/page")
//    public Page<Card> getAllCardsToUser(@RequestParam int page,
//                                        @RequestParam int size,
//                                        @RequestParam String sortBy,
//                                        @RequestParam String direction){
//        return serviceCard.getAllCardsToUser(page, size, sortBy, direction);
//    }

    @GetMapping("/user/card/all")
    public List<String> getAll1(){
        return TEST;
    }

    @GetMapping("/adm/card/all")
    public List<String> getAll2(){
        return TEST;
    }

    @GetMapping("/card/{id}")
    public String getCardById(@PathVariable Long id){
        return TEST.stream().filter(e -> e.contains("" + id)).toString();
    }
}

