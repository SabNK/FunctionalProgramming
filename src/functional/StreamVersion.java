package functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVersion {
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("LightCoral", "pink",
				"orange", "Gold", "plum", "Blue", "limegreen");
		
		strings.stream().forEach(s -> System.out.println("> " + s));
		
		System.out.println("----------------------------------");
		Stream<String> upperCase = strings.stream().filter(s-> Character.isUpperCase(s.charAt(0)));
		upperCase.forEach(s -> System.out.println("> " + s));
		
		
		System.out.println("----------------------------------"); 
		strings.stream().forEach(s -> System.out.println(s));
		System.out.println("----------------------------------"); 
		strings.stream()
			.filter(x -> Character.isUpperCase(x.charAt(0)))
			.map(x -> x.toUpperCase()) 
			.forEach(x -> System.out.println(x));
		  
		System.out.println("----------------------------------"); 
		List<Car> carIter = Arrays.asList( 
				Car.withGasColorPassengers(6, "Red","Папа", "Мама", "Даша", "Гриша"), 
				Car.withGasColorPassengers(3,  "Orange", "Баина", "Алик"), 
				Car.withGasColorPassengers(9, "Black", "Юля", "Бобрович"), 
				Car.withGasColorPassengers(7, "Green","Рома", "Катя", "Аркадий", "Никита", "Кристина"),
				Car.withGasColorPassengers(6, "Red", "Руслан", "Мебель")
				);
		carIter.stream()
			.filter(c -> c.getGasLevel() > 6) 
			.map(c -> c.getPassengers()
					.get(0) + " is driving a " + c.getColor() + " car with lots of fuel") 
					.forEach(c -> System.out.println("> " + c));
		System.out.println("----------------------------------"); 
		carIter.stream()
			.map(c -> c.addGas(7))
			.forEach(c -> System.out.println(">> " + c));
		System.out.println("----------------------------------"); 
		carIter.stream()
			.forEach(c -> System.out.println(">> " + c));
		System.out.println("----------------------------------"); 
		carIter.stream()
			.filter(c -> c.getColor()== "Red") 
			.flatMap(c -> c.getPassengers().stream()) 
			.map(d -> d.toUpperCase()) 
			.forEach(d -> System.out.println(">> " + d));
		 		 
		}	
}
