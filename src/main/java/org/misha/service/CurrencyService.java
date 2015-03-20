package org.misha.service;

import org.misha.wsdl.currency.ConversionRate;
import org.misha.wsdl.currency.ConversionRateResponse;
import org.misha.wsdl.currency.Currency;
import org.misha.wsdl.currency.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * author: misha
 * date: 3/20/15 12:46 AM.
 */
@Service
public class CurrencyService {
    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public Double getConversionRate(Currency fromCurrency, Currency toCurrency) {
        ConversionRate conversionRate = new ObjectFactory().createConversionRate();
        conversionRate.setFromCurrency(fromCurrency);
        conversionRate.setToCurrency(toCurrency);
        ConversionRateResponse response =
                (ConversionRateResponse) webServiceTemplate.marshalSendAndReceive(conversionRate);
        return response.getConversionRateResult();
    }
}

