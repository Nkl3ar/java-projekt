package com.ftutic.itoprema;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateMonitorController implements returnToOriginalView, changeView {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tbCijena;

    @FXML
    private TextField tbDijagonala;

    @FXML
    private TextField tbIme;

    @FXML
    private TextField tbSerialExt;
    Uredaji ui;
    public void initialize()
    {
        ui = Uredaji.getInstance();
        Monitor t = (Monitor) ui.selectedUredaj;
        tbIme.setText(t.getIme());
        tbCijena.setText(String.valueOf(t.getCijena()));
        tbDijagonala.setText(String.valueOf(t.getDijagonala()));
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
        } else if (tbDijagonala.getLength() == 0) {
            allGood=false;
            alertMsg="Molimo unesite dijagonalu";
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

        int dijagonala;
        double cijena;

        try {
            cijena = Double.parseDouble(tbCijena.getText());
        } catch (Exception e) {
            allGood=false;
            alertMsg="Cijena mora biti broj";
        }
        try {
            dijagonala = Integer.parseInt(tbDijagonala.getText());
        } catch (Exception e) {
            allGood=false;
            alertMsg="Dijagonala mora biti cijeli broj";
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
            dijagonala = Integer.parseInt(tbDijagonala.getText());
            Monitor m = (Monitor) ui.selectedUredaj;


            if (tbSerialExt.getLength() == 0) {
                serialExt = m.getSerijskiBrUniqueExt();
                m.setAll(tbIme.getText(), cijena, serialExt, dijagonala);
            } else {
                serialExt = Long.parseLong(tbSerialExt.getText());
                m.setAll(tbIme.getText(), cijena, serialExt, dijagonala);
            }
            ui.update(m);
            returnToOriginalView();

        }



    }
    public UpdateMonitorController(){}

    @Override
    public void changeView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ITApplication.class.getResource("update-monitor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 334, 400);
        stage.setTitle("Ažuriranje monitora");
        stage.setScene(scene);
        stage.setMinHeight(360);
        stage.setMinWidth(430);
        stage.show();

    }
}
