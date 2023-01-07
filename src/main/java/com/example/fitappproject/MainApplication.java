package com.example.fitappproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApplication extends Application {
    private Mysql sql;
    private static MainApplication instance;

    public static boolean zalogowane = false;
    public static ObservableList<Potrawa> potrawy = FXCollections.observableArrayList();
    public static ObservableList<Wybrana_potrawa> id_wybranych = FXCollections.observableArrayList();
    public static int suma = 0;

    private void setupSql() {

        String host = "localhost";
        String username = "root";
        String password = "";
        String port =  "3306";
        String database = "ick_app";

        boolean ssl = false;
        this.sql = new Mysql(host, username, password, database, port, ssl);
    }

    @Override
    public void start(Stage stage) throws IOException {
        setupSql();
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("FitApp");
        stage.setScene(scene);
        stage.show();
    }

    public Mysql getSql() {
        return sql;
    }

    public static MainApplication getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }
}