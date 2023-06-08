package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBCountry;

import java.sql.SQLException;

public class Country {

    private int id;
    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return (Integer.toString(id) + ": " + name);
    }
}
