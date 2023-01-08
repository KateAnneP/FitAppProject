package com.example.fitappproject;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KalorycznoscZBazyViewController implements Initializable {

    @FXML public Pane pane;
    @FXML public Pane pane1;
    @FXML public AnchorPane anchorPane;
    @FXML public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_zaloguj;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;
    @FXML Button button_wyloguj;
    @FXML public TableView tabela_dane;
    @FXML public TableColumn kolumna_nazwa;
    @FXML public TableColumn kolumna_kalorie;
    @FXML public TableColumn kolumna_ilosc;
    @FXML public Button button_oblicz;
    @FXML public Button button_wyczysc;
    @FXML public Label label_wynik;
    @FXML public Button button_dodaj;
    @FXML public Label label_opis;

    Mysql baza = MainApplication.getInstance().getSql();

    ObservableList<Potrawa> potrawyDoTabeli = FXCollections.observableArrayList();

    public static int suma = 0;

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
    public void wylogujAction(ActionEvent actionEvent) throws IOException {
        MainApplication.zalogowane = false;
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        scene.getStylesheets().add("style.css");
        stage.show();
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
        stage.show();
    }

    @FXML
    public void wyczyscAction(ActionEvent actionEvent)
    {
        MainApplication.id_wybranych.clear();
        tabela_dane.refresh();
        suma = 0;
    }

    public void dodajAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawaniePotrawView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        scene.getStylesheets().add("style.css");
        stage.show();
    }

    public void obliczAction(ActionEvent actionEvent) {
        int ilosc, idP;
        double kalorie;
        for (Wybrana_potrawa p: MainApplication.id_wybranych)
        {
            kalorie = p.getKalorie()/100.0;
            ilosc = p.getIlosc();
            suma += kalorie*ilosc;
            System.out.println("Kalorie: " + kalorie);
            System.out.println("Ilość: " + ilosc);
        }
        if (suma == 0 && MainApplication.id_wybranych.isEmpty()) {
            label_wynik.setText("Brak danych w tabeli.");
            Color color = Color.RED;
            label_wynik.setTextFill(color);
        }
        else
            label_wynik.setText("Suma spożytych kalorii wynosi: " + suma);


    }

    @FXML
    public void wybierzZBazyAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.getStyleClass().add("anchorPane");
        pane.getStyleClass().add("pane");
        pane1.getStyleClass().add("pane");
        MainApplication.otworzen++;
        menuOpt_plik.setDisable(false);
        menuOpt_baza.setDisable(true);

        int id, kalorie;
        String nazwa, jednostka, nazwa_jedn;
        if (MainApplication.otworzen == 1) {
            try {
                ResultSet dane = baza.getResult("SELECT potrawy.id,potrawy.nazwa,potrawy.jednostka,jednostki.skrot,kalorycznosc FROM potrawy JOIN jednostki ON potrawy.jednostka = jednostki.id");
                while (dane.next()) {
                    id = dane.getInt(1);
                    nazwa = dane.getString(2);
                    jednostka = dane.getString(4);
                    nazwa_jedn = nazwa + " [" + jednostka + "]";
                    kalorie = dane.getInt(5);
                    MainApplication.potrawy.add(new Potrawa(id, nazwa_jedn, kalorie));
                }
            } catch (SQLException e) {
                System.out.println("Nie można uzyskać danych z bazy");
            }
        }

        tabela_dane.itemsProperty().setValue(MainApplication.id_wybranych);
        kolumna_nazwa.setCellValueFactory(new PropertyValueFactory<Potrawa, String>("nazwa"));
        kolumna_kalorie.setCellValueFactory(new PropertyValueFactory<Potrawa, String>("kalorie"));
        kolumna_ilosc.setCellValueFactory(new PropertyValueFactory<Wybrana_potrawa, String>("ilosc"));
    }
}
