package com.jpmc.midascore;

import com.jpmc.midascore.entity.UserRecord;

import java.math.RoundingMode;

public class BalanceQuerier {
    public static int extractBalance(UserRecord user) {
        return user.getBalance().setScale(0, RoundingMode.DOWN).intValue();
    }
}
