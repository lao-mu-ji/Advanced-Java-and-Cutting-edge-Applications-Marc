//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

public class Car {
	
	private CarModel model;
	private String plate;
	
	public Car(CarModel model, String plate) {
		this.model = model;
		this.plate = plate;
	}

	public String toString() {
		return plate + "\n" + model.getBrand() + "\n" + model;
	}
	
	public static void main(String[] args) {
		Car yaris = new Car(CarBrand.getCarModel(CarBrand.Brand.Toyota, "Yaris"),"A-123");
		System.out.println(yaris);
	}
}
