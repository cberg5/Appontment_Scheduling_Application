package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Month {
    int monthNum;
    String monthName;

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public Month(int monthNum, String monthName){
        this.monthNum = monthNum;
        this.monthName = monthName;
    }

    public static ObservableList<Month> getAllMonths(){
        ObservableList<Month> allMonths = FXCollections.observableArrayList();
        Month month;
        int monthNum = 1;
        String monthName = "January";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 2;
        monthName = "February";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 3;
        monthName = "March";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 4;
        monthName = "April";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 5;
        monthName = "May";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 6;
        monthName = "June";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 7;
        monthName = "July";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 8;
        monthName = "August";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 9;
        monthName = "September";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 10;
        monthName = "October";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 11;
        monthName = "November";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        monthNum = 12;
        monthName = "December";
        month = new Month(monthNum, monthName);
        allMonths.add(month);

        return allMonths;
    }

    @Override
    public String toString(){
        return (Integer.toString(monthNum) + ": " + monthName);
    }
}
