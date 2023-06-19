package com.ftutic.itoprema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ITApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ITApplication.class.getResource("it-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 679, 496);
        stage.setTitle("IT Aplikacija");
        stage.setScene(scene);
        stage.setMinHeight(535);
        stage.setMinWidth(696);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Uredaji ui = Uredaji.getInstance();
        System.out.println(ui.uredajBaseList);
        launch();
    }
}