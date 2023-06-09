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
        Month jan = null;
        jan.setMonthNum(1);
        jan.setMonthName("January");
        allMonths.add(jan);

        Month feb = null;
        feb.setMonthNum(2);
        feb.setMonthName("February");
        allMonths.add(feb);

        Month mar = null;
        mar.setMonthNum(3);
        mar.setMonthName("March");
        allMonths.add(mar);

        Month apr = null;
        apr.setMonthNum(4);
        apr.setMonthName("April");
        allMonths.add(apr);

        Month may = null;
        may.setMonthNum(5);
        may.setMonthName("May");
        allMonths.add(may);

        Month jun = null;
        jun.setMonthNum(6);
        jun.setMonthName("June");
        allMonths.add(jun);

        Month jul = null;
        jul.setMonthNum(7);
        jul.setMonthName("July");
        allMonths.add(jul);

        Month aug = null;
        aug.setMonthNum(8);
        aug.setMonthName("August");
        allMonths.add(aug);

        Month sep = null;
        sep.setMonthNum(9);
        sep.setMonthName("September");
        allMonths.add(sep);

        Month oct = null;
        oct.setMonthNum(10);
        oct.setMonthName("October");
        allMonths.add(oct);

        Month nov = null;
        nov.setMonthNum(11);
        nov.setMonthName("November");
        allMonths.add(nov);

        Month dec = null;
        dec.setMonthNum(12);
        dec.setMonthName("December");
        allMonths.add(dec);

        return allMonths;
    }

    @Override
    public String toString(){
        return (Integer.toString(monthNum) + ": " + monthName);
    }
}
