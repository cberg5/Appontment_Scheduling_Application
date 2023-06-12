package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBFLDivision;

import java.sql.SQLException;

/**
 * Division class. Used to set and retrieve data for a division object.
 */
public class FLDivision {

    private int id;
    private String name;
    private int countryId;

    /**
     * Constructor for a division object.
     * @param id
     * @param name
     * @param countryId
     */
    public FLDivision(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    /**
     * A method to retrieve a division object.
     * Gets the division ID from a customer object and compares to a list of all division objects and returns the matching division.
     * @param customer
     * @return Division
     * @throws SQLException
     */
    public static FLDivision getFLDivision(Customer customer) throws SQLException {
        int flDivisionId = customer.getFlDivisionId();
        FLDivision selectedFLDivision = null;
        ObservableList<FLDivision> allFLDivisions = FXCollections.observableArrayList();
        allFLDivisions = DBFLDivision.getAllDivisions();

        for (FLDivision flDivision : allFLDivisions) {
            if (flDivision.getId() == flDivisionId){
                selectedFLDivision = flDivision;
            }
        }
        return selectedFLDivision;
    }

    /**
     * Retrieves the ID of a division.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of a division.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of a division.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a division.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves country ID from a division
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets country ID for a division
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Overrides the toString function for Division class. Helps to properly display division info in comboboxes.
     * @return
     */
    @Override
    public String toString(){
        return (name);
    }
}
