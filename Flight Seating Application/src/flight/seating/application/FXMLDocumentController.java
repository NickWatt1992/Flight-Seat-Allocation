/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight.seating.application;

import classes.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Nick
 */
public class FXMLDocumentController implements Initializable {
    
    //Variables
    private Customer currentCustomer;

    private FlightSeatingApplication fsa;
    
    private Customer foundCustomer;
    
    //Buttons and TextFields from SceneBuilder
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

    //Initialize
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       fsa = new FlightSeatingApplication();
       fsa.setupSeats();
       
       newCustomer();
       showSeats();
    } 
    
    
    //Exit the application
    @FXML
    private void handleExitButtonAction(ActionEvent event) 
    {
        Platform.exit();
    }
    
    //On click, add all entered details to currentCustomer
    @FXML
    private void handleConfirmButtonAction(ActionEvent event) 
    {
        currentCustomer.setName(firstName.getText() + " " + lastName.getText());
        fsa.customerList.add(currentCustomer);
        fsa.SeatAllocation(currentCustomer);

        newCustomer();
        JOptionPane.showMessageDialog(null, "Customer added." );
        reset();
        showSeats();
        fsa.SavetoFile();
        
    }
    
    //On click, reset the textfields and buttons
    @FXML
    private void handleResetButtonAction(ActionEvent event) 
    {
        reset();
    }
    
    //On Search button click, search for customer 
    //If customer is not found, show error message 
    //If no customer is found, show message containing customer details
    @FXML
    private void handleSearchButtonAction(ActionEvent event) 
    {
        if(searchCustomer.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Nothing entered." );
        }
        else
        {
            Customer cust = fsa.findCustomer(searchCustomer.getText());
            if(cust != null)
            {
                JOptionPane.showMessageDialog(null, "Customer found." 
                        + "\n" + "Customer name: " + cust.getName() 
                        + "\n" + "Row# " + cust.getSeatRow() 
                        + "\n" + "Seat# " + cust.getSeatNumber());
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
    
    //On click, remove customer from array and reset the search textfield
    @FXML
    private void handleRemoveButtonAction(ActionEvent event) 
    {
        removeCustomer();
        JOptionPane.showMessageDialog(null, "Customer removed." );
        reset();
        fsa.SavetoFile();
        removeButton.setDisable(true);
    }
    
    //Show Seats array
    private void showSeats()
    {
        seatingTextArea.setText(fsa.ShowSeating());
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleAdultButtonAction(ActionEvent event) 
    {
        currentCustomer.setAgeType("Adult");
        adultButton.setDisable(true);
        childButton.setDisable(false);
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleChildButtonAction(ActionEvent event) 
    {
        currentCustomer.setAgeType("Child");
        adultButton.setDisable(false);
        childButton.setDisable(true);
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleFirstClassButtonAction(ActionEvent event) 
    {
        currentCustomer.setClassType("First");
        firstClassButton.setDisable(true);
        businessClassButton.setDisable(false);
        economyClassButton.setDisable(false);
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleBusinessClassButtonAction(ActionEvent event) 
    {
        currentCustomer.setClassType("Business");
        firstClassButton.setDisable(false);
        businessClassButton.setDisable(true);
        economyClassButton.setDisable(false);
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleEconomyClassButtonAction(ActionEvent event) 
    {
        currentCustomer.setClassType("Economy");
        firstClassButton.setDisable(false);
        businessClassButton.setDisable(false);
        economyClassButton.setDisable(true);
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleWindowButtonAction(ActionEvent event) 
    {
        currentCustomer.setSeatType("Window");
        windowButton.setDisable(true);
        aisleButton.setDisable(false);
        middleButton.setDisable(false);
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleAisleButtonAction(ActionEvent event) 
    {
        currentCustomer.setSeatType("Aisle");
        windowButton.setDisable(false);
        aisleButton.setDisable(true);
        middleButton.setDisable(false);
    }
    
    //On click, disable chosen option and enable other options
    @FXML
    private void handleMiddleButtonAction(ActionEvent event) {
        currentCustomer.setSeatType("Middle");
        windowButton.setDisable(false);
        aisleButton.setDisable(false);
        middleButton.setDisable(true);
    }
    
    //Set age type for currentCustomer
    private void setAgeType(String ageType)
    {
        currentCustomer.setAgeType(ageType);
    }
   
    //Create new customer into currentCustomer
    private void newCustomer()
    {
        currentCustomer = new Customer();
    }
    
    //Remove customer from customer array and reset search textfield
    private void removeCustomer()
    {
        fsa.CancelSeatAllocation(foundCustomer);
        fsa.customers.remove(foundCustomer);
        foundCustomer = null;
        showSeats();
        searchCustomer.setText("");
    }
    
    //Reset all customer details
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
