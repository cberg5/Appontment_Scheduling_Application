package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DBCustomer Class.
 * Used to retrieve customer data from the database as well as add, update and delete data from the database.
 */
public class DBCustomer {

    /**
     * A method to retrieve a list of all customers in the database.
     * Runs a SQL query to get all customer data from the database then runs a while loop to populate a list of all customers.
     * @return a list of all customers
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {

        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        Customer customer;

        String sql = "SELECT * FROM customers " +
                "join first_level_divisions on customers.Division_ID = first_level_divisions.Division_ID" +
                " join countries on first_level_divisions.Country_ID = countries.Country_ID;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            customer = new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"), rs.getString("Address"),
                    rs.getString("Postal_Code"), rs.getString("Phone"), rs.getInt("Division_ID"),
                    rs.getString("Division"), rs.getInt("Country_ID"), rs.getString("Country"));
            allCustomers.add(customer);
        }

        return allCustomers;
    }

    /**
     * A method to add a new customer to the database.
     * Runs a SQL insert statement to add new customer information into the database.
     * @param customer
     * @return rows affected.
     * @throws SQLException
     */
    public static int addCustomer(Customer customer) throws SQLException {

        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID)" +
                " VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customer.getId());
        ps.setString(2, customer.getName());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getPostalCode());
        ps.setString(5, customer.getPhone());
        ps.setInt(6, customer.getFlDivisionId());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**
     * A method to update an existing customer in the database.
     * Runs an update sql statement to update an existing customer based on the customer ID.
     * @param customer
     * @return rows affected
     * @throws SQLException
     */
    public static int updateCustomer(Customer customer) throws SQLException {

        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? " +
                "WHERE Customer_ID = " + customer.getId();

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(5, customer.getFlDivisionId());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**
     * A method to delete a customer from the database.
     * Runs a delete SQL statement to delete a customer based on the customer ID.
     * @param customer
     * @return
     * @throws SQLException
     */
    public static int deleteCustomer(Customer customer) throws SQLException {

        String sql = "DELETE FROM customers WHERE Customer_ID = " + customer.getId();
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
}
