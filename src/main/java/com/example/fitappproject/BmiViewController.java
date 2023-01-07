package com.example.fitappproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BmiViewController implements Initializable {
    @FXML
    public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_zaloguj;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;
    @FXML public Label label_opis;
    @FXML public TextField field_wzrost;
    @FXML public TextField field_waga;
    @FXML Button button_oblicz;
    @FXML public Label label_twojeBMI;
    @FXML public Label label_warning;
    @FXML public ProgressBar progress;

    private int waga, wzrost;

    @FXML
    public void obliczBMIAction(ActionEvent actionEvent) {
    }

    @FXML
    public void wyjscieAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void zalogujAction(ActionEvent actionEvent) {
    }

    @FXML
    public void wczytajDaneZPlikuAction(ActionEvent actionEvent) {
    }

    @FXML
    public void wybierzZBazyAction(ActionEvent actionEvent) {
    }

    @FXML
    public void sprawdzPoprawnoscWzrostuAction(KeyEvent keyEvent) {
        try
        {
            wzrost = Integer.valueOf(field_wzrost.getText());
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Uwaga!!!");
            alert.setHeaderText(null);
            alert.setContentText("Do tego pola należy wprowadzić tylko liczbę!");
            alert.showAndWait();
            field_wzrost.setText("");
        }
    }

    @FXML
    public void sprawdzPoprawnoscWagiAction(KeyEvent keyEvent) {
        try
        {
            waga = Integer.valueOf(field_waga.getText());
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Uwaga!!!");
            alert.setHeaderText(null);
            alert.setContentText("Do tego pola należy wprowadzić tylko liczbę!");
            alert.showAndWait();
            field_waga.setText("");
        }
    }

    @FXML
    public void obliczAction(ActionEvent actionEvent) throws InterruptedException {

        double w = wzrost/100.0;
        double bmi = waga/Math.pow(w,2);
        label_twojeBMI.setText("Twoje BMI wynosi: " + String.format("%.2f",bmi));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainApplication.suma = 0;
        MainApplication.id_wybranych.clear();
        Tooltip tooltip1 = new Tooltip("Te opcje są dostępne po zalogowaniu");
        menu_kalorycznosc.setTooltip(tooltip1);
    }

}
