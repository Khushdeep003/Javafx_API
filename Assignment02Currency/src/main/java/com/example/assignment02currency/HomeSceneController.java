package com.example.assignment02currency;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class HomeSceneController {
    @FXML
    private ComboBox<String> currencyComboBox;

    @FXML
    private Button searchButton;

    @FXML
    private Label messageLabel;

    private CurrencyModel currencyModel;
    private Stage primaryStage;

    public void setCurrencyModel(CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
        populateCurrencyComboBox();
    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void populateCurrencyComboBox() {
        List<String> currencyNames = currencyModel.getAllCurrencyNames();
        currencyComboBox.getItems().addAll(currencyNames);
    }

    @FXML
    void onSearchButtonClicked(ActionEvent event) {
        String selectedCurrencyName = currencyComboBox.getValue();
        if (selectedCurrencyName == null) {
            messageLabel.setText("Please select a currency.");
        } else {
            String currencyCode = currencyModel.getCurrencyCodeByName(selectedCurrencyName);
            if (currencyCode == null) {
                messageLabel.setText("Currency not found.");
            } else {
                try {
                    Currency selectedCurrency = new Currency(selectedCurrencyName, currencyCode);
                    CurrencyConverterApp app = new CurrencyConverterApp();
                    app.setPrimaryStage(primaryStage);
                    primaryStage.setScene(app.getCurrencyDetailScene(selectedCurrency));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
