package org.misha.main;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * author: misha
 * date: 3/28/15 12:57 PM.
 */
public class Launcher {
    public static final String PROMPT
            = "\n\n==================================================================================" +
            "\nThe app converts\n" +
            "        JPY,\n" +
            "        GBP,\n" +
            "        CHF,\n" +
            "        EUR,\n" +
            "        USD,\n" +
            "        ILS,\n" +
            "        RUB\nto all rest currencies. \nThe Conversion is preparing through web service " +
            "'http://www.webserviceX.NET'.\n" +
            "==================================================================================\n\n";
    private static final Logger log = Logger.getLogger(Launcher.class);

    public static void main(final String[] args) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int size = Calculator.size();
        log.info(PROMPT);
        for (int i = 0; i < size; ++i) {
            log.info(Calculator.SELECT_CURRENCY);
            final Calculator calculator = Calculator.create(Calculator.readCurrency(in), Calculator.readAmount(in, 0));
            calculator.calculateView();
        }
    }
}
