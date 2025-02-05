package agency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import util.TimeProvider;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("util")
@DisplayName("Util tests")
public class UtilTests {
    @Test
    @DisplayName("Test current year value")
    public void testCurrentYearValue() {
        TimeProvider timeProvider = new TimeProvider();
        assertEquals(timeProvider.currentYearValue(), new Date().getYear() + 1900);
    }
}
