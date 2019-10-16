package functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car {
	private final int gasLevel;
	private final String color;
	private final List<String> passengers;
	private final List<String> trunkContents;
	
	private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
		super();
		this.gasLevel = gasLevel;
		this.color = color;
		this.passengers = passengers;
		this.trunkContents = trunkContents;
	}
	
	public static Car withGasColorPassengers(int gas, String color, String ... passengers) {
		List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
		Car self = new Car(gas, color, p, null);
		return self;
	}
	
	public static Car withGasColorPassengersAndTrunk(int gas, String color, String ... passengers) {
		List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
		Car self = new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));
		return self;
	}

	public int getGasLevel() {
		return gasLevel;
	}

	public String getColor() {
		return color;
	}

	public List<String> getPassengers() {
		return passengers;
	}

	public List<String> getTrunkContents() {
		return trunkContents;
	}

	@Override
	public String toString() {
		return "Car [gasLevel=" + gasLevel + ", color=" + color + ", passengers=" + passengers + (trunkContents != null ?", trunkContents="
				+ trunkContents: " no trunck") + "]";
	}
		
	public static CarCriterion getRedCarCriterion() {
		return RED_CAR_CRITERION;
	}

	public static final CarCriterion RED_CAR_CRITERION = c -> c.color.equals("Red");
	
	
	public static CarCriterion getGasLevelCarCriterion (int threshold) {
		return new GasLevelCarCriterion (threshold);
	}
	
	private static class GasLevelCarCriterion implements CarCriterion {
		int gasLevelThreshold;
		
		public GasLevelCarCriterion(int gasLevel) {
			super();
			this.gasLevelThreshold = gasLevel;
		}

		@Override
		public boolean test(Car c) {
			return c.gasLevel >= gasLevelThreshold;
		}
	}
	
	public static Comparator<Car> getGasComparator() {
		return GAS_COMPARATOR;
	}

	private static final Comparator<Car> GAS_COMPARATOR = (arg0, arg1) -> arg0.gasLevel - arg1.gasLevel;
	
}
