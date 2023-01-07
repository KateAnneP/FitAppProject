package com.example.fitappproject;

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
import java.util.ResourceBundle;

public class DodawaniePotrawViewController implements Initializable {
    @FXML public Button button_powrot;
    @FXML public Button button_dodaj;
    @FXML public TableView tabela_potrawy;
    @FXML public TableColumn kolumna_nazwa;
    @FXML public TableColumn kolumna_kalorie;
    @FXML public TextField field_ilosc;

    public int id_wybranej, ilosc;

    @FXML
    public void powrotAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("kalorycznoscZBazyView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FitApp");
        stage.show();
    }

    @FXML
    public void dodajAction(ActionEvent actionEvent) throws IOException {
        id_wybranej = tabela_potrawy.getSelectionModel().getSelectedIndex();
        try {
            ilosc = Integer.valueOf(field_ilosc.getText());
        }
        catch (Exception e)
        {
            ilosc = 0;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Uwaga!!!");
            alert.setHeaderText(null);
            alert.setContentText("Do tego pola należy wprowadzić tylko liczbę!");
            alert.showAndWait();
        }

        if(ilosc != 0) {
            MainApplication.id_wybranych.add(new Wybrana_potrawa(id_wybranej,ilosc));
            Parent root = FXMLLoader.load(getClass().getResource("kalorycznoscZBazyView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("FitApp");
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabela_potrawy.itemsProperty().setValue(MainApplication.potrawy);
        kolumna_nazwa.setCellValueFactory(new PropertyValueFactory<Potrawa, String>("nazwa"));
        kolumna_kalorie.setCellValueFactory(new PropertyValueFactory<Potrawa, String>("kalorie"));
    }
}
