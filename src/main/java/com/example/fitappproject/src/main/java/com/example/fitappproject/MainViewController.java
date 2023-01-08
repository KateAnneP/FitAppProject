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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML public AnchorPane anchorPane;
    @FXML public Pane pane;
    @FXML public Pane pane1;
    @FXML public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_zaloguj;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;
    @FXML public Button button_wyloguj;
    @FXML public Label label_powitanie;
    @FXML public Label label_uwaga;
    @FXML public ImageView img;


    @FXML
    public void obliczBMIAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bmiView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        scene.getStylesheets().add("style.css");
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
        scene.getStylesheets().add("style.css");
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
        scene.getStylesheets().add("style.css");
        anchorPane.getStyleClass().add("anchorPane");
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
        scene.getStylesheets().add("style.css");
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.getStyleClass().add("anchorPane");
        pane.getStyleClass().add("pane");
        pane1.getStyleClass().add("pane");
        KalorycznoscZBazyViewController.suma = 0;
        if(MainApplication.zalogowane)
        {
            menuOpt_plik.setDisable(false);
            menuOpt_baza.setDisable(false);
            button_zaloguj.setVisible(false);
            button_wyloguj.setVisible(true);
            label_powitanie.setText("Witaj " + LoginViewController.login + "! Teraz możesz korzystać z wszystkich opcji aplikacji bez ograniczeń");
            label_uwaga.setVisible(false);

            Image image = new Image("sushi.png");
            img.setImage(image);
        }
        else {
            button_wyloguj.setVisible(false);
            menuOpt_plik.setDisable(true);
            menuOpt_baza.setDisable(true);
            Tooltip tooltip1 = new Tooltip("Te opcje są dostępne po zalogowaniu");
            menu_kalorycznosc.setTooltip(tooltip1);
            label_powitanie.setText("Witaj w programie! \n Rozpocznij swoją przygodę ze zdrowym odżywianiem razem z nami!");
            label_uwaga.setVisible(true);

            Image image = new Image("ciasto.png");
            img.setImage(image);
        }

    }

}