package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarScratch {
	public static void showAll (List<Car> lc) {
		for (Car c : lc) {
			System.out.println(c);
		}
		System.out.println("----------------------------------");
	}
	public static List<Car> getCarsByCriterion (Iterable<Car> in, CarCriterion crit) {
		List<Car> output = new ArrayList<>();
		for (Car c : in) {
			if (crit.test(c))
				output.add(c);
		}
		return output;
	}
	
	public static void main(String[] args) {
		List<Car> cars  = Arrays.asList(
				Car.withGasColorPassengers(6, "Red","Папа", "Мама", "Даша", "Гриша"),
				Car.withGasColorPassengers(3, "Orange", "Баина", "Алик"),
				Car.withGasColorPassengers(9, "Black", "Юля", "Бобрович"),
				Car.withGasColorPassengers(7, "Green","Рома", "Катя", "Аркадий", "Никита", "Кристина"),
				Car.withGasColorPassengers(6, "Red", "Руслан", "Мебель")
				);
		
		showAll(cars);
		showAll(getCarsByCriterion(cars, Car.getRedCarCriterion()));
		showAll(getCarsByCriterion(cars, new Car.GasLevelCarCriterion(7)));
	}
}

interface CarCriterion {
	boolean test (Car c);
}
class RedCarCriterion implements CarCriterion {
	@Override
	public boolean test(Car c) {
		return c.getColor().equals("Red");
	}
}
class GasLevelCarCriterion implements CarCriterion {
	int gasLevelThreshold;
	
	public GasLevelCarCriterion(int gasLevel) {
		super();
		this.gasLevelThreshold = gasLevel;
	}

	@Override
	public boolean test(Car c) {
		return c.getGasLevel()>= gasLevelThreshold;
	}
	
}