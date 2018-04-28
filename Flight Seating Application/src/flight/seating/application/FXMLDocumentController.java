/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight.seating.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Nick
 */
public class FXMLDocumentController implements Initializable {
    
    private Customer currentCustomer;

    private FlightSeatingApplication fsa;
    
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
    
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    private TextField searchCustomer;
    
    @FXML
    private void handleSeatingButtonAction(ActionEvent event) {

        
        seatingTextArea.setText(fsa.ShowSeating());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       fsa = new FlightSeatingApplication();
       fsa.setupSeats();
       
       newCustomer();
    } 
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        Platform.exit();
    }
    
     @FXML
    private void handleConfirmButtonAction(ActionEvent event) {
        currentCustomer.setName(firstName.getText() + " " + lastName.getText());
        fsa.customerList.add(currentCustomer);
        fsa.SeatAllocation(currentCustomer);
        // Add a confirmation message
        newCustomer();
    }
    
    @FXML
    private void handleAdultButtonAction(ActionEvent event) {
        currentCustomer.setAgeType("Adult");
    }
    @FXML
    private void handleChildButtonAction(ActionEvent event) {
        currentCustomer.setAgeType("Child");
    }
    @FXML
    private void handleFirstClassButtonAction(ActionEvent event) {
        currentCustomer.setClassType("First");
    }
    @FXML
    private void handleBusinessClassButtonAction(ActionEvent event) {
        currentCustomer.setClassType("Business");
    }
    @FXML
    private void handleEconomyClassButtonAction(ActionEvent event) {
        currentCustomer.setClassType("Economy");
    }
    @FXML
    private void handleWindowButtonAction(ActionEvent event) {
        currentCustomer.setSeatType("Window");
    }
    @FXML
    private void handleAisleButtonAction(ActionEvent event) {
        currentCustomer.setSeatType("Aisle");
    }
    @FXML
    private void handleMiddleButtonAction(ActionEvent event) {
        currentCustomer.setSeatType("Middle");
    }
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        
    }

    private void setAgeType(String ageType)
    {
        currentCustomer.setAgeType(ageType);
    }
   
    private void newCustomer()
    {
        currentCustomer = new Customer();
    }
    
}
