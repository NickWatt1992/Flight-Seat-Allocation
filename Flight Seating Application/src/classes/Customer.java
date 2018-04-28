/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Nick
 */

//
public class Customer implements Comparable<Customer>
{
    //Variables
    private String _name;
    private String _ageType;
    private String _classType;
    private int _seatRow;
    private int _seatNumber;
    private String _seatType;
  
  //Compare customers  
  public int compareTo(Customer cust) 
  {
    if(this._name != null && cust._name != null)
    {
        return this._name.compareToIgnoreCase(cust._name);
    }
    return 0;
  }
    
    //Default constructor
    public Customer()
    {
        
    }
    
    //Constuctor with variables
    public Customer(String name, String ageType, String preferredClassType, int preferredSeatRow, int preferredSeatNumber, String preferredSeatType)
    {
           _name = name;
           _ageType = ageType;
           _classType = preferredClassType;
           _seatRow = preferredSeatRow;
           _seatNumber = preferredSeatNumber;
           _seatType = preferredSeatType;
    }

    //Getters and Setters for each variables
    
    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _ageType
     */
    public String getAgeType() {
        return _ageType;
    }

    /**
     * @return the _classType
     */
    public String getClassType() {
        return _classType;
    }

    /**
     * @return the _seatRow
     */
    public int getSeatRow() {
        return _seatRow;
    }

    /**
     * @return the _seatNumber
     */
    public int getSeatNumber() {
        return _seatNumber;
    }

    /**
     * @param _ageType the _ageType to set
     */
    public void setAgeType(String _ageType) {
        this._ageType = _ageType;
    }

    /**
     * @param _classType the _classType to set
     */
    public void setClassType(String _classType) {
        this._classType = _classType;
    }

    /**
     * @param _seatRow the _seatRow to set
     */
    public void setSeatRow(int _seatRow) {
        this._seatRow = _seatRow;
    }

    /**
     * @param _seatNumber the _seatNumber to set
     */
    public void setSeatNumber(int _seatNumber) {
        this._seatNumber = _seatNumber;
    }

    /**
     * @return the _seatType
     */
    public String getSeatType() {
        return _seatType;
    }

    /**
     * @param _seatType the _seatType to set
     */
    public void setSeatType(String _seatType) {
        this._seatType = _seatType;
    }
    
    
    
}
