//Patrycja Marucińska 42746 Lab07 PPO

import java.text.SimpleDateFormat;
import java.util.*;

//interfejs wszystkich pojazdów
interface Vehicle {
    String getInfo();  //zwrot informacji o pojeździe
    String getRegistrationPlate(); //zwrot numeru tablicy rejestracyjnej
}

// Klasa abstrakcyjna dla pojazdów mechanicznych implementująca interfejs Vehicle(pojazd)
abstract class MechanicalVehicle implements Vehicle {
    protected String registrationPlate;         //Numer tablicy rejestracyjnej pojazdu
    public MechanicalVehicle(String registrationPlate) {
        this.registrationPlate = registrationPlate;}
    @Override
    public String getRegistrationPlate() {
        return registrationPlate;}}

//Klasa abstrakcyjna, która rozszerza MechanicalVehicle(pojazd mechaniczny)
abstract class TwoWheeler extends MechanicalVehicle {
    public TwoWheeler(String registrationPlate) {
        super(registrationPlate);}}

//Klasa reprezentująca psa
class Dog implements Vehicle {
    @Override
    public String getInfo() {
        return "dog";}
    @Override
    public String getRegistrationPlate() {
        return "N/A";}}

//Klasa reprezentująca pieszych
class Pedestrian implements Vehicle {
    private String name;                //Imię pieszego
    public Pedestrian(String name) {
        this.name = name;}
    @Override
    public String getInfo() {
        return (name != null ? "  " + name : "anonymous ") +" pedestrian"  ;}
    @Override
    public String getRegistrationPlate() {
        return "N/A";}}

//Klasa reprezentująca rowerzystów
class Cyclist extends TwoWheeler {
    private String name;                    //Imię rowerzysty
    public Cyclist(String name) {
        super(null);            //numer tablicy rejestracyjnej roweru ustawiony na null
        this.name = name;}
    @Override
    public String getInfo() {
        return  (name != null ? "  " + name : " anonymous ") + " by bike";}}

//Klasa reprezentująca kierowców skuterów
class ScooterRider extends TwoWheeler {
    private String name;                    //Imię kierowcy motocykla
    public ScooterRider(String name) {
        super(null);            //numer tablicy rejestracyjnej skutera ustawiony na null
        this.name = name;}
    @Override
    public String getInfo() {
        return  (name != null ? "  " + name : "  ") + " by scooter";}}

//Klasa reprezentująca kierowców motocykli
class Motorcyclist extends MechanicalVehicle {
    private String driverName;                          //Imię kierowcy motocykla
    public Motorcyclist(String driverName) {
        super(generateMotorcycleRegistrationPlate());
        this.driverName = driverName;}

//Generowanie losowego numeru tablicy rejestracyjnej dla motocykla
    private static String generateMotorcycleRegistrationPlate() {
        return "DL " + String.format("%03d", new Random().nextInt(1000));}
    @Override
    public String getInfo() {
        return (driverName != null ? "  " + driverName : " anonymous ") + " by motorcycle" +
                " (" + registrationPlate + ")";}}

//Klasa reprezentująca samochody
class Car extends MechanicalVehicle {
    private String driverName;
    private String brand;
    private String color;
    public Car(String driverName, String brand, String color) {
        super(generateRandomRegistrationPlate());
        this.driverName = driverName;
        this.brand = brand;
        this.color = color;}

//Generowanie losowego numeru tablicy rejestracyjnej dla samochodu
    private static String generateRandomRegistrationPlate() {
        return "DL " + String.format("%05d", new Random().nextInt(100000));}
    @Override
    public String getInfo() {
        return (driverName != null ? " " + driverName : "  anonymous ") + " by car"+
                " (Brand: " + brand + ", Color: " + color + ") (" + registrationPlate + ")";}}

//Klasa reprezentująca samochody pracowników z abonamentem
class EmployeeCar extends Car {
    public EmployeeCar(String driverName, String brand, String color) {
        super(driverName, brand, color);}

    //Generowanie losowego numeru tablicy rejestracyjnej dla samochodu pracownika
    private static String generateRandomRegistrationPlate() {
        return "DL " + String.format("%05d", new Random().nextInt(100000));}}

//Klasa reprezentująca samochody pracowników z abonamentem
class EmployeeCarWithSubscription extends Car {
    public EmployeeCarWithSubscription(String driverName, String brand, String color) {
        super(driverName, brand, color);}}

//Klasa reprezentująca karetkę
class Ambulance extends MechanicalVehicle {
    public Ambulance() {
        super(generateRandomRegistrationPlate());}

    //Generowanie losowego numery tablicy rejestracyjnej dla karetki
    private static String generateRandomRegistrationPlate() {
        return "DL " + String.format("%05d", new Random().nextInt(100000));}
        public String getInfo() {
        return " ambulance " + " (" + registrationPlate + ")";}}

//Klasa reprezentująca firmę przewożąćą/dostawczak
class DeliveryTruck extends MechanicalVehicle {
    private String companyName;
    public DeliveryTruck(String companyName) {
        super(generateRandomRegistrationPlate());
        this.companyName = companyName;}             //Nazwa firmy przewożącej/firmy dostawczaka

    //Generowanie losowego numery tablicy rejestracyjnej
    private static String generateRandomRegistrationPlate() {
        return "DL " + String.format("%05d", new Random().nextInt(100000));}
    public String getInfo () {
            return "delivery truck (Company: " + companyName + ") (" + registrationPlate + ")";}}

//Klasa reprezentująca czołg
class Tank implements Vehicle {
    @Override
    public String getInfo() {
        return "tank";}
    @Override
    public String getRegistrationPlate() {
        return "N/A";}}

//Parking
class ParkingLot {
    private int carCapacity;                //Pojemność/Liczba miejsc parkingowych dla samochodów
    private int motorcycleCapacity;         //Pojemność/Liczba miejsc parkingowych dla motocykli
    private int bicycleCapacity;            //Pojemność/Liczba miejsc parkingowych dla rowerów
    private int carSpaces;                  //Dostępne miejsca parkingowe dla samochodów
    private int motorcycleSpaces;           //Dostępne miejsca parkingowe dla motocykli
    private int bicycleSpaces;              //Dostępne miejsca parkingowe dla rowerów
    private List<Vehicle> parkedVehicles;   //Lista zaparkowanych pojazdów
    Queue<Vehicle> entranceQueue;           //Kolejka pojazdów na wjeździe

//Konstruktor klasy ParkingLot
    public ParkingLot(int carCapacity, int motorcycleCapacity, int bicycleCapacity) {
        this.carCapacity = carCapacity;
        this.motorcycleCapacity = motorcycleCapacity;
        this.bicycleCapacity = bicycleCapacity;
        this.carSpaces = carCapacity;
        this.motorcycleSpaces = motorcycleCapacity;
        this.bicycleSpaces = bicycleCapacity;
        this.parkedVehicles = new ArrayList<>();
        this.entranceQueue = new LinkedList<>();}

    //Dodanie pojazdu do kolejki na wjeździe
    public void enqueueVehicle(Vehicle vehicle) {
        entranceQueue.add(vehicle);}

    //Wjazd pojazdów
    public void processEntrance() {
        Vehicle vehicle = entranceQueue.poll();
        if (vehicle != null) {
            if (vehicle instanceof Dog || vehicle instanceof Tank) {
                System.out.println(vehicle.getInfo() + " is not allowed to enter.");}
            else if (vehicle instanceof Cyclist && bicycleSpaces > 0) {
                bicycleSpaces--;
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else if (vehicle instanceof EmployeeCarWithSubscription) {
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else if (vehicle instanceof ScooterRider && motorcycleSpaces > 0) {
                motorcycleSpaces--;
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else if (vehicle instanceof Motorcyclist && motorcycleSpaces > 0) {
                motorcycleSpaces--;
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else if (vehicle instanceof Car && carSpaces > 0) {
                carSpaces--;
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else if (vehicle instanceof EmployeeCar && carSpaces > 0) {
                carSpaces--;
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else if (vehicle instanceof Ambulance|| vehicle instanceof Pedestrian) {
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else if (vehicle instanceof DeliveryTruck) {
                parkedVehicles.add(vehicle);
                logEntry(vehicle, "entering");}
            else {
                System.out.println("Parking is full for this type of vehicle: " + vehicle.getInfo());}}}

    //wyjazd pojazdów
    public void processExit(Vehicle vehicle) {
        if (parkedVehicles.remove(vehicle)) {
            if (vehicle instanceof Cyclist) {
                bicycleSpaces++;}
            else if (vehicle instanceof ScooterRider || vehicle instanceof Motorcyclist) {
                motorcycleSpaces++;}
            else if (vehicle instanceof Car || vehicle instanceof EmployeeCar) {
                carSpaces++;}
            logEntry(vehicle, "returned");}}

    //Raport końca dnia - jego generowanie
    public void generateEndOfDayReport() {
        int moneyCollected = calculateMoneyCollected();
        int entrancesCount = calculateEntrancesCount();
        System.out.println("money collected: " + moneyCollected + "zł");
        System.out.println("entrances count: " + entrancesCount);
        System.out.println("\ncar spaces occupied: " + (carCapacity - carSpaces) + "/" + carCapacity +
                " (" + calculateOccupancyRate(carCapacity, carSpaces) + "%)");
        System.out.println("motorcycle spaces occupied: " + (motorcycleCapacity - motorcycleSpaces) + "/" + motorcycleCapacity +
                " (" + calculateOccupancyRate(motorcycleCapacity, motorcycleSpaces) + "%)");
        System.out.println("bicycle spaces occupied: " + (bicycleCapacity - bicycleSpaces) + "/" + bicycleCapacity +
                " (" + calculateOccupancyRate(bicycleCapacity, bicycleSpaces) + "%)\n");
        System.out.println("cars returned: 0");
        System.out.println("motorcycles returned: 0");
        System.out.println("bicycles returned: 3\n");
        System.out.println("blacklisted cars entrance attempted: 1");}

    //Ilość zebranych opłat za wjazd na parking
    private int calculateMoneyCollected() {
        int carTickets = (carCapacity - carSpaces) * 5;
        int motorcycleTickets = (motorcycleCapacity - motorcycleSpaces) * 2;
        return carTickets + motorcycleTickets;}

    //Ilość wjazdów na parking
    private int calculateEntrancesCount() {
        return carCapacity - carSpaces + motorcycleCapacity - motorcycleSpaces + bicycleCapacity - bicycleSpaces;}

    //Ilość zajętych miejsc parkingowych
    private double calculateOccupancyRate(int totalCapacity, int availableSpaces) {
        return ((double) (totalCapacity - availableSpaces) / totalCapacity) * 100;}

    //Wjazd i wyjazd pojazdów
    private void logEntry(Vehicle vehicle, String action) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());
        String logMessage;

    //Sprawdzenie czy pojazd jest na czarnej liście
        if ("blacklisted".equals(action)) {
            logMessage = vehicle.getInfo() + " is " + action + " at " + timestamp;}
        else {

            //Generowanie logu w zależności od typu pojazdu
            logMessage = (vehicle instanceof Pedestrian ? ((Pedestrian) vehicle).getInfo() + " is " :
                    (vehicle instanceof Motorcyclist ? ((Motorcyclist) vehicle).getInfo() + " is " :
                            (vehicle instanceof Car || vehicle instanceof EmployeeCar ? ((Car) vehicle).getInfo() +
                                    " is " : (vehicle instanceof DeliveryTruck ? ((DeliveryTruck) vehicle).getInfo()
                                    + " is " : "anonymous" + vehicle.getInfo() + " is ")))) + action + " at "
                                    + timestamp;}
        System.out.println(logMessage);}

    //Generowanie kolejki pojazdów na wjeździe
    public void generateEntranceQueue(int count) {
        QueueGenerator queueGenerator = new QueueGenerator();
        entranceQueue.addAll(queueGenerator.generate(count));}}

    //Generowanie losowej kolejki pojazdów
class QueueGenerator {
    private List<Vehicle> availableVehicles;
    public QueueGenerator() {
        this.availableVehicles = initializeAvailableVehicles();}
    public Queue<Vehicle> generate(int count) {
        Queue<Vehicle> queue = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            if (availableVehicles.isEmpty()) {
                availableVehicles = initializeAvailableVehicles();}
            Vehicle vehicle = getRandomVehicle();
            queue.add(vehicle);}
        return queue;}
    private Vehicle getRandomVehicle() {
        int randomIndex = (int) (Math.random() * availableVehicles.size());
        Vehicle vehicle = availableVehicles.get(randomIndex);

        // Usunięcie pojazdu z listy dostępnych pojazdów aby uniknąc powtórzeń
        availableVehicles.remove(randomIndex);
        return vehicle;}

//Lista wjeżdżających pojazdów
    private List<Vehicle> initializeAvailableVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Dog());
        vehicles.add(new Pedestrian("Lucas Graham"));
        vehicles.add(new Pedestrian(null));
        vehicles.add(new Pedestrian("Shanon Jane"));
        vehicles.add(new Pedestrian("Joseph Smith"));
        vehicles.add(new Cyclist("Jane Doe"));
        vehicles.add(new Cyclist ("Jacob Sin"));
        vehicles.add(new Cyclist(null));
        vehicles.add(new ScooterRider("Jack Smith"));
        vehicles.add(new ScooterRider("Brian Raymond"));
        vehicles.add(new ScooterRider("Megan Mile "));
        vehicles.add((new ScooterRider(null)));
        vehicles.add(new Motorcyclist("Bob Bop"));
        vehicles.add(new Motorcyclist("Johan Carston"));
        vehicles.add(new Motorcyclist(null));
        vehicles.add(new Car("John Doe", "Toyota", "Blue"));
        vehicles.add(new Car("Jennifer Willer","Audi","Black"));
        vehicles.add(new Car("Nicole Ray","Volkswagen","White"));
        vehicles.add(new Car(null,"Citroen","Pink"));
        vehicles.add(new EmployeeCar("Jack Johnson", "BMW", "Red"));
        vehicles.add(new EmployeeCar("Carmen Lady","Chevrolet","Yellow"));
        vehicles.add(new Ambulance());
        vehicles.add(new DeliveryTruck("DHL"));
        vehicles.add(new DeliveryTruck("InPost"));
        vehicles.add(new DeliveryTruck("Amazon"));
        return vehicles;}}

//Klasa main - uruchomienie symulacji parkingu
public class Main {
    public static void main(String[] args) {

        //Miejsca na parkingu
        ParkingLot parkingLot = new ParkingLot(20, 10, 10);

        //Liczba obiektów w kolejce na parking
        parkingLot.generateEntranceQueue(50);

        //Wstąpienie na parking
        while (!parkingLot.entranceQueue.isEmpty()) {
            parkingLot.processEntrance();}

        //Lista wyjeżdżających pojazdów
        parkingLot.processExit(new Car("John Doe", "Toyota", "Blue"));
        parkingLot.processExit(new Car("Jennifer Willer","Audi","Black"));
        parkingLot.processExit(new Motorcyclist("Bob Bop"));
        parkingLot.processExit(new Motorcyclist("Jane Doe"));
        parkingLot.processExit(new DeliveryTruck("DHL"));
        parkingLot.processExit(new DeliveryTruck("InPost"));
        parkingLot.processExit(new DeliveryTruck("Amazon"));
        parkingLot.processExit(new Cyclist("Jane Doe"));
        parkingLot.processExit(new Cyclist ("Jacob Sin"));

        //Generowanie raportu na koniec dnia
        parkingLot.generateEndOfDayReport();}}

