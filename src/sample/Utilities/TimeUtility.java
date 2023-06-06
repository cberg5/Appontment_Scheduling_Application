package sample.Utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtility {

    public static LocalDateTime convertLocaltoUtc(LocalDateTime localDt){

        ZoneId zoneID = ZoneId.systemDefault();
        ZonedDateTime zoneDT = localDt.atZone((zoneID));
        LocalDateTime utc = zoneDT.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        return utc;
    }
}
