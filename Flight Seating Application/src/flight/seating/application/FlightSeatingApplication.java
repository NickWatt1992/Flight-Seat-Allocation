/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight.seating.application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Nick
 */
public class FlightSeatingApplication extends Application {
    
    public FXMLDocumentController fc = new FXMLDocumentController();
    List<Customer> customerList = new ArrayList<Customer>();;
    List<Seat> seatsList = new ArrayList<Seat>();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        setupSeats();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
    
    public void setupSeats()
    {
        String seatType = null;
        String seatClass;
        int countRow = 0;
        int rowNumber = 0;
        int seatNumber = 0;
        for(int i = 0; i < 72; i++)
        {          

            seatNumber = i - (6*(rowNumber-1));
            seatNumber = seatNumber + 1;
            
            if(i % 6 == 0)
            {
                rowNumber = rowNumber + 1;
            }
            
            if(seatNumber == 7)
            {
                seatNumber = 1;
            }
            
            switch (seatNumber)
            {
                case 1:
                    seatType = "Window";
                    break;
                case 3:
                    seatType = "Aisle";
                    break;
                case 4:
                    seatType = "Aisle";
                    break;
                case 6:
                    seatType = "Window";
                    break;
                default:
                    seatType = "Middle";
                    break;
            }
            
            if(i <= 12)
            {
                seatClass="First";
            }
            else if (i > 12 && i <= 30)
            {
                seatClass="Business";
            }
            else
            {
                seatClass="Economy";
            }
            seatsList.add(new Seat(null,rowNumber,seatNumber,seatClass, seatType));
            if(seatNumber == 6)
            {
                seatNumber = 0;
            }
        }
    }
   
    
    public String ShowSeating(){
 
        String txt = "";
        
        for(int x = 0; x < seatsList.size(); x++)
        {
            String checkCustomer = "";
            if(seatsList.get(x).getCust() != null)
            {
                switch (seatsList.get(x).getCust().getAgeType())
                {
                    case "Adult":
                        checkCustomer = "A";
                        break;
                    case "Child":
                        checkCustomer = "B";
                        break;
                }
            }
            else
            {
                checkCustomer = "*";
            }
            if(seatsList.get(x).getSeatNumber() == 6)
            {
                txt = txt + "  " + checkCustomer + "\n";
            }
            else
            {
              txt = txt + "  " + checkCustomer;  
            }
        }
        
        return txt;
    }
    
    public String SeatAllocation(Customer cust)
    {
        String message = "Preferred seat unavailable.";
        for(int x = 0; x < seatsList.size(); x++)
        {
            if((seatsList.get(x).getSeatClass() == cust.getClassType()) && (seatsList.get(x).getSeatType() == cust.getSeatType()))
            {
                if(seatsList.get(x).getCust() == null)
                {
                    seatsList.get(x).setCust(cust);
                    message = "Seat confirmed.";
                    break;
                }
            }
        }
        return message;
    }
    
    public String searchForCustomer(String name)
    {
        String message = "No matching customer found.";
        for(int x = 0; x < seatsList.size(); x++)
        {
            if(seatsList.get(x).getCust().getName() == name)
            {
                message = "Customer Found - Row#: " + seatsList.get(x).getSeatRow() + " - Seat#: " + seatsList.get(x).getSeatNumber();
                break;
            }
        }
        return message;
    }
    
    public void CancelSeatAllocation(Customer cust)
    {
        for(int x = 0; x < seatsList.size(); x++)
        {
            
        }
    }
}   
