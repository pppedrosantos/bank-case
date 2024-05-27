package com.br.api.casebank.util;

import java.util.Random;

public class Utils {

    public static String generateRandomNumber() {
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            randomNumber.append(random.nextInt(10));
        }

        return randomNumber.toString();
    }
}
