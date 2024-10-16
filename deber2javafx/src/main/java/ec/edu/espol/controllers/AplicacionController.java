/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Ejercicio;
import ec.edu.espol.model.GymException;
import ec.edu.espol.model.Rutina;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author sebastian
 */
public class AplicacionController implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBox;
    private ArrayList<String> nomRutina;
    private ArrayList<String> nomEjercicios;
    private ArrayList<Integer> repeticiones;
    private ArrayList<Ejercicio> ejercicios;
    
    @FXML
    private HBox hRutinas;
    @FXML
    private VBox vBotones;
    @FXML
    private ScrollPane myScrollpane;
    @FXML
    private Label lblRutina;
    @FXML
    private Label lblEjercicio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myScrollpane.setVisible(false);
        lblEjercicio.setVisible(false);
        try {
            nomRutina = Rutina.nombresRutina("rutinas.txt");
            myChoiceBox.getItems().addAll(nomRutina);         
        } catch (GymException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"EL DOCUMENTO NO EXISTE");
            a.show();
        }
        myChoiceBox.setOnAction( 
                (e) -> {
                    myScrollpane.setVisible(false);
                    lblRutina.setVisible(false);
                    lblEjercicio.setVisible(true);
            try {
                nomEjercicios = Rutina.nombresEjercicio("rutinas.txt", myChoiceBox.getValue());
                repeticiones = Rutina.cargarRepeticiones("rutinas.txt", myChoiceBox.getValue());
                vBotones.getChildren().clear();
                for(int i = 0; i < nomEjercicios.size();i++)
                {
                    String str1 = nomEjercicios.get(i);
                    String str2 = repeticiones.get(i).toString();
                    Button boton1 = new Button(str2 + " - " + str1);
                    vBotones.getChildren().add(boton1);
                    boton1.setOnAction((ActionEvent event) -> {                       
                        hRutinas.getChildren().clear();
                        myScrollpane.setVisible(true);
                        String linea = boton1.getText();
                        String str = linea.substring(5, linea.length());
                        ArrayList<String> imagenes;
                        try {
                            ejercicios = Rutina.cargarListaEjercicio("rutinas.txt", myChoiceBox.getValue());
                            ArrayList<String> nombresEjercicios = Rutina.nombresEjercicio("rutinas.txt", myChoiceBox.getValue());
                            int pos = nombresEjercicios.indexOf(str);
                            imagenes = ejercicios.get(pos).getImagenes();
                            ImageView imgview;
                            for (int j =0; j < imagenes.size() ;j++)
                            {   
                                Image img = new Image("img/"+imagenes.get(j));
                                imgview = new ImageView(img);
                                hRutinas.getChildren().add(imgview);
                            }
                        } catch (GymException ex) {
                            Alert a = new Alert(Alert.AlertType.ERROR,"EL DOCUMENTO NO EXISTE");
                            a.show();
                        }
                    });
                }
            } catch (GymException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"EL DOCUMENTO NO EXISTE");
                a.show();
            }
                });
    }   
}
