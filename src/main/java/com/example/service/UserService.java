package com.example.service;

import com.example.entity.User;
import com.example.exception.InsufficientFundsException;
import com.example.exception.UserNotFoundException;
import com.example.model.AddToCardRequest;
import com.example.model.CardToCardRequest;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User topUp(AddToCardRequest payload) {
        if (payload.getAmount() == null || payload.getAccountNumber() == null) {
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
    public ResponseEntity<User> cardToCard(CardToCardRequest payload) {
    if (payload.getToAccountNumber() == null || payload.getFromAccountNumber() == null || payload.getAmount() == null) {
        log.error(" Göndərənin alıcının hesab nömrəsi və ya təyin olunan məbləğ null dur");
        Optional<User> optionalUser1 = userRepository.findByAccountNumber(payload.getFromAccountNumber());
        if (optionalUser1.isPresent()) {
            User sender = optionalUser1.get();


            Optional<User> optionalUser = userRepository.findByAccountNumber(payload.getToAccountNumber());
            if (optionalUser.isPresent()) {
                User reciver = optionalUser.get();

                if (sender.getBalance() < payload.getAmount()) {
                    log.error("Təyin olunan məbləğ göndərənin balansından coxdur");
                    throw new InsufficientFundsException("Hesabınızda yetərli məbləğ yoxdur.");
                }else{
                    sender.setBalance(sender.getBalance() - payload.getAmount());
                    log.info("Məbləğ göndərənin balansından cıxdı");
                    reciver.setBalance(reciver.getBalance() + payload.getAmount());
                    log.info("Məbləğ alıcının balansına əlavə edildi");

                    userRepository.save(sender);
                    userRepository.save(reciver);
                    if (payload.getAmount()>100.0){

                    }


                    return ResponseEntity.ok(sender);


                }
                }


            }

        }
    return ResponseEntity.ok(null);
    }

}
