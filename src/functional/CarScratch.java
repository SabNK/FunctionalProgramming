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
	public static List<Car> getColoredCars (Iterable<Car> in, String color) {
		List<Car> output = new ArrayList<>();
		for (Car c : in) {
			if (c.getColor().equals(color))
				output.add(c);
		}
		return output;
	}
	public static List<Car> getCarsByGasLevel (Iterable<Car> in, int gasLevel) {
		List<Car> output = new ArrayList<>();
		for (Car c : in) {
			if (c.getGasLevel() >= gasLevel)
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
		showAll(getColoredCars(cars, "Black"));		
	}	
}
