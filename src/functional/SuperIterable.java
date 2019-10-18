package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;


public class SuperIterable<E> implements Iterable<E> {
	private Iterable<E> self;

	public SuperIterable(Iterable self) {
		this.self = self;
	}

	public SuperIterable<E>  filter (Predicate<E> pred) {
		List<E> results = new ArrayList<>();
		for (E e : self)
			if (pred.test(e)) {
				results.add(e);
			}
		return new SuperIterable<>(results);
	}
	
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
		for (String s: upperCase) {
			System.out.println("> " + s);
		}
		System.out.println("----------------------------------");
		for (String s: strings) {
			System.out.println("> " + s);
		}
	}	
}
