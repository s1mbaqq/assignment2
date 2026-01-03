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
            throw new IllegalArgumentException("Model mustn't be null or empty!");
        };
        this.model = model;
    }
    public void setYear(int year) {
        if (year < 1885 || year > 2026){
            throw new IllegalArgumentException("The car was not produced that year!");
        };
        this.year = year;
    }
    public void setBasePrice(double basePrice) {
        if (basePrice < 0){
            throw new IllegalArgumentException("The price of a car cannot be negative!");
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


public class Main {
    public static void main (String[] args) {
        Servicable car1 = new Car("Audi", 2023, 70000000, 4);
        car1.performService();
        System.out.println("The mileage of this car is: " + car1.getServiceIntervalKm());
    }
}
