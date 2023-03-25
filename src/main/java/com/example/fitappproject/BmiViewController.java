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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BmiViewController implements Initializable {

    @FXML public Pane pane;
    @FXML public Pane pane1;
    @FXML public AnchorPane anchorPane;
    @FXML public MenuItem menuOpt_baza;
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
    @FXML public Button button_wyloguj;

    private int waga, wzrost, zakres;

    @FXML
    public void obliczBMIAction(ActionEvent actionEvent) {
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

        if (field_waga.getText().equals("") || field_wzrost.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Uwaga!!!");
            alert.setHeaderText(null);
            alert.setContentText("Nie wprowadzono danych!");
            alert.showAndWait();
        }
        else {
            double w = wzrost / 100.0;
            double bmi = waga / Math.pow(w, 2);
            label_twojeBMI.setText("Twoje BMI wynosi: " + String.format("%.2f", bmi));
            if (bmi > 30.0)
                zakres = 0;
            else if (bmi > 25.0 && bmi <= 30.0)
                zakres = 1;
            else if (bmi <= 25.0 && bmi > 18.5)
                zakres = 2;
            else if (bmi <= 18.5 && bmi > 17.0)
                zakres = 3;
            else if (bmi <= 17.0 && bmi > 16.0)
                zakres = 4;
            else
                zakres = 5;
        }

        switch(zakres)
        {
            case 0:
            {
                label_opis.setText("Ten wynik wskazuje na otyłość. Zadbaj o siebie, twoje zdrowie zawsze powinno być dla Ciebie priorytetem");
                Color color = Color.RED;
                label_opis.setTextFill(color);
                break;
            }
            case 1:
            {
                label_opis.setText("Ten wynik wskazuje na nadwagę. Zadbaj o siebie, twoje zdrowie zawsze powinno być dla Ciebie priorytetem");
                Color color = Color.RED;
                label_opis.setTextFill(color);
                break;
            }
            case 2:
            {
                label_opis.setText("Brawo! Masz prawidłową masę ciała.");
                Color color = Color.GREEN;
                label_opis.setTextFill(color);
                break;
            }
            case 3:
            {
                label_opis.setText("Masz lekką niedowagę. Uważaj na siebie, potrzebujesz dużo siły do życia.");
                Color color = Color.ORANGE;
                label_opis.setTextFill(color);
                break;
            }
            case 4:
            {
                label_opis.setText("Twoje BMI świadczy o wychudzeniu. To niebezpieczne dla twojego zdrowia!");
                Color color = Color.RED;
                label_opis.setTextFill(color);
                break;
            }
            case 5:
            {
                label_opis.setText("Ten wynik świadczy o wygłodzeniu Twojego organizmu. Uważaj, to niebezpieczne dla Twojego zdrowia! Lepiej skonsultuj się z lekarzem.");
                Color color = Color.RED;
                label_opis.setTextFill(color);
                break;
            }
        }
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
        }
        else {
            button_wyloguj.setVisible(false);
            menuOpt_plik.setDisable(true);
            menuOpt_baza.setDisable(true);
            Tooltip tooltip1 = new Tooltip("Te opcje są dostępne po zalogowaniu");
            menu_kalorycznosc.setTooltip(tooltip1);
        }

        label_warning.setText("Pamiętaj! BMI to tylko liczba. Nie uwzględnia, jak bardzo masz rozwinięte mięśnie, ani ile uprawiasz sportu. W celu fachowej oceny skonsultuj się z dietetykiem lub lekarzem.");
    }

}
