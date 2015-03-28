package org.misha.main;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.misha.main.Calculator.*;

/**
 * author: misha
 * date: 3/28/15 12:57 PM.
 */
public class Launcher {
    private static Logger log = Logger.getLogger(Launcher.class);

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int size = size();
        for (int i = 0; i < size;++i) {
            log.info("Enter 'U' for USD, 'R' for RUB or 'I' for ILS or CTRL-C to exit:");
            Calculator calculator = create(readCurrency(in), readAmount(in, 0));
            calculator.calculateView();
        }
    }
}
