//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

import java.util.*;

import tiei.ajp.rental.AbstractCar.Gear;
import tiei.ajp.rental.AbstractCar.Type;

public class CarBrand {
	
	public static enum Brand { Peugeot, Renault, Toyota };
	
	private Brand brand;
	private String model;
	
	private static EnumMap<Brand,Map<String,CarModel>> models = new EnumMap<>(CarBrand.Brand.class);
	
	static {
		models.put(Brand.Toyota, new HashMap<>());
		models.get(Brand.Toyota).put("Yaris", new CarModel(new ElectricEnergy(500),Type.SUBCOMPACT,4,Gear.AUTOMATIC,5,new CarBrand(Brand.Toyota,"Yaris")));
	}
	
	public static CarModel getCarModel(Brand brand, String model) {
		return models.get(brand).get(model);
	}
	
	private CarBrand(Brand brand, String model) {
		this.brand = brand;
		this.model = model;
	}
	
	public Brand name() {
		return brand;
	}
	
	public String model() {
		return model;
	}
	
	public String toString() {
		return brand + " " + model;
	}

}
