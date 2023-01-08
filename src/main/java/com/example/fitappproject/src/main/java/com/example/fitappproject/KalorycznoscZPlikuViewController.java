package com.example.fitappproject;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.filechooser.*;

public class KalorycznoscZPlikuViewController implements Initializable {

    @FXML public Pane pane;
    @FXML public Pane pane1;
    @FXML public AnchorPane anchorPane;
    @FXML public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_wyloguj;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;
    @FXML public Button button_wczytaj;
    @FXML public TableView tabela_dane;
    @FXML public TableColumn kolumna_nazwa;
    @FXML public TableColumn kolumna_ilosc;
    @FXML public TableColumn kolumna_kalorie;
    @FXML public Label label_wynik;

    Mysql baza = MainApplication.getInstance().getSql();

    ObservableList<Wybrana_potrawa> wybrane_potrawy = FXCollections.observableArrayList();

    public String linia, nazwa;
    public int ilosc, kalorie, id = 0, suma;

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
    public void wczytajDaneZPlikuAction(ActionEvent actionEvent) {
    }

    @FXML
    public void wybierzZBazyAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("kalorycznoscZBazyView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("FitApp");
        scene.getStylesheets().add("style.css");
        stage.show();
    }

    @FXML
    public void wczytajAction(ActionEvent actionEvent) {
        File file;

        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
        {
            file = new File(j.getSelectedFile().getAbsolutePath());
            try {
                Scanner s = new Scanner(new FileReader(file));
                while (s.hasNext())
                {
                    nazwa = s.nextLine();
                    ilosc = Integer.valueOf(s.nextLine());
                    for (Potrawa p : MainApplication.potrawy)
                    {
                        if(p.getNazwa().equals(nazwa))
                        {
                            kalorie = p.getKalorie();
                        }
                    }
                    wybrane_potrawy.add(new Wybrana_potrawa(id,ilosc,kalorie,nazwa));
                    System.out.println("Odczytano: " + nazwa + ", " + ilosc + ", kalorie: " + kalorie);
                    id++;
                }
            }
            catch (FileNotFoundException e)
            {

            } catch (IOException e) {
                throw new RuntimeException(e);

            }
            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd!");
                alert.setHeaderText(null);
                alert.setContentText("Nieprawidłowy format pliku!");
                alert.showAndWait();
            }
        }
        else {
        }

        tabela_dane.itemsProperty().setValue(wybrane_potrawy);
        kolumna_nazwa.setCellValueFactory(new PropertyValueFactory<Wybrana_potrawa, String>("nazwa"));
        kolumna_ilosc.setCellValueFactory(new PropertyValueFactory<Wybrana_potrawa, String>("ilosc"));
        kolumna_kalorie.setCellValueFactory(new PropertyValueFactory<Wybrana_potrawa, String>("kalorie"));

        if (!wybrane_potrawy.isEmpty()) {
            double kalorieD;
            int iloscD;
            for (Wybrana_potrawa p : wybrane_potrawy) {
                kalorieD = p.getKalorie() / 100.0;
                iloscD = p.getIlosc();
                suma += kalorieD * iloscD;
                System.out.println("Kalorie: " + kalorieD);
                System.out.println("Ilość: " + iloscD);
            }
            label_wynik.setText("Suma spożytych kalorii wynosi: " + suma);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.getStyleClass().add("anchorPane");
        pane.getStyleClass().add("pane");
        pane1.getStyleClass().add("pane");
        KalorycznoscZBazyViewController.suma = 0;

        MainApplication.otworzen++;
        menuOpt_plik.setDisable(true);
        menuOpt_baza.setDisable(false);

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


    }
}
