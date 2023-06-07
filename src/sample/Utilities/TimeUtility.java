package sample.Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

public class TimeUtility {

    public static LocalDateTime convertLocaltoUtc(LocalDateTime localDt){

        ZoneId zoneID = ZoneId.systemDefault();
        ZonedDateTime zoneDT = localDt.atZone((zoneID));
        LocalDateTime utc = zoneDT.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        return utc;
    }

    public static ObservableList<LocalTime> populateMeetingTimes(LocalTime time) {

        ZoneId localZone = ZoneId.systemDefault();
        ZoneId businessZone = ZoneId.of("America/New_York");
        int hours = 13;

        ObservableList<LocalTime> meetingTimes = FXCollections.observableArrayList();
        ZonedDateTime businessZDT = ZonedDateTime.of(LocalDate.now(), time, businessZone);
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(businessZDT.toInstant(), localZone);
        int localStartingHour = localZDT.getHour();
        int totalHours = localStartingHour + hours;
        int pastMidnight = 0;

        for(int i = localStartingHour; i <= totalHours; i++) {
            if(i<24) {
                meetingTimes.add(LocalTime.of(i,0));
            }
            if(i>23) {
                meetingTimes.add(LocalTime.of(pastMidnight,0));
                pastMidnight += 1;
            }
        }
        return meetingTimes;
    }
}
