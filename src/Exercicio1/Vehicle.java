package Exercicio1;

import java.util.Scanner;
public class Vehicle{
    private String model;
    private String brand;
    private int release_year;

    private double price;

    public Vehicle(){
    }

    public Vehicle(String _model, String _brand, int _year, double _price){
        this.model = _model;
        this.brand = _brand;
        this.release_year = _year;
        this.price = _price;
    }

    public void setModel(String _model){
        this.model = _model;
    }

    public String getModel() {
        return this.model;
    }

    public void setBrand(String _brand){
        this.brand = _brand;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setReleaseYear(int release_year) {
        this.release_year = release_year;
    }

    public int getReleaseYear() {
        return this.release_year;
    }

    public void setPrice(double _price){
        this.price = _price;
    }

    public double getPrice(){
        return this.price;
    }

    public double getVehiclePrice(int _year){
        double current_price = 0;
        double rounded_price = 0;
        int newYear;
        int difYears = _year - this.getReleaseYear();
        if(_year <= this.getReleaseYear()){
            System.out.println("The year needs to be higher than the release year!(" + this.getReleaseYear() +")");
            System.out.println("Type a new year!");
            Scanner scanner = new Scanner(System.in);
            newYear = scanner.nextInt();
            return getVehiclePrice(newYear);
        }
        else{
            current_price = this.getPrice();
            do{
                current_price = current_price * 0.97;
                difYears--;
                rounded_price = Math.round(current_price * 100)/100.0;
            }while(difYears!=0);
        }
        return rounded_price;
    }

    @Override
    public String toString(){
        return "Vehicle Information:" +"\nModel: " + this.getModel() + "\nBrand:" + this.getBrand() + "\nRelease Year: " + this.getReleaseYear()
                + "\nRelease Price: " + this.getPrice();
    }
}