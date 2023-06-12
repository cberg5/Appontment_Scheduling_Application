package sample.Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

/**
 * A time utility class with methods to assist in dealing with time zone conversions
 */
public class TimeUtility {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";

    /**
     * A method to convert the local time of the user to UTC time.
     * @param localDt
     * @return
     */
    public static LocalDateTime convertLocaltoUtc(LocalDateTime localDt){

        ZoneId zoneID = ZoneId.systemDefault();
        ZonedDateTime zoneDT = localDt.atZone((zoneID));
        LocalDateTime utc = zoneDT.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        return utc;
    }

    /**
     * A method used to populate a list of times for the start time combo box.
     * Converts the EST business hours of the business headquarters to the local time of the user and populates the list of times in the user's time zone.
     * @param time
     * @return list of meeting start times
     */
    public static ObservableList<LocalTime> populateStartTimes(LocalTime time) {

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

    /**
     * A method used to populate a list of times for the end time combo box.
     * Converts the EST business hours of the business headquarters to the local time of the user and populates the list of times in the user's time zone.
     * @param time
     * @return list of meeting end times
     */
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
