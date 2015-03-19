package org.misha.views;

import org.misha.wsdl.currency.Currency;
import org.springframework.stereotype.Service;

/**
 * author: misha
 * date: 3/20/15 1:45 AM.
 */
@Service
public class StringView {
    public String getView(Double conversionRate, Currency toCurrency, Currency fromCurrency) {
        return String.format("You can get %s %s for 1 %s.", String.format("%1$,.4f", conversionRate), toCurrency, fromCurrency);
    }
}
