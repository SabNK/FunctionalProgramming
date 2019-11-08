package warburton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Album implements Performance {
	private String name;
	private List<Track> tracks;
	private List<Artist> musicians;

	public Album(String name, List<Track> tracks, List<Artist> musicians) {
		super();
		this.name = name;
		this.tracks = new ArrayList<>(tracks);
		this.musicians = new ArrayList<>(musicians);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stream<Track> getTracks() {
		return tracks.stream();
	}
	public List<Track> getTrackList() {
		return tracks;
	}

	
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public Stream<Artist> getMusicians() {
		return musicians.stream();
	}

	public void setMusicians(List<Artist> musicians) {
		this.musicians = musicians;
	}

	@Override
	public String toString() {
		return "Album [name=" + name + ", tracks=" + tracks + ", musicians=" + musicians + "]";
	}
	
	
}
