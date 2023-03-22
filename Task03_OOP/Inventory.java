package Task03_OOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Inventory {
    private List<Vehicle> vehicles;

    public Inventory() {
        this.vehicles = new ArrayList<>();
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        List<Vehicle> vehicles = Arrays.asList(
                new Car("Audi", "A8", 2022, "Red", 120590, 4, 5, false),
                new Car("Audi", "E-Tron", 2023, "Blue", 90510, 4, 5, true),
                new Car("BMW", "i7", 2020, "Black", 119220, 4, 5, false),
                new Car("BMW", "iX", 2019, "Red", 108675, 4, 5, true),
                new Car("BMW", "X7", 2023, "Black", 145350, 5, 6, false),
                new Car("Ford", "Bronco", 2020, "Black", 76580, 4, 5, true),
                new Car("Ford", "EcoSport", 2022, "Black", 28395, 5, 6, false),
                new Car("Ford", "Expedition", 2021, "Black", 86615, 4, 5, true),
                new Truck("Nissan", "Frontier", 2021, "Green", 39120, 59, 1460),
                new Truck("Nissan", "Titan XD", 2022, "Red", 65320, 75, 1560),
                new Truck("Honda", "Ridge-line", 2020, "Green", 46230, 55, 1265),
                new Truck("Chevrolet", "Colorado", 2023, "Red", 46800, 59, 1460),
                new Truck("Chevrolet", "Silverado 1500", 2023, "Silver", 71500, 59, 1460)
        );

        inventory.setVehicles(vehicles);
        System.out.println("Vehicle Avg Price: " + inventory.getAveragePrice());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--------------------------------------------");
            System.out.println("Search Vehicles By Make and Model");
            System.out.println("--------------------------------------------");
            System.out.print("Enter Make: ");
            String make = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();

            if (make.equalsIgnoreCase("99") || model.equalsIgnoreCase("99")) {
                scanner.close();
                System.exit(0);
            }

            inventory.searchByMakeAndModel(make, model)
                    .stream()
                    .forEach(vehicle -> vehicle.showDetails());

        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public double getAveragePrice() {

        //List<Vehicle> filteredVehicles = new ArrayList<>();
        //double total = 0;
        //for (Vehicle v : getVehicles()) {
        //    total += v.getPrice();
        //}
        //return total / getVehicles().size();

        // Using Stream API
        return getVehicles()
                .stream()
                .mapToDouble(Vehicle::getPrice)
                .average()
                .orElse(0);
    }

    public List<Vehicle> searchByMakeAndModel(String make, String model) {

        //List<Vehicle> filteredVehicles = new ArrayList<>();
        //for (Vehicle vehicle : getVehicles()) {
        //
        //    // filter by make
        //    if (!make.isBlank()
        //            && model.isBlank()
        //            && vehicle.getMake().equalsIgnoreCase(make)
        //    ) {
        //        filteredVehicles.add(vehicle);
        //    }
        //
        //    // filter by model
        //    if (make.isBlank()
        //            && !model.isBlank()
        //            && vehicle.getModel().equalsIgnoreCase(model)
        //    ) {
        //        filteredVehicles.add(vehicle);
        //    }
        //
        //    // filter by make and model
        //    if (!make.isBlank()
        //            && !model.isBlank()
        //            && vehicle.getMake().equalsIgnoreCase(make)
        //            && vehicle.getModel().equalsIgnoreCase(model)
        //    ) {
        //        filteredVehicles.add(vehicle);
        //    }
        //}
        //return filteredVehicles;


        // Using Stream API
        Predicate<Vehicle> predicate = vehicle -> true;

        // filter by make
        if (!make.isBlank() && model.isBlank()) {
            predicate = vehicle -> vehicle.getMake().equalsIgnoreCase(make);
        }

        // filter by model
        if (make.isBlank() && !model.isBlank()) {
            predicate = vehicle -> vehicle.getModel().equalsIgnoreCase(model);
        }

        // filter by make and model
        if (!make.isBlank() && !model.isBlank()) {
            predicate = vehicle -> vehicle.getMake().equalsIgnoreCase(make)
                    &&
                    vehicle.getModel().equalsIgnoreCase(model);
        }

        return getVehicles()
                .stream()
                .filter(predicate).collect(Collectors.toList());

    }


}
