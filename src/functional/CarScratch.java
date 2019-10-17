package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarScratch {
	public static <E> void showAll (List<E> lc) {
		for (E c : lc) {
			System.out.println(c);
		}
		System.out.println("----------------------------------");
	}
	public static <E> List<E> getByCriterion (Iterable<E> in, Criterion<E> crit) {
		List<E> output = new ArrayList<>();
		for (E c : in) {
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
		showAll(getByCriterion(cars, Car.getRedCarCriterion()));
		showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(7)));
		
		cars.sort(Car.getGasComparator());
		showAll(cars);
		showAll(getByCriterion(cars, c -> c.getPassengers().size() == 2));
		showAll(getByCriterion(cars, Car.getFourPassengerCriterion()));
	}
}

@FunctionalInterface
interface Criterion <E> {
	boolean test (E c);	
}
