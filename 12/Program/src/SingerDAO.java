import java.util.List;

public interface SingerDAO {
    List<Singer> findSingers();

    void saveSingers(List<Singer> singers);
}
