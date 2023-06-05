public class SingerJdbcDaoFactory implements AbstractSingerDaoFactory{

    private static final SingerJdbcDaoFactory instance = new SingerJdbcDaoFactory();

    private SingerJdbcDaoFactory(){

    }

    public static SingerJdbcDaoFactory getInstance(){
        return instance;
    }

    @Override
    public SingerDAO createSingerDao() {
        return new SingerJdbcDao();
    }
}
