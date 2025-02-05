package agency;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.TimeProvider;

import static org.junit.jupiter.api.Assertions.*;

@Tag("agency")
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
            IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", invalidFutureYear, 4));
            IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", 1899, 4));

            assertEquals("Production year must be between 1900 and the current year. " + invalidFutureYear + " is invalid.", exception1.getMessage());
            assertEquals("Production year must be between 1900 and the current year. 1899 is invalid.", exception2.getMessage());

        }

        @ParameterizedTest(name = "Car creation test with invalid number of seats: {0}")
        @DisplayName("Car creation test with invalid number of seats")
        @CsvSource({
                "0",
                "-1",
                "-10"
        })
        public void carCreationTestWithInvalidNumberOfSeats(int invalidNumberOfSeats) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Corolla", 2019, invalidNumberOfSeats));
            assertEquals("Number of seats must be at least 1. " + invalidNumberOfSeats + " is invalid.", exception.getMessage());
        }

        @Test
        @DisplayName("New car price test")
        public void newCarPriceTest() {
            Car car = new Car("Toyota", "Corolla", 2024, 4);
            assertEquals(4*40, car.dailyRentalPrice());
        }

        @ParameterizedTest
        @DisplayName("Car toString test")
        @CsvSource({
                "4, 'Car Toyota Corolla 2019 (4 seats) : 80.0€'",
                "1, 'Car Toyota Corolla 2019 (1 seat) : 20.0€'"
        })
        public void carToStringTest(int numberOfSeats, String expectedString) {
            Car car = new Car("Toyota", "Corolla", 2019, numberOfSeats);
            assertEquals(expectedString, car.toString());
        }

    }

    @DisplayName("Motorbike tests")
    @Nested
    class MotorbikeTests {
        @Test
        @DisplayName("Motorbike creation test")
        public void motorbikeCreationTest() {
            Motorbike motorbike = new Motorbike("Honda", "CBR", 2020, 500);
            assertEquals("Honda", motorbike.getBrand());
            assertEquals("CBR", motorbike.getModel());
            assertEquals(2020, motorbike.getProductionYear());
            assertEquals(500*0.25, motorbike.dailyRentalPrice());
        }

        @Test
        @DisplayName("Motorbike creation test with invalid production year")
        public void motorbikeCreationTestWithInvalidProductionYear() {
            int invalidFutureYear = TimeProvider.currentYearValue() + 1;
            IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> new Motorbike("Honda", "CBR", invalidFutureYear, 500));
            IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new Motorbike("Honda", "CBR", 1899, 500));

            assertEquals("Production year must be between 1900 and the current year. " + invalidFutureYear + " is invalid.", exception1.getMessage());
            assertEquals("Production year must be between 1900 and the current year. 1899 is invalid.", exception2.getMessage());
        }

        @ParameterizedTest(name = "Motorbike creation test with invalid cylinder capacity: {0}")
        @DisplayName("Motorbike creation test with invalid cylinder capacity")
        @CsvSource({
                "49",
                "-1",
        })
        public void motorbikeCreationTestWithInvalidCylinderCapacity(int invalidCylinderCapacity) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Motorbike("Honda", "CBR", 2020, invalidCylinderCapacity));
            assertEquals("Cylinder capacity must be at least 50. " + invalidCylinderCapacity + " is invalid.", exception.getMessage());
        }

        @Test
        @DisplayName("Motorbike toString test")
        public void motorbikeToStringTest() {
            Motorbike motorbike = new Motorbike("Honda", "CBR", 2020, 500);
            assertEquals("Motorbike Honda CBR 2020 (500cm3) 125.0€", motorbike.toString());
        }

    }

    @DisplayName("Client tests")
    @Nested
    class ClientTests {
        Client client;

        @BeforeEach
        public void createClient() {
            client = new Client("John", "Doe", 1998);
        }

        @Test
        @DisplayName("Client info test")
        public void clientCreationTest() {
            assertEquals("John", client.getFirstName());
            assertEquals("Doe", client.getLastName());
        }

        @Test
        @DisplayName("Client setters test")
        public void clientToStringTest() {
            client.setFirstName("Jane");
            client.setLastName("Smith");
            client.setBirthYear(2000);
            assertEquals("Jane", client.getFirstName());
            assertEquals("Smith", client.getLastName());
            assertEquals(2000, client.getBirthYear());
        }
    }

    @DisplayName("Vehicle equal test")
    @Test
    public void vehicleEqualTest() {
        Car car1 = new Car("Toyota", "Corolla", 2019, 4);
        Car car2 = new Car("Toyota", "Corolla", 2019, 4);
        Car car3 = new Car("Toyota", "Corolla", 2019, 5);
        Car car4 = new Car("Toyota", "Corolla", 2020, 4);
        Car car5 = new Car("Toyota", "Yaris", 2019, 4);
        Car car6 = new Car("Ford", "Fiesta", 2018, 4);
        Motorbike motorbike1 = new Motorbike("Honda", "CBR", 2020, 500);

        assertTrue(car1.equals(car2));
        assertTrue(car1.equals(car3));
        assertFalse(car1.equals(car4));
        assertFalse(car1.equals(car5));
        assertFalse(car1.equals(car6));
        assertFalse(car1.equals(motorbike1));
    }
}
