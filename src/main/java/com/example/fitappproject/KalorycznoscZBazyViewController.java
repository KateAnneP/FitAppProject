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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KalorycznoscZBazyViewController implements Initializable {
    @FXML
    public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_zaloguj;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;
    @FXML Button button_wyloguj;
    @FXML public TableView tabela_dane;
    @FXML public TableColumn kolumna_nazwa;
    @FXML public TableColumn kolumna_kalorie;
    @FXML public Button button_oblicz;
    @FXML public Label label_wynik;
    @FXML public Button button_dodaj;
    @FXML public Label label_opis;

    Mysql baza = MainApplication.getInstance().getSql();

    ObservableList<Potrawa> potrawyDoTabeli = FXCollections.observableArrayList();

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
    public void wylogujAction(ActionEvent actionEvent) throws IOException {
        MainApplication.zalogowane = false;
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }

    @FXML
    public void wczytajDaneZPlikuAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("kalorycznoscZPlikuView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }

    public void dodajAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawaniePotrawView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }

    public void obliczAction(ActionEvent actionEvent) {
        int idP;
        for (Potrawa p: potrawyDoTabeli)
        //zapisz do tabeli id_wybranych tez nazwy, kalorie i id tych potraw, to będzie je łatwiej uzyskac
        {
            MainApplication.suma += (MainApplication.potrawy.get(p.getId()).getKalorie()*MainApplication.id_wybranych.get(p.getId()).getIlosc());
            System.out.println("Kalorie: " + MainApplication.potrawy.get(p.getId()).getKalorie());
            System.out.println("Ilość: " + MainApplication.id_wybranych.get(p.getId()).getIlosc());
        }
        label_wynik.setText("Suma spożytych kalorii wynosi: " + MainApplication.suma);
    }

    @FXML
    public void wybierzZBazyAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuOpt_plik.setDisable(false);
        menuOpt_baza.setDisable(true);

        int id, kalorie;
        String nazwa, jednostka, nazwa_jedn;
        try {
            ResultSet dane = baza.getResult("SELECT potrawy.id,potrawy.nazwa,potrawy.jednostka,jednostki.skrot,kalorycznosc FROM potrawy JOIN jednostki ON potrawy.jednostka = jednostki.id");
            while(dane.next())
            {
                id = dane.getInt(1);
                nazwa = dane.getString(2);
                jednostka = dane.getString(4);
                nazwa_jedn = nazwa + " [" + jednostka + "]";
                kalorie = dane.getInt(5);
                MainApplication.potrawy.add(new Potrawa(id,nazwa_jedn,kalorie));
            }
        }
        catch(SQLException e)
        {
            System.out.println("Nie można uzyskać danych z bazy");
        }

        int idP;

        for ( Wybrana_potrawa p : MainApplication.id_wybranych)
        {
            idP = p.getId();
            potrawyDoTabeli.add(new Potrawa(MainApplication.potrawy.get(idP).getId(),MainApplication.potrawy.get(idP).getNazwa(),MainApplication.potrawy.get(idP).getKalorie()));
        }
        tabela_dane.itemsProperty().setValue(potrawyDoTabeli);
        kolumna_nazwa.setCellValueFactory(new PropertyValueFactory<Potrawa, String>("nazwa"));
        kolumna_kalorie.setCellValueFactory(new PropertyValueFactory<Potrawa, String>("kalorie"));
    }

}
