package com.example.fitappproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class KalorycznoscZPlikuViewController implements Initializable {
    @FXML
    public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_zaloguj;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainApplication.suma = 0;
        MainApplication.id_wybranych.clear();
        menuOpt_plik.setDisable(false);
        menuOpt_baza.setDisable(false);
    }
}
