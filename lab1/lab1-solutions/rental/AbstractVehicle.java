//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.rental;

import java.util.*;

public abstract class AbstractVehicle {
	
	private long id;
	private AbstractEnergy energy;
	private int seats;
	
	public AbstractVehicle(AbstractEnergy energy, int seats) {
		this.id = (new Date()).getTime();
		this.energy = energy;
		this.seats = seats;
	}
	
	public long getId() {
		return id;
	}
	
	public AbstractEnergy getEnergy() {
		return energy;
	}
	
	public int getSeats() {
		return seats;
	}
	
	@Override
	public String toString() {
		return "Vehicle ID: " + id + "\n" + energy + "\n" + seats + " seats";
	}

}
