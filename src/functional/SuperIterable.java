package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class SuperIterable<E> implements Iterable<E> {
	private Iterable<E> self;

	public SuperIterable(Iterable self) {
		this.self = self;
	}

	public <F> SuperIterable<F> map (Function<E,F> op) {
		List<F> results = new ArrayList<>();
		self.forEach(e -> results.add(op.apply(e)));
		return new SuperIterable<>(results);
	}
	
	public SuperIterable<E>  filter (Predicate<E> pred) {
		List<E> results = new ArrayList<>();
		self.forEach(e -> {
			if (pred.test(e)) results.add(e);
		});
		/*
		 * for (E e : self) if (pred.test(e)) { results.add(e); }
		 */
		return new SuperIterable<>(results);
	}
	
	/*
	 * public void forEvery (Consumer<E> cons) { for (E e : self) cons.accept(e); }
	 */
	
	@Override
	public Iterator<E> iterator() {
		return self.iterator();
	}
	
	public static void main(String[] args) {
		SuperIterable<String> strings = new SuperIterable<>(Arrays.asList("LightCoral", "pink",
				"orange", "Gold", "plum", "Blue", "limegreen"));
		for (String s: strings) {
			System.out.println(s);
		}
		System.out.println("----------------------------------");
		SuperIterable<String> upperCase = strings.filter(s-> Character.isUpperCase(s.charAt(0)));
		upperCase.forEach(s -> System.out.println("> " + s));
		
		System.out.println("----------------------------------");
		strings.forEach(s -> System.out.println(s));
		System.out.println("----------------------------------");
		strings
			.filter(x -> Character.isUpperCase(x.charAt(0)))
			.map(x -> x.toUpperCase())
			.forEach(x -> System.out.println(x));
		System.out.println("----------------------------------");
		SuperIterable<Car> carIter = new SuperIterable<>(
				Arrays.asList(
						Car.withGasColorPassengers(6, "Red","Папа", "Мама", "Даша", "Гриша"),
						Car.withGasColorPassengers(3, "Orange", "Баина", "Алик"),
						Car.withGasColorPassengers(9, "Black", "Юля", "Бобрович"),
						Car.withGasColorPassengers(7, "Green","Рома", "Катя", "Аркадий", "Никита", "Кристина"),
						Car.withGasColorPassengers(6, "Red", "Руслан", "Мебель")
						)
				);
		carIter
			.filter(c -> c.getGasLevel() > 6)
			.map(c -> c.getPassengers().get(0) + " is driving a " + c.getColor() + " car with lots of fuel")
			.forEach(c -> System.out.println("> " + c));
	}	
}
