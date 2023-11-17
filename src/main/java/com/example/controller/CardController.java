package com.example.controller;

import com.example.entity.User;
import com.example.model.AddToCardRequest;
import com.example.model.CardToCardRequest;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/demo")
@RequiredArgsConstructor
public class CardController {
    private final UserService userService;




    @PostMapping("/addtocard")
    public Optional<User> topUp(@RequestBody @Valid AddToCardRequest payload){
        return Optional.ofNullable(userService.topUp(payload));
    }

    @PostMapping("/cardtocard")
    public ResponseEntity<User> cardToCard(@RequestBody @Valid CardToCardRequest payload){
        return userService.cardToCard(payload);
    }
    }


