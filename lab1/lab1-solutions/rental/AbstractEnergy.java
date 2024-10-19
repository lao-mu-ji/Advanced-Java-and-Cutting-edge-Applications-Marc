//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

public abstract class AbstractEnergy {
	
	private double consumption;
	
	public AbstractEnergy(double consumption) {
		this.consumption = consumption;
	}
	
	public double getConsumption() {
		return consumption;
	}

}
