package com.example.assignment02currency;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CurrencyDetailController {
    @FXML
    private Label currencyNameLabel;

    @FXML
    private Label currencyCodeLabel;

    @FXML
    private Label exchangeRateLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    private CurrencyModel currencyModel;
    private Currency selectedCurrency;
    private Stage primaryStage;
    private Currency currency;

    public void setCurrencyModel(CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
    }

    @FXML
    void initialize() {
        showCurrencyDetails();
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
        showCurrencyDetails();
    }

    private void showCurrencyDetails() {
        if (selectedCurrency != null) {
            String currencyCode = selectedCurrency.getCode();
            String currencyName = selectedCurrency.getName();
            double exchangeRate = currencyModel.getExchangeRate("USD", currencyCode);

            System.out.println("Currency Name: " + currencyName);
            System.out.println("Currency Code: " + currencyCode);
            System.out.println("Exchange Rate (USD): " + exchangeRate);

            currencyNameLabel.setText("Currency Name: " + currencyName);
            currencyCodeLabel.setText("Currency Code: " + currencyCode);
            exchangeRateLabel.setText("Exchange Rate (USD): " + exchangeRate);
        }
    }


    @FXML
    void onBackButtonClicked() {
        primaryStage.setScene(new CurrencyConverterApp().getHomeScene());
    }

    @FXML
    void onHomeButtonClicked() {
        primaryStage.setScene(new CurrencyConverterApp().getHomeScene());
    }
}
