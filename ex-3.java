import java.util.ArrayList;
import java.util.Scanner;

abstract class Vehicle {
    protected int id;
    protected static int idGen;
    protected String model;
    protected int year;
    protected double basePrice;

    public Vehicle(String model, int year, double basePrice){
        this.id = idGen++;
        setModel(model);
        setYear(year);
        setBasePrice(basePrice);
    }

    public int getId() {
        return id;
    }
    public String getModel() {
        return model;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public int getAge(int currentYear){
        return currentYear - year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        if (model == null || model.isEmpty()){
            throw new IllegalArgumentException("Model must not be null or empty!");
        };
        this.model = model;
    }
    public void setYear(int year) {
        if (year < 1990 || year > 2026){
            throw new IllegalArgumentException("No cars produced that year!");
        };
        this.year = year;
    }
    public void setBasePrice(double basePrice) {
        if (basePrice < 0){
            throw new IllegalArgumentException("The price of a car can't be negative!");
        };
        this.basePrice = basePrice;
    }

    public abstract double calculateInsuranceFee();

    public String toString(){
        String info = "Id: " + id + " | Model: " + model + " | Year: " + year + " | BasePrice: " + basePrice;
        return info;
    }
}


class Car extends Vehicle implements Servicable {
    int numberOfDoors;

    public Car(String model, int year, double basePrice, int numberOfDoors){
        super(model, year, basePrice);
        this.numberOfDoors = numberOfDoors;
    }


    @Override
    public double calculateInsuranceFee() {
        double fee;
        if (basePrice <= 5000000){
            fee = basePrice * 0.05;
        }
        else if (basePrice > 5000000 && basePrice <= 15000000) {
            fee = basePrice * 0.08;
        }
        else {
            fee = basePrice * 0.1;
        }
        return fee;
    }

    @Override
    public String toString(){
        return super.toString() + " | Doors: " + numberOfDoors;
    }

    @Override
    public void performService() {
        System.out.println(model + ": " + "Checking engine and changing oil");
    }

    @Override
    public int getServiceIntervalKm() {
        return 15339;
    }
}


class Bus extends Vehicle implements Servicable {
    int passengerCapacity;

    public Bus(String model, int year, double basePrice, int passengerCapacity){
        super(model, year, basePrice);
        this.passengerCapacity = passengerCapacity;
    }


    @Override
    public double calculateInsuranceFee() {
        double fee;
        if (basePrice <= 10000000) {
            fee = basePrice * 0.1;
        }
        else if (basePrice <= 30000000) {
            fee = basePrice * 0.15;
        }
        else {
            fee = basePrice * 0.2;
        }
        return fee;
    }


    @Override
    public String toString(){
        return super.toString() + " | Capacity: " + passengerCapacity;
    }

    @Override
    public void performService() {
        System.out.println(model + ": " + "Checking brakes and tires for safety");
    }

    @Override
    public int getServiceIntervalKm() {
        return 5987;
    }
}


class FleetApp {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to Fleet App!");
            System.out.println("1. Print all vehicles");
            System.out.println("2. Add new car");
            System.out.println("3. Add new bus");
            System.out.println("4. Show total yearly insurance fees");
            System.out.println("5. Show vehicles older than N years");
            System.out.println("6. Perform service for all vehicles");
            System.out.println("7. Quit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> printAllVehicles();
                case 2 -> addNewCar();
                case 3 -> addNewBus();
                case 4 -> totalInsFees();
                case 5 -> vehiclesOlderThanN();
                case 6 -> performVehicles();
                case 7 -> running = false;
                default -> System.out.println("Invalid option");
            }

            if (choice == 7){
                System.out.println("Goodbye!!!");
            }
        }
    }

    private void printAllVehicles() {
        if (vehicles.size() == 0) {
            System.out.println("No vehicles in the fleet");
        }
        else {
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println(vehicles.get(i));
            }
        }
    }


    private void addNewCar() {
        System.out.print("Car's model: ");
        String model = scanner.nextLine();

        System.out.print("Year of the car: ");
        int year = scanner.nextInt();

        System.out.print("Base price of the car");
        int basePrice = scanner.nextInt();

        System.out.print("Number of doors: ");
        int numDoor = scanner.nextInt();

        vehicles.add(new Car(model, year, basePrice, numDoor));
        System.out.println("The Car has been added to the fleet!");
    }


    private void addNewBus() {
        System.out.print("Bus model: ");
        String model = scanner.nextLine();

        System.out.print("Year of the bus: ");
        int year = scanner.nextInt();

        System.out.print("Base price of the bus");
        int basePrice = scanner.nextInt();

        System.out.print("Passenger capacity: ");
        int passCapacity = scanner.nextInt();

        vehicles.add(new Bus(model, year, basePrice, passCapacity));
        System.out.println("The Bus has been added to the fleet!");
    }


    private void totalInsFees(){
        double total = 0;
        for (int i = 0; i < vehicles.size(); i++){
            Vehicle veh = vehicles.get(i);
            total += veh.calculateInsuranceFee();
        }
        System.out.println("Total of all Insurance fees: " + total);
    }


    private void vehiclesOlderThanN(){
        System.out.print("Print older than how much: ");
        int N = scanner.nextInt();
        int age;
        for (int i = 0; i < vehicles.size(); i++){
            Vehicle veh = vehicles.get(i);
            age = veh.getAge(2026);
            if (age > N){
                System.out.println(veh.toString());
            }
        }
    }


    private void performVehicles(){
        System.out.println("Service for all vehicles");

        for (int i = 0; i < vehicles.size(); i++){
            Vehicle veh = vehicles.get(i);

            if (veh instanceof Servicable){
                Servicable s = (Servicable) veh;
                s.performService();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new FleetApp().run();
    }
}
