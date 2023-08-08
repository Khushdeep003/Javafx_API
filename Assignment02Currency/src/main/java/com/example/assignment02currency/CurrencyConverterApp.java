package com.example.assignment02currency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CurrencyConverterApp extends Application {
    private CurrencyModel currencyModel;

    private Stage primaryStage;
    private Scene homeScene;

    @Override
    public void start(Stage primaryStage) {
        currencyModel = new CurrencyModel();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScene.fxml"));
            Scene scene = new Scene(loader.load());
            HomeSceneController homeSceneController = loader.getController();
            homeSceneController.setCurrencyModel(currencyModel);
            homeSceneController.setPrimaryStage(primaryStage);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Currency Converter");
            primaryStage.show();
            primaryStage.getIcons().add(new Image("currencyconvert.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getCurrencyDetailScene(Currency currency) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrencyDetail.fxml"));
        Scene scene = new Scene(loader.load());
        CurrencyDetailController currencyDetailController = loader.getController();
        currencyDetailController.setCurrency(currency);
        currencyDetailController.setCurrencyModel(currencyModel);
        return scene;
    }

    public Scene getHomeScene() {
        return homeScene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}