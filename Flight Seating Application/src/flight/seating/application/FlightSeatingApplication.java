/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight.seating.application;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Collections;
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
    List<Customer> customerList = new ArrayList<Customer>();
    List<Seat> seatsList = new ArrayList<Seat>();
    ArrayList customers = new ArrayList();
    
    //2D Array
    String[][] seatarray = new String[12][6];
    
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
            if(seatsList.get(x).getCust() == null)
            {
                seatarray[seatsList.get(x).getSeatRow()-1][seatsList.get(x).getSeatNumber()-1] = "*";
            }
        }
        
        for(int row = 0; row < seatarray.length; row++)
        {
            for(int col = 0; col < seatarray[row].length; col++)
            {
                txt = txt + " " + seatarray[row][col];
                if(col == 5)
                {
                    txt = txt + "\n";
                }
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
                    cust.setSeatRow(seatsList.get(x).getSeatRow());
                    cust.setSeatNumber(seatsList.get(x).getSeatNumber());
                    seatsList.get(x).setCust(cust);
                    
                    switch (seatsList.get(x).getCust().getAgeType())
                    {
                    case "Adult":
                        seatarray[seatsList.get(x).getSeatRow()-1][seatsList.get(x).getSeatNumber()-1] = "A";
                        break;
                    case "Child":
                        seatarray[seatsList.get(x).getSeatRow()-1][seatsList.get(x).getSeatNumber()-1] = "B";
                        break;
                    }
                    
                    message = "Seat confirmed.";
                    break;
                }
            }
        }
        Collections.sort(customerList);
        customers.add(cust.getName());
        //TODO
        return message;
    }
    
    public String searchForCustomer(String name)
    {
        
        String message = "No matching customer found.";
     
        int index = Collections.binarySearch(customers, name);
        
        if(index != 0)
        {
            message = "Found";
        }

        return message;
    }
    
    
    public Customer findCustomer(String name)
    {
        Customer cust;
        
        for(int x = 0; x < seatsList.size(); x++)
        {
           if(seatsList.get(x).getCust() == null)
           {
               
           }
           else if(seatsList.get(x).getCust().getName().equals(name))
           {
               return seatsList.get(x).getCust();
           }
        }
        return null;
    }
    
    public void CancelSeatAllocation(Customer cust)
    {
        for(int x = 0; x < seatsList.size(); x++)
        {
            if(seatsList.get(x).getCust() == cust)
            {
                seatsList.get(x).setCust(null);
            }
        }
    }
    
    public void SavetoFile()
    {
        try{
            String fileName = "Seats.txt";
            File fileObject = new File(fileName);
            initialWrite(fileName);

        }
        catch(Exception e){
            
        }
    }
    
    public void initialWrite(String fileName) throws IOException 
    {
    RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
    raf.writeBytes(ShowSeating().toString());
    raf.close();
  }
}   
