//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

public abstract class AbstractMotorbike extends AbstractVehicle {
	
	public enum Type { STANDARD, CRUISER, TOURING, SPORT, OFFROAD };
	
	private Type type;
	private int cc;
	
	public AbstractMotorbike(AbstractEnergy energy, Type type, int cc) {
		super(energy,2);
		this.type = type;
		this.cc = cc;
	}
	
	public Type getType() {
		return type;
	}
	
	public int getCc() {
		return cc;
	}

}
