package sample.Model;

public class Customer {

    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int flDivisionId;
    private String flDivision;
    private String country;

    public Customer(int id, String name, String address, String postalCode, String phone, int flDivisionId, String flDivision, String country){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.flDivisionId = flDivisionId;
        this.flDivision = flDivision;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
