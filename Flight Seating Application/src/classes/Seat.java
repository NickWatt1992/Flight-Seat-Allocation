/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.Customer;

/**
 *
 * @author Nick
 */
public class Seat 
{
   //Variables
   private Customer _cust;
   private int _seatRow;
   private int _seatNumber;
   private String _seatType;
   private String _seatClass;
   
   //Default constructor
   public Seat()
   {
       
   }
   
   //Constuctor with variables
   public Seat(Customer cust, int seatRow, int seatNumber, String seatClass, String seatType)
   {
       _cust = cust;
       _seatRow = seatRow;
       _seatNumber = seatNumber;
       _seatClass = seatClass;
       _seatType = seatType;
   }

    //Getters and Setters for variables
    /**
     * @return the cust
     */
    public Customer getCust() {
        return _cust;
    }

    /**
     * @param cust the cust to set
     */
    public void setCust(Customer cust) {
        this._cust = cust;
    }

    /**
     * @return the seatRow
     */
    public int getSeatRow() {
        return _seatRow;
    }

    /**
     * @param seatRow the seatRow to set
     */
    public void setSeatRow(int seatRow) {
        this._seatRow = seatRow;
    }

    /**
     * @return the seatNumber
     */
    public int getSeatNumber() {
        return _seatNumber;
    }

    /**
     * @param seatNumber the seatNumber to set
     */
    public void setSeatNumber(int seatNumber) {
        this._seatNumber = seatNumber;
    }

    /**
     * @return the seatType
     */
    public String getSeatType() {
        return _seatType;
    }

    /**
     * @param seatType the seatType to set
     */
    public void setSeatType(String seatType) {
        this._seatType = seatType;
    }

    /**
     * @return the _seatClass
     */
    public String getSeatClass() {
        return _seatClass;
    }

    /**
     * @param _seatClass the _seatClass to set
     */
    public void setSeatClass(String _seatClass) {
        this._seatClass = _seatClass;
    }
   
   
}
