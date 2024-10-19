//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

public class GasEnergy extends AbstractEnergy {
	
	public enum Fuel { GASOLINE, DIESEL };
	
	private Fuel fuel;
	
	public GasEnergy(double consumption, Fuel fuel) {
		super(consumption);
		this.fuel = fuel;
	}
	
	public GasEnergy(double consumption) {
		this(consumption,Fuel.GASOLINE);
	}
	
	@Override
	public String toString() {
		return "(" + fuel + ") " + getConsumption() + "L/100Km";
	}
}
