package com.sanedge.paymentgateway.utils;

import java.util.Arrays;
import java.util.List;

public class PaymentMethod {
    public static boolean isValidPaymentMethod(String paymentMethod) {
        List<String> validPaymentMethods = Arrays.asList(
                "alfamart", "indomart", "lawson", "dana", "ovo", "gopay",
                "linkaja", "jenius", "fastpay", "kudo", "bri", "mandiri",
                "bca", "bni", "bukopin", "e-banking", "visa", "mastercard",
                "discover", "american express", "paypal");

        String paymentMethodLower = paymentMethod.toLowerCase();

        for (String validMethod : validPaymentMethods) {
            if (paymentMethodLower.equals(validMethod.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}
