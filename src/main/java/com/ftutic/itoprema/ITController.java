package com.ftutic.itoprema;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ITController implements changeView {

    public ITController(){}



    @FXML
    public void update() throws IOException{
        Stage stage = (Stage) btnAddMis.getScene().getWindow();
        ui.selectedUredaj = table.getSelectionModel().getSelectedItem();

        if(ui.selectedUredaj instanceof Tipkovnica){
            new UpdateKeyboardController().changeView(stage);
        }
        else if(ui.selectedUredaj instanceof Mis){
            new UpdateMouseController().changeView(stage);
        }
        else if(ui.selectedUredaj instanceof Monitor){
            new UpdateMonitorController().changeView(stage);
        }

    }

    @FXML
    public void addMouse() throws IOException {
        Stage stage = (Stage) btnAddMis.getScene().getWindow();
        new InsertMouseController().changeView(stage);
    }

    @FXML
    public void addMonitor() throws IOException {
        Stage stage = (Stage) btnAddMis.getScene().getWindow();
        new InsertMonitorController().changeView(stage);
    }

    @FXML
    public void addKeyboard() throws IOException {
        Stage stage = (Stage) btnAddMis.getScene().getWindow();
        new InsertKeyboardController().changeView(stage);
    }


    Uredaji ui;

    @FXML
    public void getInfo()
    {
        //Ako budem imao vremena, biti će view
        //zasad je samo alert
        Uredaj odabrano = table.getSelectionModel().getSelectedItem();
        if(odabrano != null) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setTitle("Informacije");
            a.setHeaderText(odabrano.getIme());
            a.setContentText(odabrano.toString());

            a.setAlertType(Alert.AlertType.INFORMATION);

            a.show();
        }


    }

    public void initialize()
    {
        ui  = Uredaji.getInstance();
        table.setItems(ui.uredajList);


        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        colSerijski.setCellValueFactory(new PropertyValueFactory<>("serijskiBr"));






        colCijena.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(Double balance, boolean empty) {
                super.updateItem(balance, empty);
                if (balance == null || empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f€", balance));
                }
            }
        });

        unsort();



    }
    private boolean vece = true;
    @FXML
    private void switchVece()
    {
        if(vece)
        {
            vece = false;
            btnSort2HILo.setText("Manje");
        }
        else {
            vece = true;
            btnSort2HILo.setText("Veće");
        }
    }

    @FXML
    private void sort(){
        boolean allGood = true;
        String alertMsg = "";

        if (tbCijena.getLength() == 0) {
            allGood = false;
            alertMsg = "Molimo unesite cijenu";
        }
        double cijena;

        try {
            cijena = Double.parseDouble(tbCijena.getText());
        } catch (Exception e) {
            allGood=false;
            alertMsg="Cijena mora biti broj";
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
            cijena = Double.parseDouble(tbCijena.getText());
            if(vece){
            ui.getByCijenaBiggerThan(cijena);}
            else
            {
                ui.getByCijenaSmallerThan(cijena);
            }
            btnDelete.setDisable(true);
            table.setItems(ui.uredajList);

        }

    }


    @FXML
    private void unsort(){

        ui.updateObservableList();
        table.setItems(ui.uredajList);
        btnDelete.setDisable(false);

    }




    @FXML
    private void deleteSelected()
    {
        if(table.getSelectionModel().getSelectedItem()!=null){
        ui.remove(table.getSelectionModel().getSelectedItem());
        table.setItems(ui.uredajList);
        }
    }

    @FXML
    private Button btnAddMis;


    @FXML
    private Button btnSort2Execute;

    @FXML
    private Button btnSort2HILo;

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnIzmjeni;


    @FXML
    private TableColumn<Uredaj, Double> colCijena;

    @FXML
    private TableColumn<Uredaj, Integer> colID;

    @FXML
    private TableColumn<Uredaj, String> colIme;

    @FXML
    private TableColumn<Uredaj, String> colSerijski;

    @FXML
    private TableView<Uredaj> table;

    @FXML
    private TextField tbCijena;


    @Override
    public void changeView(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ITApplication.class.getResource("it-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 679, 496);
        stage.setTitle("IT Aplikacija");
        stage.setScene(scene);
        stage.setMinHeight(535);
        stage.setMinWidth(696);
        stage.show();
    }
}