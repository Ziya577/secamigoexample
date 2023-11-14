package com.example.demo;

import com.example.entity.User;
import com.example.payload.AddToCardPayload;
import com.example.payload.CardToCardPayload;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/demo")
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;




    @PostMapping("/addtocard")
    public Optional<User> addToCard(@RequestBody @Valid AddToCardPayload payload){
        return Optional.ofNullable(userService.addToCard(payload));
    }

    @PostMapping("/cardtocard")
    public ResponseEntity<User> cardToCard(@RequestBody @Valid CardToCardPayload payload){
        return userService.cardToCard(payload);
    }
    }


