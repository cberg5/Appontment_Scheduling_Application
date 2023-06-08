package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBCustomer;
import sample.DAO.DBUser;

import java.sql.SQLException;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFlDivisionId() {
        return flDivisionId;
    }

    public void setFlDivisionId(int flDivisionId) {
        this.flDivisionId = flDivisionId;
    }

    public String getFlDivision() {
        return flDivision;
    }

    public void setFlDivision(String flDivision) {
        this.flDivision = flDivision;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static Customer getCustomer(Appointment appointment) throws SQLException {
        int customerId = appointment.getCustomerId();
        Customer selectedCustomer = null;
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        allCustomers = DBCustomer.getAllCustomers();

        for(Customer customer : allCustomers){
            if(customer.getId() == customerId){
                selectedCustomer = customer;
            }
        }
        return selectedCustomer;
    }

    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
