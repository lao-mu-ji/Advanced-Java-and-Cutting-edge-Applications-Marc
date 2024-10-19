//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

public class CarModel extends AbstractCar {
	
	private CarBrand brand;
	
	public CarModel(AbstractEnergy energy, Type type, int seats, Gear gear, int door, CarBrand brand) {
		super(energy,type,seats,gear,door);
		this.brand = brand;
	}
	
	public CarBrand getBrand() {
		return this.brand;
	}
}
