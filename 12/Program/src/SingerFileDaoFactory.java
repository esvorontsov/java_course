public class SingerFileDaoFactory implements AbstractSingerDaoFactory{

    private static final SingerFileDaoFactory instance = new SingerFileDaoFactory();

    private SingerFileDaoFactory(){

    }

    public static SingerFileDaoFactory getInstance(){
        return instance;
    }

    @Override
    public SingerDAO createSingerDao() {
        return new SingerFileDao();
    }
}
