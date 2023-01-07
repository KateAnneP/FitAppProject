package com.example.fitappproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML public MenuItem menuOpt_baza;
    @FXML public MenuItem menuOpt_plik;
    @FXML public MenuButton menu_kalorycznosc;
    @FXML public Button button_login;
    @FXML public Button button_wyjscie;
    @FXML public Button button_bmi;
    @FXML public TextField field_login;
    @FXML public PasswordField field_passwd;

    public String login, password;
    public int id = 0;
    public boolean emptyPasswd = true, emptyLogin = true;

    Mysql baza = MainApplication.getInstance().getSql();

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
        logowanie();
        if (MainApplication.zalogowane) {
            Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("FitApp");
            stage.show();
        }
    }

    public void logowanie()
    {
        try {
            ResultSet wynik = baza.getResult("SELECT id FROM users WHERE login='" + login + "' AND password='" + password + "';");
            wynik.next();
            id = wynik.getInt(1);
            if (id != 0)
            {
                MainApplication.zalogowane = true;
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd!");
                alert.setHeaderText(null);
                alert.setContentText("Nieprawidłowe dane logowania!");
                alert.showAndWait();
            }
        }
        catch(SQLException e)
        {
            System.out.println("Nie można odnaleźć użytkownika w bazie");
        }
    }

    @FXML
    public void wczytajDaneZPlikuAction(ActionEvent actionEvent) {
    }

    @FXML
    public void wybierzZBazyAction(ActionEvent actionEvent) {
    }

    public void odblokujPrzycisk(KeyEvent keyEvent)
    {
        login = field_login.getText().toString();
        password = field_passwd.getText().toString();
        if(login != "")
            emptyLogin = false;
        if(password != "")
            emptyPasswd = false;
        if(!emptyLogin && !emptyPasswd) {
            button_login.setDisable(false);
        }
        else
            button_login.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Tooltip tooltip1 = new Tooltip("Te opcje są dostępne po zalogowaniu");
        menu_kalorycznosc.setTooltip(tooltip1);

    }
}