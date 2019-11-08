package warburton;

import java.util.stream.*;
import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static java.util.Arrays.asList;

public class Test {
	public static void main(String[] args) {
		Artist ringo = new Artist ("Ringo Starr", "London");
		Artist john = new Artist ("John Lennon", "London");
		Artist paul = new Artist ("Paul McCartney", "London");
		Artist george = new Artist ("George Harrison", "London");
		Artist beatles = new Artist ("The Beatles", asList(paul, john, george, ringo), "Liverpoul");
		Artist rolling = new Artist ("The Rolling Stones",  "Maiami");
		List <Artist> artists = asList(
				ringo,
				new Artist ("Alla Pugacheva", "Moscow"),
				new Artist ("Pink Floyd", "London"),
				beatles
		);
		List<Track> tracks = asList(new Track("Bakai", 524),
				new Track("Violets for Your Furs", 378),				
				new Track("Time Was", 451));
		List<Track> tracks1 = asList(new Track("Ququ", 301),
				new Track("Bring me back", 412),				
				new Track("Buy buy my Love", 497),
				new Track("Yellow Submarine", 45));
		Album letitbe = new Album("Let It Be", tracks, asList(beatles, rolling));
		Album whiteAlbum = new Album("White Album", tracks1, asList(beatles, rolling));
		int count = 0;
		for (Artist artist : artists) {
			if (artist.isFrom("London")) {
				count++;
			}
		}
		System.out.println("Total from London: " + count);
		System.out.println("=======================================");
		count = 0;
		Iterator<Artist> iterator = artists.iterator();
		while (iterator.hasNext()) {
			Artist artist = iterator.next();
			if (artist.isFrom("London")) {
				count ++;
			}
		}
		System.out.println("Total from London: " + count);
		System.out.println("=======================================");
		//long countLong
		Stream str = artists.stream()
				.filter(a -> {
					System.out.println(a.getName());
					return a.isFrom("London");});
		long countLong = str.count();
		System.out.println("Total from London: " + countLong);
		System.out.println("=======================================");
		List<Integer> collected = Stream.of("2", "3", "4")
				.map(e -> Integer.valueOf(e.toUpperCase()))
				.collect(toList());
		System.out.println(collected);
		System.out.println("=======================================");
		List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
				.flatMap(numbers -> numbers.stream())
				.collect(toList());
		System.out.println(together);
		System.out.println("=======================================");
		
		Track shortestTrack = tracks.stream()
				.min(Comparator.comparing(track -> track.getLength()))
				.get();
		System.out.println(shortestTrack);
		System.out.println("=======================================");
		int sum = Stream.of(1, 2, 3, 4, 5)
				.reduce(0, (acc, element) -> acc + element);
		System.out.println(sum);
		System.out.println("=======================================");
		System.out.println(getAlbumOrigins(letitbe));
		Runnable helloWorld = () -> System.out.println("hello world");
		helloWorld.run();
		//Q q = Q.check(x -> x>5);
		System.out.println(findLongTracks(asList(letitbe, whiteAlbum)));
		Stream num = Stream.of(1,2,3);
		System.out.println(addUp(num));
		System.out.println("=======================================");
		System.out.println(getSmallAlbums(asList(letitbe, whiteAlbum)));
		System.out.println("=======================================");
		System.out.println(getNamesAndOrigin(beatles));
		System.out.println(
		artists	.stream()
				.flatMap(a -> a.getMembers())
				.count()
				);
		System.out.println(
				artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, Long::sum)
                .intValue()
						);
		/*
		 * System.out.println( "Abcd".chars() .filter(c -> Character.isUpperCase(new
		 * Character(c))) .count();
		 */
		System.out.println(maximumLetters(asList("Xthyf", "Чернеет Парус")).get());		
	}
	
	
	static Optional<String> maximumLetters (List<String> strings) {
		return strings.stream()
				.max(Comparator.comparing(s -> s.length()));
	}
	
	
	public static Q testQ (Q q) {
		return q;
	}
	
	public static Set<String> getAlbumOrigins (Album album) {
		return album.getAllMusicians()
					.filter(g -> g.getName().startsWith("The"))
					.map(a -> a.getOrigin())
					.collect(Collectors.toSet());
		}
	
	static List<String> getNamesAndOrigin (Artist artist) {
		return artist.getMembers()
				.flatMap(a -> Stream.of(a.getName(), a.getOrigin()))
				.collect(Collectors.toList());
	}
	
	static int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (n,a) -> n+a);
				
	}
	static List<Album> getSmallAlbums (List<Album> albums) {
		return 
		albums	.stream()
				.filter(a -> a.getTracks().count() < 4)
				.collect(Collectors.toList())
		;
	}
	public static Set<String> findLongTracks(List<Album> albums) {
		return 
		albums.stream()
			.flatMap(album -> album.getTracks())			
			.filter (track -> track.getLength() > 60)
			.map(t -> t.getName())
			.collect(Collectors.toSet());
									
			
		/*for(Album album : albums) {
			for (Track track : album.getTrackList()) {
				if (track.getLength() > 60) {
					String name = track.getName();
					trackNames.add(name);
				}
			}
		}*/
		//return trackNames;	
	}
	interface Q {
		boolean check(Predicate<Integer> predicate);
		boolean check(IntPred predicate);
	}
}

interface IntPred {
boolean test(Integer value);
}

