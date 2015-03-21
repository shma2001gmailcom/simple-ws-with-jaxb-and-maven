package org.misha.views;

import org.misha.wsdl.currency.Currency;

/**
 * author: misha
 * date: 3/21/15 9:47 AM.
 */
public interface View {
    String getView(Double conversionRate, Currency toCurrency, Currency fromCurrency);
}
