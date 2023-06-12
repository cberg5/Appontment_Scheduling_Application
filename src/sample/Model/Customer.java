package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBCustomer;

import java.sql.SQLException;

/**
 * Customer Class. Used to set and retrieve data for customer objects.
 */
public class Customer {

    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int flDivisionId;
    private String flDivision;
    private int countryId;
    private String country;

    /**
     * Constructor for customer object.
     * @param id
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param flDivisionId
     * @param flDivision
     * @param countryId
     * @param country
     */
    public Customer(int id, String name, String address, String postalCode, String phone, int flDivisionId, String flDivision, int countryId, String country){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.flDivisionId = flDivisionId;
        this.flDivision = flDivision;
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Constructor for customer class that does not include the customer ID
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param flDivisionId
     * @param flDivision
     * @param countryId
     * @param country
     */
    public Customer(String name, String address, String postalCode, String phone, int flDivisionId, String flDivision, int countryId, String country){
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.flDivisionId = flDivisionId;
        this.flDivision = flDivision;
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Retrieves ID of a customer
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets ID of a customer
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves name of a customer.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of a customer.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves address of a customer.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address of a customer.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves postal code of a customer
     * @return postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code of a customer
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Retrieves phone number of customer
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Retrieves division ID of customer
     * @return division ID
     */
    public int getFlDivisionId() {
        return flDivisionId;
    }

    /**
     * Retrieves country ID of customer
     * @return country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Retrieves country from customer
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country for customer
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * A method to retrieve a customer object.
     * Gets the customer ID from an appointment object and compares with a list of all customers and returns the matching customer.
     * @param appointment
     * @return customer
     * @throws SQLException
     */
    public static Customer getCustomer(Appointment appointment) throws SQLException {
        int customerId = appointment.getCustomerId();
        Customer selectedCustomer = null;
        ObservableList<Customer> allCustomers;
        allCustomers = DBCustomer.getAllCustomers();

        for(Customer customer : allCustomers){
            if(customer.getId() == customerId){
                selectedCustomer = customer;
            }
        }
        return selectedCustomer;
    }

    /**
     * Overrides the toString function for Customer class. Helps to properly display customer info in comboboxes.
     * @return
     */
    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
