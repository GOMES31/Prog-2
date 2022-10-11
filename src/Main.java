public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("M5","BMW",2018,52250.25);
        System.out.println(vehicle.toString());
        System.out.println("Price after 3 years: " + vehicle.getVehiclePrice(vehicle.getReleaseYear() + 2));
    }
}