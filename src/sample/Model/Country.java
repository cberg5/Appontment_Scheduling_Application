package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBCountry;

import java.sql.SQLException;

/**
 * Country class. Used to set and retrieve data for country objects.
 */
public class Country {

    private int id;
    private String name;

    /**
     * Constructor for country object.
     * @param id
     * @param name
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * A method to retrieve a country object.
     * Gets the country ID from a customer object and compares to a list of all countries and returns the matching country.
     * @param customer
     * @return
     * @throws SQLException
     */
    public static Country getCountry(Customer customer) throws SQLException {
        int countryId = customer.getCountryId();
        Country selectedCountry = null;
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        allCountries = DBCountry.getAllCountries();

        for(Country country : allCountries){
            if(country.getId() == countryId){
                selectedCountry = country;
            }
        }
        return selectedCountry;
    }

    /**
     * Retrieves country ID from country.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves name from country
     * @return country's name
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides the toString function for Country class. Helps to properly display country info in comboboxes.
     * @return
     */
    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
