import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;
    private final ArrayList<Song> songs = new ArrayList<>();

    public Album(String name, List<Song> songs){
        this.name = name;
        this.songs.addAll(songs.stream().map(s -> new Song(s)).toList());
    }

    public Album(Album album){
        this.name = album.getName();
        this.songs.addAll(album.getSongs().stream().map(s -> new Song(s)).toList());
    }

    public String getName(){
        return this.name;
    }

    public List<Song> getSongs(){
        return this.songs.stream().map(s -> new Song(s)).toList();
    }
}
