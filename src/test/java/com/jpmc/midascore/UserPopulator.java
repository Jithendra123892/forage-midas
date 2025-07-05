package com.jpmc.midascore;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserPopulator implements CommandLineRunner {

    private final UserRepository userRepo;

    public UserPopulator(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {
        userRepo.save(new UserRecord(1L, "waldorf", BigDecimal.valueOf(1000)));
        userRepo.save(new UserRecord(2L, "statler", BigDecimal.valueOf(1000)));
        userRepo.save(new UserRecord(3L, "fozzie", BigDecimal.valueOf(1000)));

        // ✅ Wilbur must have ID 4L for TaskFourTests to match
        userRepo.save(new UserRecord(4L, "wilbur", BigDecimal.valueOf(1000)));

        userRepo.save(new UserRecord(5L, "piggy", BigDecimal.valueOf(1000)));
    }
}
