package com.sanedge.paymentgateway.utils;

import java.util.Random;

public class VirtualCreditCardGenerator {
    public static Long generateRandomVCC() {
        Random random = new Random();

        String randomVirtualCreditCard = String.format("%.16f", random.nextDouble()).substring(2, 18);

        String visaCreditCard = "4" + randomVirtualCreditCard;

        String ccNumber = visaCreditCard.replaceAll("\\D", "");

        return Long.parseLong(ccNumber);
    }
}
