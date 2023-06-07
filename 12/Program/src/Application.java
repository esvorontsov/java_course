import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args){
        AbstractSingerDaoFactory factory = SingerFileDaoFactory.getInstance();
        SingerDAO dao = factory.createSingerDao();
        List<Singer> singers = dao.findSingers();
        SingerService service = new SingerService(dao);
        List<Singer> l1 = service.filterByMinAlbumsCount(2);
        dao.saveSingers(l1);
        List<Singer> l2 = service.filterBySongName("song8");
        dao.saveSingers(l2);
    }
}
