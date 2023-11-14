package com.example.service;

import com.example.entity.User;
import com.example.exception.InsufficientFundsException;
import com.example.exception.UserNotFoundException;
import com.example.payload.AddToCardPayload;
import com.example.payload.CardToCardPayload;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User addToCard(AddToCardPayload payload) {
        if (payload.getAmount() != null && payload.getAccountNumber() != null) {
            log.error("məbləğ və ya hesab nömrəsi təyin edilmədi");
        Optional<User> foundedAccountNumber = userRepository.findByAccountNumber(payload.getAccountNumber());
        if (foundedAccountNumber.isPresent()){
            User user = foundedAccountNumber.get();
            user.setBalance(user.getBalance()+ payload.getAmount());
            userRepository.save(user);

            return user;

        } else throw new UserNotFoundException("İstifadəçi tapilmadi");
    }else throw new NullPointerException("məbləğ və yaxud Hesab nömrəsi yanlış qeyd olunub");


        }


@Transactional
    public ResponseEntity<User> cardToCard(CardToCardPayload payload) {
    if (payload.getToAccountNumber() != null && payload.getFromAccountNumber() != null && payload.getAmount() != null) {
        log.error(" Göndərənin alıcının hesab nömrəsi və ya təyin olunan məbləğ null dur");
        Optional<User> user1 = userRepository.findByAccountNumber(payload.getFromAccountNumber());
        if (user1.isPresent()) {
            User sender = user1.get();


            Optional<User> user2 = userRepository.findByAccountNumber(payload.getToAccountNumber());
            if (user2.isPresent()) {
                User reciver = user2.get();

                if (sender.getBalance() >= payload.getAmount()) {
                    log.error("Təyin olunan məbləğ göndərənin balansından coxdur");

                    sender.setBalance(sender.getBalance() - payload.getAmount());
                    log.info("Məbləğ göndərənin balansından cıxdı");
                    reciver.setBalance(reciver.getBalance() + payload.getAmount());
                    log.info("Məbləğ alıcının balansına əlavə edildi");

                    userRepository.save(sender);
                    userRepository.save(reciver);
                    if (payload.getAmount()>100.0){

                    }


                    return ResponseEntity.ok(sender);


                } else if (sender.getBalance() < payload.getAmount()) {
                    throw new InsufficientFundsException("Hesabınızda yetərli məbləğ yoxdur.");
                }


            }

        }
    }return ResponseEntity.ok(null);
    }

}
