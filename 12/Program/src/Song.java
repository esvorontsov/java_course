public class Song {
    private String name;
    private int size;

    public Song(String name, int size){
        this.name = name;
        this.size = size;
    }

    public Song(Song song){
        this.name = song.getName();
        this.size = song.getSize();
    }

    public String getName(){
        return this.name;
    }

    public int getSize(){
        return this.size;
    }

}
