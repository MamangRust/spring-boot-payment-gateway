package com.sanedge.paymentgateway.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class RupiahFormatter {
    public static String formatRupiah(String digit) {
        try {
            double digitNumber = Double.parseDouble(digit);

            Locale indonesiaLocale = new Locale("id", "ID");

            NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(indonesiaLocale);
            return rupiahFormat.format(digitNumber);

        } catch (NumberFormatException e) {
            return "Rp 0";
        }
    }
}