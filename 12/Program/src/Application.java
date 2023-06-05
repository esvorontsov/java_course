import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args){
        List<Singer> singers = Arrays.asList(
                new Singer("singer1", Arrays.asList(
                        new Album("album11", Arrays.asList(
                                new Song("song111", 111),
                                new Song("song112", 112),
                                new Song("song113", 113),
                                new Song("song8", 8)
                        ))
                )),
                new Singer("singer2", Arrays.asList(
                        new Album("album21", Arrays.asList(
                                new Song("song211", 211),
                                new Song("song212", 212),
                                new Song("song213", 213)
                        )),
                        new Album("album22", Arrays.asList(
                                new Song("song221", 221),
                                new Song("song222", 222),
                                new Song("song223", 223)
                        ))
                )),
                new Singer("singer3", Arrays.asList(
                        new Album("album31", Arrays.asList(
                                new Song("song311", 311),
                                new Song("song312", 312),
                                new Song("song313", 313)
                        )),
                        new Album("album32", Arrays.asList(
                                new Song("song321", 321),
                                new Song("song322", 322),
                                new Song("song323", 323)
                        )),
                        new Album("album33", Arrays.asList(
                                new Song("song331", 331),
                                new Song("song332", 332),
                                new Song("song333", 333),
                                new Song("song334", 334)
                        ))
                )),
                new Singer("singer4", Arrays.asList(
                        new Album("album41", Arrays.asList(
                                new Song("song888", 888)
                        ))
                ))
        );

        AbstractSingerDaoFactory factory = SingerFileDaoFactory.getInstance();
        SingerDAO dao = factory.createSingerDao();
        dao.setSingers(singers);
        SingerService service = new SingerService(dao);
        List<Singer> l1 = service.filterByMinAlbumsCount(2);
        List<Singer> l2 = service.filterBySongName("song8");
    }
}
