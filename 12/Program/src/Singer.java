import java.util.ArrayList;
import java.util.List;

public class Singer {
    private String name;
    private final ArrayList<Album> albums = new ArrayList<>();

    public Singer(String name, List<Album> albums){
        this.name = name;
        this.albums.addAll(albums.stream().map(a -> new Album(a)).toList());
    }

    public Singer(Singer singer){
        this.name = singer.getName();
        this.albums.addAll(singer.getAlbums().stream().map(a -> new Album(a)).toList());
    }

    public String getName(){
        return this.name;
    }

    public List<Album> getAlbums(){
        return this.albums.stream().map(a -> new Album(a)).toList();
    }
}
