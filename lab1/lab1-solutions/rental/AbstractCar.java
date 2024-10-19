//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

public abstract class AbstractCar extends AbstractVehicle {
	
	public enum Type { MINICOMPACT, SUBCOMPACT, COMPACT, MIDSIZE, LARGE, TWOSEATER, MINIVAN, SMALLSUV, SUV };
	
	public enum Gear { AUTOMATIC, MANUAL }
	
	private Type type;
	private Gear gear;
	private int door;
	
	public AbstractCar(AbstractEnergy energy, Type type, int seats, Gear gear, int door) {
		super(energy, seats);
		this.type = type;
		this.gear = gear;
		this.door = door;
	}
	
	public AbstractCar(AbstractEnergy energy, Type type, int seats, int door) {
		this(energy, type, seats, Gear.AUTOMATIC, door);
	}
	
	public Type getType() {
		return type;
	}
	
	public Gear getGear() {
		return gear;
	}
	
	public int getDoor() {
		return door;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + gear + "\n" + door + " door";
	}
}
