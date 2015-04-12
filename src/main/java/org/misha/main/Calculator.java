package org.misha.main;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.misha.service.CurrencyService;
import org.misha.views.StringView;
import org.misha.views.View;
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
    public static final String SELECT_AMOUNT = "\nPlease enter some integer value or type CTRL-C to exit.";
    public static final String SELECT_CURRENCY = "\nPlease select currency to convert." +
            "\n-- Type 'e' for EUR, " +
            "\n--      'u' for USD, " +
            "\n--      'r' for RUB, " +
            "\n--      'i' for ILS, " +
            "\n--      'j' for JPY, " +
            "\n--      'c' for CHF, " +
            "\n--      'g' for GBP, " +
            "\n--      'CTRL-C' to exit:";
    public static final String THE_NEXT_LINE = "\n     The next line: \n'";
    public static final String IS_INCORRECT_INPUT = "'\n     is incorrect input. ";
    public static final String THE_LINE = "\n\nThe line '";
    public static final String COULDN_T_BE_PARSED_AS_INTEGER_VALUE = "' couldn't be parsed as integer value.\n";
    public static final String SELECTED_ENTER_AMOUNT_TO_CONVERT = " selected. Enter amount to convert:";
    public static final String PLEASE_WAIT_WHILE_DATA_IS_TRANSFERRING = "Please wait while data is transferring...\n";
    private static Logger log = Logger.getLogger(Calculator.class);
    private final static List<Currency> list = new ArrayList<Currency>(4);

    static {
        list.add(Currency.JPY);
        list.add(Currency.GBP);
        list.add(Currency.CHF);
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

    public static int size() {
        return list.size();
    }

    public static Currency readCurrency(BufferedReader in) throws IOException {
        Currency fromCurrency;
        String line = in.readLine();
        fromCurrency = findCurrencyName(line);
        while (fromCurrency == null) {
            log.error(THE_NEXT_LINE + line + IS_INCORRECT_INPUT + SELECT_CURRENCY);
            line = in.readLine();
            fromCurrency = findCurrencyName(line);
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
                log.error(THE_LINE + line + COULDN_T_BE_PARSED_AS_INTEGER_VALUE);
                log.info(SELECT_AMOUNT);
                amount = 0;
            }
        }
        return amount;
    }

    private static Currency findCurrencyName(String line) {
        String name;
        for (Currency currency : list) {
            name = currency.name();
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(line)) {
                System.exit(1);
            }
            if (name.startsWith(line.toUpperCase())) {
                log.info(name + SELECTED_ENTER_AMOUNT_TO_CONVERT);
                return currency;
            }
        }
        return null;
    }

    public void calculateView() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CurrencyService currencyService = context.getBean(CurrencyService.class);
        View stringView = context.getBean(StringView.class);
        log.info(stringView.getView((double) fromAmount, fromCurrency));
        list.remove(fromCurrency);
        log.info(PLEASE_WAIT_WHILE_DATA_IS_TRANSFERRING);
        String answer = "\n\n" + stringView
                .getView(fromAmount * currencyService.getConversionRate(fromCurrency, fromCurrency), fromCurrency);
        for (Currency another : list) {
            answer += '\n' + stringView
                    .getView(fromAmount * currencyService.getConversionRate(fromCurrency, another), another);
        }
        list.add(fromCurrency);
        log.info(answer + "\n\n");
    }
}
