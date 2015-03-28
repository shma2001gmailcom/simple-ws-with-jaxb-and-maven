package org.misha.views;

import org.misha.wsdl.currency.Currency;
import org.springframework.stereotype.Service;

/**
 * author: misha
 * date: 3/20/15 1:45 AM.
 */
@Service
public class StringView implements View {
    public String getView(Double number, Currency toCurrency) {
        return String.format("%s %s;", String.format("%1$.2f", number), toCurrency);
    }
}
