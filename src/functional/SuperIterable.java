package functional;

import java.util.Arrays;
import java.util.Iterator;


public class SuperIterable<E> implements Iterable<E> {
	private Iterable<E> self;

	public SuperIterable(Iterable self) {
		this.self = self;
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
	}	
}
