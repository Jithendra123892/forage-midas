package com.jpmc.midascore.controller;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class BalanceController {

    private final UserRepository userRepo;

    public BalanceController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/balance")
    public Balance getBalance(@RequestParam Long userId) {
        return userRepo.findById(userId)
                .map(user -> new Balance(user.getBalance()))
                .orElse(new Balance(BigDecimal.ZERO));
    }
}
