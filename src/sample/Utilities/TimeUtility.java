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

    public static LocalDateTime convertUTCtoLocal(LocalDateTime localDT){

        ZonedDateTime zoneDT = localDT.atZone(ZoneId.systemDefault());
        ZonedDateTime utc = zoneDT.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime convertedUTC = utc.toLocalDateTime();
        return
    }
}
