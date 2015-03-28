package org.misha.views;

import org.misha.wsdl.currency.Currency;

/**
 * author: misha
 * date: 3/28/15 2:42 AM.
 */
public class GoodView implements View {
    @Override
    public String getView(
            Double conversionRate, Currency toCurrency
    ) {
        return null;
    }
}
