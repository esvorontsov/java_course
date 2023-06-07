import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SingerFileDaoFactory implements AbstractSingerDaoFactory{

    private static final SingerFileDaoFactory instance = new SingerFileDaoFactory();

    private SingerFileDaoFactory(){

    }

    public static SingerFileDaoFactory getInstance(){
        return instance;
    }

    @Override
    public SingerDAO createSingerDao() {
        String rootPath = new File("").getAbsolutePath().concat("\\music");
        String resultPath = new File("").getAbsolutePath().concat("\\result");
        return new SingerFileDao(Paths.get(rootPath), Paths.get(resultPath));
    }
}
