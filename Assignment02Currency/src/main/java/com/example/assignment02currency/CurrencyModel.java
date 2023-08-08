package com.example.assignment02currency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class CurrencyModel {
    private Map<String, String> currencyNames;
    private Map<String, Double> exchangeRates;

    public CurrencyModel() {
        try {
            URL currencyNamesURL = new URL("https://openexchangerates.org/api/currencies.json");
            HttpURLConnection currencyNamesConnection = (HttpURLConnection) currencyNamesURL.openConnection();
            Type currencyNamesType = new TypeToken<Map<String, String>>() {}.getType();
            currencyNames = new Gson().fromJson(new InputStreamReader(currencyNamesConnection.getInputStream()), currencyNamesType);
            System.out.println("Currency Names: " + currencyNames);

            // Fetch exchange rates
            URL exchangeRatesURL = new URL("https://openexchangerates.org/api/latest.json?app_id=5d9a3887a52d489e837e7e283408f554");
            HttpURLConnection exchangeRatesConnection = (HttpURLConnection) exchangeRatesURL.openConnection();
            Type exchangeRatesType = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> exchangeRatesData = new Gson().fromJson(new InputStreamReader(exchangeRatesConnection.getInputStream()), exchangeRatesType);
            exchangeRates = (Map<String, Double>) exchangeRatesData.get("rates");
            System.out.println("Exchange Rates: " + exchangeRates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> getAllCurrencyNames() {
        return List.of(currencyNames.values().toArray(new String[0]));
    }

    public String getCurrencyCodeByName(String currencyName) {
        for (Map.Entry<String, String> entry : currencyNames.entrySet()) {
            if (entry.getValue().equals(currencyName)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode) {
        double fromCurrencyRate = exchangeRates.get(fromCurrencyCode);
        double toCurrencyRate = exchangeRates.get(toCurrencyCode);
        return toCurrencyRate / fromCurrencyRate;
    }
}
