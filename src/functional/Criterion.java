package functional;

public interface Criterion <E> {
	boolean test (E e);
	
	default Criterion <E> negate () {
		return c -> !this.test(c);
	}
	
	default Criterion <E> and (Criterion<E> other) {
		return c -> this.test(c) && other.test(c);
	}
	
	default Criterion <E> or (Criterion<E> other) {
		return c -> this.test(c)||other.test(c);
	}

}
