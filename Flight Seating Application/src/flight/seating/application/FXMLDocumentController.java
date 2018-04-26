/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight.seating.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.Arrays;
import javafx.application.Platform;
import javax.swing.JOptionPane;

/**
 *
 * @author Nick
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button seatingButton;
    @FXML
    private Button adultButton;
    @FXML
    private Button childButton;
    @FXML
    private Button firstClassButton;
    @FXML
    private Button businessClassButton;
    @FXML
    private Button enconomyClassButton;
    @FXML
    private Button windowButton;
    @FXML
    private Button aisleButton;
    @FXML
    private Button middleButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    public TextArea seatingTextArea;
    @FXML
    private Button exitButton;
    
    private FlightSeatingApplication fsa;
    
    @FXML
    private void handleSeatingButtonAction(ActionEvent event) {

        seatingTextArea.setText(fsa.ShowSeating());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       fsa = new FlightSeatingApplication();
    } 

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        Platform.exit();
    }
}
