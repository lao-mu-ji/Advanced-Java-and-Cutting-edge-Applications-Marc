//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

public class ElectricEnergy extends AbstractEnergy {
	
	public ElectricEnergy(double consumption) {
		super(consumption);
	}
	
	@Override
	public String toString() {
		return "(electric) autonomy: " + getConsumption() + " Km";
	}
}
