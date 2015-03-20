package org.misha.main;

import org.apache.log4j.Logger;
import org.misha.service.CurrencyService;
import org.misha.views.StringView;
import org.misha.wsdl.currency.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author: misha
 * date: 3/20/15 12:43 AM.
 */
public class Main {
    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CurrencyService currencyService = context.getBean(CurrencyService.class);
        StringView stringView = context.getBean(StringView.class);
        Currency fromCurrency = Currency.USD;
        Currency toCurrency = Currency.ILS;
        Currency fromCurrencyAnother = Currency.ILS;
        Currency toCurrencyAnother = Currency.RUB;
        Double conversionRateAnother = currencyService.getConversionRate(fromCurrencyAnother, toCurrencyAnother);
        Double conversionRate = currencyService.getConversionRate(fromCurrency, toCurrency);
        log.info(stringView.getView(conversionRate, toCurrency, fromCurrency));
        log.info(stringView.getView(conversionRateAnother, toCurrencyAnother, fromCurrencyAnother));
        log.info(stringView.getView(conversionRate * conversionRateAnother, toCurrencyAnother, fromCurrency));
    }
}
