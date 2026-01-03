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
        if (year < 1990 || year > 2026){
            throw new IllegalArgumentException("No cars was produced that year!");
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


class Car extends Vehicle {
    int numberOfDoors;

    public Car(String model, int year, double basePrice, int numberOfDoors){
        super(model, year, basePrice);
        this.numberOfDoors = numberOfDoors;
    }


    @Override
    public double calculateInsuranceFee() {
        double fee;
        if (basePrice <= 4000000){
            fee = basePrice * 0.05;
        }
        else if (basePrice > 4000000 && basePrice <= 13000000) {
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
}


class Bus extends Vehicle {
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
        else if (basePrice <= 41000000) {
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
}


public class Main {
    public static void main (String[] args) {
        Car car1 = new Car("BMW", 2020, 70000000, 4);
        System.out.println(car1.toString());
        System.out.println("Vehicle age: " + car1.getAge(2026));
        System.out.println("Insurance fee: " + car1.calculateInsuranceFee());

        System.out.println("------------------------------------------------");

        Car car2 = new Car("Volkswagen", 2001, 2500000, 4);
        System.out.println(car2.toString());
        System.out.println("Vehicle age: " + car2.getAge(2026));
        System.out.println("Insurance fee: " + car2.calculateInsuranceFee());

        System.out.println("------------------------------------------------");

        Bus bus1 = new Bus("Yutong", 2012, 40000000, 36);
        System.out.println(bus1.toString());
        System.out.println("Vehicle age: " + bus1.getAge(2026));
        System.out.println("Insurance fee: " + bus1.calculateInsuranceFee());

        System.out.println("------------------------------------------------");

        Bus bus2 = new Bus("Hyundai Motor", 2010, 25000000, 32);
        System.out.println(bus2.toString());
        System.out.println("Vehicle age: " + bus2.getAge(2026));
        System.out.println("Insurance fee: " + bus2.calculateInsuranceFee());

    }
}
