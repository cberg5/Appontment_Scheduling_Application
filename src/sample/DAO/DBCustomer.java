package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Contact;
import sample.Model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBCustomer {

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
}
