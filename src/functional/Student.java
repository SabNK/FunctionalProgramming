package functional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Student {
	
	private static final NavigableMap<Integer, String> gradeLetters = new TreeMap<>();
	
	static {
		gradeLetters.put(90,  "A");
		gradeLetters.put(80,  "B");
		gradeLetters.put(70,  "C");
		gradeLetters.put(60,  "D");
		gradeLetters.put(50,  "E");
		gradeLetters.put(0,  "F");		
	}
	
	private String name;
	private int score;
	
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	
	public String getLetterGrade() {
		return gradeLetters.floorEntry(score).getValue();
	}
	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	@Override
	public String toString () {
		return name + ", " + score + "%, grade is " + getLetterGrade();
	}
	
	public static void main(String[] args) {
		List <Student> school = Arrays.asList(
				new Student("Рома", 71),
				new Student("Андрей", 38),
				new Student("Баина", 97),
				new Student("Юля", 100),
				new Student("Максим", 56),
				new Student("Руслан", 28),
				new Student("Слава", 65),
				new Student("Илья", 79),
				new Student("1", 93),
				new Student("2", 87),
				new Student("3", 91),
				new Student("Д-р Махмуд", 88),
				new Student("Ender", 91),
				new Student("Hyrum", 72),
				new Student("Locke", 91),
				new Student("Bonzo", 57)
				);
		school.forEach(s -> System.out.println(s));
		
		Map<String, List<Student>> table = school.stream()
			.collect(Collectors.groupingBy(s -> s.getLetterGrade()));
		System.out.println(table);
		Map<String, Long> table2 = school.stream()
				.collect(Collectors.groupingBy(s -> s.getLetterGrade(), Collectors.counting()));
			System.out.println(table2);
		table2.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.forEach(e -> System.out.println(e.getValue() + " students got grade " + e.getKey()));
		
		Map<String, List<String>> table3 = school.stream()
		.collect(Collectors.groupingBy(s -> s.getLetterGrade(), 
				 						Collectors.mapping(s -> s.getName(), Collectors.toList())));
		System.out.println(table3);
		table3.entrySet().stream()
			.forEach(e -> System.out.println("Students " + e.getValue().stream().collect(Collectors.joining(", ")) + " achieved grade " + e.getKey()));
	
	}
	
	

}
