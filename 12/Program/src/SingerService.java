import java.util.List;

public class SingerService {
    private SingerDAO singerDao;

    public SingerService(SingerDAO singerDao){
        this.singerDao = singerDao;
    }

    public List<Singer> filterBySongName(String songName) {
        return singerDao.getSingers().stream()
                .filter(s -> s.getAlbums().stream()
                        .anyMatch(a -> a.getSongs().stream()
                                .anyMatch(song -> song.getName().toLowerCase().contains(songName.toLowerCase()))))
                .toList();
    }

    public List<Singer> filterByMinAlbumsCount(int minAlbumsCount) {
        return singerDao.getSingers().stream()
                .filter(s -> s.getAlbums().size() >= minAlbumsCount)
                .toList();
    }
}
