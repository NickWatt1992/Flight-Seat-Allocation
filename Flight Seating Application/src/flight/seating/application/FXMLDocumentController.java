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
    
    private Customer foundCustomer;
    
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
    private Button economyClassButton;
    @FXML
    private Button windowButton;
    @FXML
    private Button aisleButton;
    @FXML
    private Button middleButton;
    @FXML
    private Button confirmButton;
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
    private Button searchButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button removeButton;

    
    @FXML
    private void handleSeatingButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       fsa = new FlightSeatingApplication();
       fsa.setupSeats();
       
       newCustomer();
       showSeats();
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
        reset();
        showSeats();
        fsa.SavetoFile();
        
    }
    
    @FXML
    private void handleResetButtonAction(ActionEvent event) {
        reset();
    }
    
    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        if(searchCustomer.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Nothing entered." );
        }
        else
        {
            Customer cust = fsa.findCustomer(searchCustomer.getText());
            if(cust != null)
            {
                JOptionPane.showMessageDialog(null, "Customer found." + "Customer name: " + cust.getName() + "Row#" + cust.getSeatRow() + "\n" + cust.getSeatNumber());
                foundCustomer = cust;
                removeButton.setDisable(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Customer not found." );
                foundCustomer = null;
                removeButton.setDisable(true);
            }
            
        }
    }
    
    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        removeCustomer();
        reset();
    }
    
    private void showSeats()
    {
        seatingTextArea.setText(fsa.ShowSeating());
    }
    
    @FXML
    private void handleAdultButtonAction(ActionEvent event) {
        currentCustomer.setAgeType("Adult");
        adultButton.setDisable(true);
        childButton.setDisable(false);
    }
    @FXML
    private void handleChildButtonAction(ActionEvent event) {
        currentCustomer.setAgeType("Child");
        adultButton.setDisable(false);
        childButton.setDisable(true);
    }
    @FXML
    private void handleFirstClassButtonAction(ActionEvent event) {
        currentCustomer.setClassType("First");
        firstClassButton.setDisable(true);
        businessClassButton.setDisable(false);
        economyClassButton.setDisable(false);
    }
    @FXML
    private void handleBusinessClassButtonAction(ActionEvent event) {
        currentCustomer.setClassType("Business");
        firstClassButton.setDisable(false);
        businessClassButton.setDisable(true);
        economyClassButton.setDisable(false);
    }
    @FXML
    private void handleEconomyClassButtonAction(ActionEvent event) {
        currentCustomer.setClassType("Economy");
        firstClassButton.setDisable(false);
        businessClassButton.setDisable(false);
        economyClassButton.setDisable(true);
    }
    @FXML
    private void handleWindowButtonAction(ActionEvent event) {
        currentCustomer.setSeatType("Window");
        windowButton.setDisable(true);
        aisleButton.setDisable(false);
        middleButton.setDisable(false);
    }
    @FXML
    private void handleAisleButtonAction(ActionEvent event) {
        currentCustomer.setSeatType("Aisle");
        windowButton.setDisable(false);
        aisleButton.setDisable(true);
        middleButton.setDisable(false);
    }
    @FXML
    private void handleMiddleButtonAction(ActionEvent event) {
        currentCustomer.setSeatType("Middle");
        windowButton.setDisable(false);
        aisleButton.setDisable(false);
        middleButton.setDisable(true);
    }
    
    
    

    private void setAgeType(String ageType)
    {
        currentCustomer.setAgeType(ageType);
    }
   
    private void newCustomer()
    {
        currentCustomer = new Customer();
    }
    
    private void removeCustomer()
    {
        fsa.CancelSeatAllocation(foundCustomer);
        fsa.customers.remove(foundCustomer);
        foundCustomer = null;
        showSeats();
        searchCustomer.setText("");
    }
    
    private void reset()
    {
        firstName.setText("");
        lastName.setText("");  
        adultButton.setDisable(false);
        childButton.setDisable(false);
        firstClassButton.setDisable(false);
        businessClassButton.setDisable(false);
        economyClassButton.setDisable(false);
        windowButton.setDisable(false);
        aisleButton.setDisable(false);
        middleButton.setDisable(false);
    }
    
}
