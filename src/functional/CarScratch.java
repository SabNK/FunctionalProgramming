package functional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class CarScratch {
	public static <E> ToIntFunction<E> compareWithThis (E target, Comparator<E> comp){
		return x -> comp.compare(target, x);		
	}
	
	public static <E> Predicate<E> isBiggerThanThis (ToIntFunction<E> t) {
		return x -> t.applyAsInt(x)<0;
	}
	
	public static <E> void showAll (List<E> lc) {
		for (E c : lc) {
			System.out.println(c);
		}
		System.out.println("----------------------------------");
	}
	public static <E> List<E> getByCriterion (Iterable<E> in, Predicate<E> crit) {
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
		
		/*
		 * cars.sort(Car.getGasComparator()); showAll(cars);
		 * showAll(getByCriterion(cars, c -> c.getPassengers().size() == 2));
		 * showAll(getByCriterion(cars, Car.getFourPassengerCriterion()));
		 * 
		 * List<String> colors = Arrays.asList("желтый", "Красный","синий",
		 * "Фиолетовый", "Оранжевый"); showAll(getByCriterion(colors, st -> st.length()
		 * > 7)); showAll(getByCriterion(colors, st ->
		 * Character.isUpperCase(st.charAt(0))));
		 * 
		 * LocalDate today = LocalDate.now(); List<LocalDate> dates =
		 * Arrays.asList(today, today.plusDays(1), today.plusDays(7),
		 * today.minusDays(2)); showAll(getByCriterion(dates, ld -> ld.isAfter(today)));
		 */
		showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(7)));
		showAll(getByCriterion(cars, Car.getColorCriterion("Red","Green","Orange")));
		Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);
		Predicate<Car> blackAndOrange = Car.getColorCriterion("Black","Orange");
		showAll(getByCriterion(cars, level7.and(blackAndOrange)));
		
		Car bert = Car.withGasColorPassengers(5, "Blue");
		
		ToIntFunction <Car> compareWithBert = compareWithThis(bert, Car.getGasComparator());
		Predicate<Car> compWithBert = isBiggerThanThis(compareWithBert);
		for (Car c: cars) {
			System.out.println("comparing " + c + " with bert gives " + compareWithBert.applyAsInt(c));
			System.out.println("comparing " + compWithBert.test(c));
		}
		showAll(getByCriterion(cars, compWithBert));
	}
}

/*
 * @FunctionalInterface interface Criterion <E> { boolean test (E c); }
 */
