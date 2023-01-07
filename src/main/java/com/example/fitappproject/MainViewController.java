package com.example.fitappproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML public AnchorPane anchorPane;
    @FXML public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_zaloguj;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;
    @FXML public Button button_wyloguj;


    @FXML
    public void obliczBMIAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bmiView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }

    @FXML
    public void wyjscieAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void zalogujAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }

    @FXML
    public void wylogujAction(ActionEvent actionEvent) {
        MainApplication.zalogowane = false;
        menuOpt_plik.setDisable(true);
        menuOpt_baza.setDisable(true);
        Tooltip tooltip1 = new Tooltip("Te opcje są dostępne po zalogowaniu");
        menu_kalorycznosc.setTooltip(tooltip1);
        button_zaloguj.setVisible(true);
        button_wyloguj.setVisible(false);
    }

    @FXML
    public void wczytajDaneZPlikuAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("kalorycznoscZPlikuView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }

    @FXML
    public void wybierzZBazyAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("kalorycznoscZBazyView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainApplication.suma = 0;
        MainApplication.id_wybranych.clear();
        if(MainApplication.zalogowane)
        {
            menuOpt_plik.setDisable(false);
            menuOpt_baza.setDisable(false);
            button_zaloguj.setVisible(false);
            button_wyloguj.setVisible(true);
        }
        else {
            button_wyloguj.setVisible(false);
            menuOpt_plik.setDisable(true);
            menuOpt_baza.setDisable(true);
            Tooltip tooltip1 = new Tooltip("Te opcje są dostępne po zalogowaniu");
            menu_kalorycznosc.setTooltip(tooltip1);
        }

    }

}