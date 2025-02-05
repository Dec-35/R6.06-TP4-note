package agency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.TimeProvider;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Agency tests")
public class AgencyTests {
    @DisplayName("Car tests")
    @Nested
    class CarTests {
        @Test
        @DisplayName("Car creation test")
        public void carCreationTest() {
            Car car = new Car("Toyota", "Corolla", 2019, 4);
            assertEquals("Toyota", car.getBrand());
            assertEquals("Corolla", car.getModel());
            assertEquals(2019, car.getProductionYear());
            assertEquals(4*20, car.dailyRentalPrice());
        }

        @Test
        @DisplayName("Car creation test with invalid production year")
        public void carCreationTestWithInvalidProductionYear() {
            int invalidFutureYear = TimeProvider.currentYearValue() + 1;
            assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", invalidFutureYear, 4));
            assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", 1899, 4));
        }

        @ParameterizedTest(name = "Car creation test with invalid number of seats: {0}")
        @DisplayName("Car creation test with invalid number of seats")
        @CsvSource({
                "0",
                "-1",
                "-10"
        })
        public void carCreationTestWithInvalidNumberOfSeats(int invalidNumberOfSeats) {
            assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", 2019, invalidNumberOfSeats));
        }

        @Test
        @DisplayName("New car price test")
        public void newCarPriceTest() {
            Car car = new Car("Toyota", "Corolla", 2024, 4);
            assertEquals(4*40, car.dailyRentalPrice());
        }

    }
}
