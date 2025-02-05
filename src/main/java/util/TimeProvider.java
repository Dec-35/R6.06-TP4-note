package util;

import java.util.Date;

public class TimeProvider {

    /**
     * Get the current year.
     * @return the current year value
     */
    public static int currentYearValue() {
        return new Date().getYear() + 1900;
    }
}
