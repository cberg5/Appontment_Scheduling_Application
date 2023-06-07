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

    public static ObservableList<LocalTime> populateStartTimes(LocalTime time) {

    /*    ZoneId localZone = ZoneId.systemDefault();
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
       */
        ObservableList<LocalTime> meetingTimes = FXCollections.observableArrayList();

        ZoneId localZone = ZoneId.systemDefault();
        ZoneId businessZone = ZoneId.of("America/New_York");
        ZonedDateTime businessClose = LocalDate.now().atTime(22, 0).atZone(businessZone);
        ZonedDateTime meetingStart = ZonedDateTime.of(LocalDate.now(), time, businessZone);

            for (ZonedDateTime zt = meetingStart; zt.isBefore(businessClose); zt = zt.plusHours(1)) {
                LocalTime lt = zt.withZoneSameInstant(localZone).toLocalTime();
                meetingTimes.add(lt);
            }

        return meetingTimes;
    }

    public static ObservableList<LocalTime> populateEndTimes(LocalTime time) {

        ObservableList<LocalTime> meetingTimes = FXCollections.observableArrayList();

        ZoneId localZone = ZoneId.systemDefault();
        ZoneId businessZone = ZoneId.of("America/New_York");
        ZonedDateTime businessClose = LocalDate.now().atTime(23, 0).atZone(businessZone);
        ZonedDateTime meetingEnd = ZonedDateTime.of(LocalDate.now(), time, businessZone);

        for (ZonedDateTime zt = meetingEnd; zt.isBefore(businessClose); zt = zt.plusHours(1)) {
            LocalTime lt = zt.withZoneSameInstant(localZone).toLocalTime();
            meetingTimes.add(lt);
        }

        return meetingTimes;
    }
}
