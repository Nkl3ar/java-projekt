package com.ftutic.itoprema;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateMouseController implements returnToOriginalView, changeView {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tbCijena;

    @FXML
    private TextField tbDpi;

    @FXML
    private TextField tbIme;

    @FXML
    private TextField tbSensorName;

    @FXML
    private TextField tbSerialExt;

    Uredaji ui;

    public void initialize()
    {
        ui = Uredaji.getInstance();
        Mis t = (Mis) ui.selectedUredaj;
        tbIme.setText(t.getIme());
        tbCijena.setText(String.valueOf(t.getCijena()));
        tbDpi.setText(String.valueOf(t.getDPI()));
        tbSensorName.setText(t.getImeSenzora());
        tbSerialExt.setText(String.valueOf(t.getSerijskiBrUniqueExt()));
    }

    @FXML
    public void returnToOriginalView() throws IOException {
        new ITController().changeView((Stage) tbIme.getScene().getWindow());
    }


    @FXML
    public void add() throws IOException {

        boolean allGood = true;
        String alertMsg = "";
        if(tbIme.getLength()==0){
            allGood=false;
            alertMsg="Molimo unesite ime";
        } else if (tbCijena.getLength() == 0) {
            allGood=false;
            alertMsg="Molimo unesite cijenu";
        } else if (tbDpi.getLength() == 0) {
            allGood=false;
            alertMsg="Molimo unesite DPI";
        }
        else if(tbSensorName.getLength()==0){
            allGood=false;
            alertMsg="Molimo unesite ime senzora";
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

        int dpi;
        double cijena;

        try {
            cijena = Double.parseDouble(tbCijena.getText());
        } catch (Exception e) {
            allGood=false;
            alertMsg="Cijena mora biti broj";
        }
        try {
            dpi = Integer.parseInt(tbDpi.getText());
        } catch (Exception e) {
            allGood=false;
            alertMsg="DPI mora biti cijeli broj";
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
            dpi = Integer.parseInt(tbDpi.getText());
            Mis m = (Mis)ui.selectedUredaj;



            if (tbSerialExt.getLength() == 0) {
                serialExt = m.serijskiBrUniqueExt;
                m.setAll(tbIme.getText(), cijena, serialExt, dpi,tbSensorName.getText());
            } else {
                serialExt = Long.parseLong(tbSerialExt.getText());
                m.setAll(tbIme.getText(), cijena, serialExt, dpi,tbSensorName.getText());
            }
            ui.update(m);
            returnToOriginalView();

        }






    }

    public UpdateMouseController(){}

    @Override
    public void changeView(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ITApplication.class.getResource("update-mouse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 334, 400);
        stage.setTitle("Ažuriranje miša");
        stage.setScene(scene);
        stage.setMinHeight(360);
        stage.setMinWidth(430);
        stage.show();
    }
}
