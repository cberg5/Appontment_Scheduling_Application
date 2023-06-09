package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DAO.DBCountry;
import sample.DAO.DBFLDivision;

import java.sql.SQLException;

public class FLDivision {

    private int id;
    private String name;
    private int countryId;

    public FLDivision(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString(){
        return (name);
    }
}
