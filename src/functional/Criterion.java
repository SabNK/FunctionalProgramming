package functional;

public interface Criterion <E> {
	boolean test (E e);
	
	static <E> Criterion <E> negate (Criterion<E> crit) {
		return c -> !crit.test(c);
	}
	
	static <E> Criterion <E> and (Criterion<E> crit1, Criterion<E> crit2) {
		return c -> crit1.test(c)&&crit2.test(c);
	}
	
	static <E> Criterion <E> or (Criterion<E> crit1, Criterion<E> crit2) {
		return c -> crit1.test(c)||crit2.test(c);
	}

}
