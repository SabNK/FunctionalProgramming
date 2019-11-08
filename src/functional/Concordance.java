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
	private static final Comparator<Map.Entry<String,Long>> valueOrder = Map.Entry.comparingByValue();
	private static final Comparator<Map.Entry<String,Long>> reversedValue = valueOrder.reversed();
	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("pg42671.txt"))
			.flatMap(l -> Pattern.compile("\\W+").splitAsStream(l))
			.filter(w -> w.length()>0)
			.map(w -> w.toLowerCase())
			.collect(groupingBy(Function.identity(), Collectors.counting()))
			.entrySet().stream()
			.sorted(reversedValue)
			.limit(30)
			.forEach(e -> System.out.printf("%10s : %5d\n", e.getKey(), e.getValue()));
	}
}
