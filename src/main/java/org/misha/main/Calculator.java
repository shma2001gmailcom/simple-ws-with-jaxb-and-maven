package org.misha.main;

import org.apache.log4j.Logger;
import org.misha.service.CurrencyService;
import org.misha.views.StringView;
import org.misha.wsdl.currency.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: misha
 * date: 3/20/15 12:43 AM.
 */
public class Calculator {
    private static Logger log = Logger.getLogger(Calculator.class);
    private final static List<Currency> list = new ArrayList<Currency>(4);

    static {
        list.add(Currency.EUR);
        list.add(Currency.USD);
        list.add(Currency.ILS);
        list.add(Currency.RUB);
    }

    private final Currency fromCurrency;
    private final int fromAmount;

    private Calculator(final Currency currency, int amount) {
        fromCurrency = currency;
        fromAmount = amount;
    }

    public static Calculator create(Currency currency, int amount) {
        return new Calculator(currency, amount);
    }

    public static int  size() {
        return list.size();
    }

    public static Currency readCurrency(BufferedReader in) throws IOException {
        Currency fromCurrency;
        String line = in.readLine();
        fromCurrency = findCurrencyName(line);
        while(fromCurrency == null) {
            log.info("Enter 'U' for USD, 'R' for RUB or 'I' for ILS or CTRL-C to exit:");
            fromCurrency= findCurrencyName(line);
        }
        return fromCurrency;
    }

    public static int readAmount(BufferedReader in, int amount) throws IOException {
        String line;
        while (amount == 0) {
            line = in.readLine();
            try {
                amount = Integer.parseInt(line);
            } catch (final NumberFormatException e) {
                log.info("Please enter some integer value or type CTRL-C to exit.");
                amount = 0;
            }
        }
        return amount;
    }

    private static Currency findCurrencyName(String line) {
        String name;
        for(Currency currency :list) {
            name = currency.name();
            if(name.startsWith(line)) {
                log.info(name + " selected. Enter amount to convert:");
                return currency;
            }
        }
        return null;
    }

    public void calculateView() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CurrencyService currencyService = context.getBean(CurrencyService.class);
        StringView stringView = context.getBean(StringView.class);
        log.info(stringView.getView((double)fromAmount, fromCurrency));
        list.remove(fromCurrency);
        for(Currency another:list) {
            log.info(stringView.getView(fromAmount*currencyService.getConversionRate(fromCurrency, another), another));
        }
        list.add(fromCurrency);
    }
}
