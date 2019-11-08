package functional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;

public class Concordance {
	private static final Comparator<Map.Entry<String,Long>> VALUE_ORDER = Map.Entry.comparingByValue();
	private static final Comparator<Map.Entry<String,Long>> REVERSED_VALUE = VALUE_ORDER.reversed();
	private static final Pattern WORD_BREAK = Pattern.compile("\\W+");
	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("pg42671.txt"))
			.flatMap(WORD_BREAK::splitAsStream)
			.filter(w -> w.length()>0)
			.map(String::toLowerCase)
			.collect(groupingBy(Function.identity(), Collectors.counting()))
			.entrySet().stream()
			.sorted(REVERSED_VALUE)
			.limit(30)
			.map(e -> String.format("%10s : %5d", e.getKey(), e.getValue()))
			.forEach(System.out::println);
	}
}
