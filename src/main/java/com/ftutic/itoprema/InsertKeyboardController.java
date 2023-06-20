package com.ftutic.itoprema;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InsertKeyboardController implements returnToOriginalView, changeView {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tbCijena;

    @FXML
    private TextField tbKeycount;

    @FXML
    private TextField tbIme;

    @FXML
    private TextField tbSensorName;

    @FXML
    private TextField tbSerialExt;

    @FXML
    public void returnToOriginalView() throws IOException {
        new ITController().changeView((Stage) tbIme.getScene().getWindow());
    }


    @FXML
    public void add() throws IOException {

        Uredaji ui = Uredaji.getInstance();
        Tipkovnica m;
        boolean allGood = true;
        String alertMsg = "";
        if(tbIme.getLength()==0){
            allGood=false;
            alertMsg="Molimo unesite ime";
        } else if (tbCijena.getLength() == 0) {
            allGood=false;
            alertMsg="Molimo unesite cijenu";
        } else if (tbKeycount.getLength() == 0) {
            allGood=false;
            alertMsg="Molimo unesite broj tipki";
        }
        long serialExt;

        if(tbSerialExt.getLength()!=0) {

            try {
                serialExt = Long.parseLong(tbSerialExt.getText());
            } catch (Exception e) {
                allGood=false;
                alertMsg="Serijski broj extension mora biti cijeli broj";
            }

        }

        int brojTipki;
        double cijena;

        try {
            cijena = Double.parseDouble(tbCijena.getText());
        } catch (Exception e) {
            allGood=false;
            alertMsg="Cijena mora biti broj";
        }
        try {
            brojTipki = Integer.parseInt(tbKeycount.getText());
        } catch (Exception e) {
            allGood=false;
            alertMsg="Broj tipki mora biti cijeli broj";
        }

        if(!allGood)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Pogreška");
            a.setHeaderText("Pogreška");
            a.setContentText(alertMsg);

            a.setAlertType(Alert.AlertType.ERROR);

            a.show();
        }
        else {
            //moram tu ponoviti jer inače compiler se žali
            cijena = Double.parseDouble(tbCijena.getText());
            brojTipki = Integer.parseInt(tbKeycount.getText());


            if (tbSerialExt.getLength() == 0) {
                m = new Tipkovnica(tbIme.getText(), cijena, brojTipki);

            } else {
                serialExt = Long.parseLong(tbSerialExt.getText());
                m = new Tipkovnica(tbIme.getText(), cijena, serialExt, brojTipki);
            }
            ui.add(m);
            returnToOriginalView();

        }





    }

    public InsertKeyboardController(){}

    @Override
    public void changeView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ITApplication.class.getResource("insert-keyboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 334, 400);
        stage.setTitle("Dodavanje tipkovnice");
        stage.setScene(scene);
        stage.setMinHeight(360);
        stage.setMinWidth(430);
        stage.show();

    }
}
