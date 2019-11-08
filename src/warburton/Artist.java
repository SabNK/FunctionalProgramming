package warburton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Artist {
	private String name;
	private List<Artist> members;
	private String origin;
	
	public Artist(String name, List<Artist> members, String origin) {
		super();
		this.name = name;
		this.members = members;
		this.origin = origin;
	}
			
	public Artist(String name) {
		super();
		this.name = name;
	}

	public Artist(String name, String nationality) {
        this(name, Collections.emptyList(), nationality);
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Stream<Artist> getMembers() {
        return members.stream();
    }
			
	public void setMembers(List<Artist> members) {
		this.members = members;
	}
	
	public String getOrigin() {
		return origin;
	}
	public boolean isFrom(String origin) {
		return this.origin.equals(origin);
	}
	
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	@Override
    public String toString() {
        return getName();
    }

    public Artist copy() {
        List<Artist> members = getMembers().map(Artist::copy).collect(Collectors.toList());
        return new Artist(name, members, origin);
    }
	
	
}
