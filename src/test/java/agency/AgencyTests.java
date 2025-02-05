package agency;

import agency.criteria.BrandCriterion;
import agency.criteria.MaxPriceCriterion;
import agency.exceptions.UnknownVehicleException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.TimeProvider;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

        assertEquals(car1, car2);
        assertEquals(car1, car3);
        assertNotEquals(car1, car4);
        assertNotEquals(car1, car5);
        assertNotEquals(car1, car6);
        assertNotEquals(car1, motorbike1);
    }

    @DisplayName("Rental agency tests")
    @Nested
    class RentalAgencyTests {
        RentalAgency agency;
        Car car1;
        Car car2;
        Car car3;
        Motorbike motorbike1;
        Motorbike motorbike2;
        Client client1;
        Client client2;

        @BeforeEach
        public void createAgency() {
            agency = new RentalAgency();
            car1 = new Car("Toyota", "Corolla", 2019, 4);
            car2 = new Car("Toyota", "Yaris", 2019, 4);
            car3 = new Car("Ford", "Fiesta", 2018, 4);
            motorbike1 = new Motorbike("Honda", "CBR", 2020, 500);
            motorbike2 = new Motorbike("Yamaha", "R1", 2021, 1000);
            client1 = new Client("John", "Doe", 1998);
            client2 = new Client("Jane", "Smith", 2000);

            agency.add(car1);
            agency.add(car2);
            agency.add(car3);
            agency.add(motorbike1);
            agency.add(motorbike2);
        }

        @Test
        @DisplayName("Create agency with vehicles test")
        public void createAgencyWithVehiclesTest() {
            List<Vehicle> listOfVehicles = List.of(car1, car2, car3, motorbike1, motorbike2);
            RentalAgency agency2 = new RentalAgency(listOfVehicles);
            assertEquals(5, agency2.getVehicles().size());
            assertTrue(agency2.getVehicles().contains(car1));
            assertTrue(agency2.getVehicles().contains(car2));
            assertTrue(agency2.getVehicles().contains(car3));
            assertTrue(agency2.getVehicles().contains(motorbike1));
            assertTrue(agency2.getVehicles().contains(motorbike2));
        }

        @Test
        @DisplayName("Add vehicle test")
        public void addVehicleTest() {
            Car tempCar = new Car("Ford", "Ka", 2005, 2);
            assertTrue(agency.add(tempCar));

            assertFalse(agency.add(car1));
            assertEquals(6, agency.getVehicles().size());
            assertTrue(agency.getVehicles().contains(car1));
            assertTrue(agency.getVehicles().contains(car2));
            assertTrue(agency.getVehicles().contains(motorbike2));
        }

        @Test
        @DisplayName("Select vehicles by brand test")
        public void selectVehiclesTest() {
            assertEquals(5, agency.getVehicles().size());
            assertEquals(2, agency.select(new BrandCriterion("Toyota")).size());
            assertEquals(1, agency.select(new BrandCriterion("Ford")).size());
            assertThat(agency.select(new BrandCriterion("Toyota"))).contains(car1, car2);
        }

        @Test
        @DisplayName("Select vehicles by max price test")
        public void selectVehiclesByMaxPriceTest() {
            assertEquals(3, agency.select(new MaxPriceCriterion(100)).size());
            assertEquals(5, agency.select(new MaxPriceCriterion(300)).size());
        }

        @Test
        @DisplayName("Remove vehicle test")
        public void removeVehicleTest() {
            assertEquals(5, agency.getVehicles().size());
            agency.remove(car1);
            assertEquals(4, agency.getVehicles().size());
            assertFalse(agency.getVehicles().contains(car1));
            assertThrows(UnknownVehicleException.class, () -> agency.remove(car1));
        }

        @Test
        @DisplayName("Print selected vehicles test")
        public void printSelectedVehiclesTest() {
            agency.printSelectedVehicles(new BrandCriterion("Toyota"));
            agency.printSelectedVehicles(new MaxPriceCriterion(100));
        }

        @Test
        @DisplayName("Rental creation test")
        public void rentalCreationTest() {
            double price = agency.rentVehicle(client1, car1);
            assertEquals(4*20, price);
        }

        @Test
        @DisplayName("Rental creation test with unknown vehicle")
        public void rentalCreationTestWithUnknownVehicle() {
            assertThrows(UnknownVehicleException.class, () -> agency.rentVehicle(client1, new Car("Renault", "Zoe", 2019, 4)));
        }

        @Test
        @DisplayName("Rental creation test with already rented vehicle")
        public void rentalCreationTestWithAlreadyRentedVehicle() {
            agency.rentVehicle(client1, car1);
            assertThrows(IllegalStateException.class, () -> agency.rentVehicle(client2, car1));
        }

        @Test
        @DisplayName("Rental creation test with client already having a rented vehicle")
        public void rentalCreationTestWithClientAlreadyHavingRentedVehicle() {
            agency.rentVehicle(client1, car1);
            assertThrows(IllegalStateException.class, () -> agency.rentVehicle(client1, car2));
        }

        @Test
        @DisplayName("Return vehicle test")
        public void returnVehicleTest() {
            agency.rentVehicle(client1, car1);
            assertTrue(agency.aVehicleIsRentedBy(client1));
            agency.returnVehicle(client1);
            assertFalse(agency.aVehicleIsRentedBy(client1));
        }

        @Test
        @DisplayName("Rented vehicle list test")
        public void rentedVehicleListTest() {
            agency.rentVehicle(client1, car1);
            assertThat(agency.allRentedVehicles()).contains(car1);
        }
    }
}
