import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingerFileDao extends AbstractSingerDao {

    private Path root;
    private Path result;

    public SingerFileDao(Path root, Path result){
        this.root = root;
        this.result = result;
    }

    @Override
    public List<Singer> findSingers() {
        List<Singer> singers = new ArrayList<>();

        try {
            // в корне находятся каталоги исполнителей
            List<Path> singerPaths  = Files.find(root, 1,
                    (p, basicFileAttributes) -> { return Files.isDirectory(p); }
            ).collect(Collectors.toList());

            //в каталоге исполнителя находятся каталоги альбомов
            for (Path singerPath: singerPaths) {
                List<Path> albumPaths  = Files.find(singerPath, 1,
                        (p, basicFileAttributes) -> { return Files.isDirectory(p); }
                ).collect(Collectors.toList());

                List<Album> albums = new ArrayList<>();

                // в каталоге альбома находятся файлы песен
                for (Path albumPath: albumPaths) {
                    List<Path> songPaths = Files.find(albumPath, 1,
                            (p, basicFileAttributes) -> {
                                return !Files.isDirectory(p) && p.getFileName().toString().toLowerCase().endsWith(".mp3");
                    }).collect(Collectors.toList());

                    List<Song> songs = new ArrayList<>();
                    for (Path songPath: songPaths){
                        songs.add(new Song(songPath.getFileName().toString().replace(".mp3", ""), Files.size(songPath)));
                    }

                    if (songs.size() > 0){
                        albums.add(new Album(albumPath.getFileName().toString(), songs));
                    }
                }

                if (albums.size() > 0){
                    singers.add(new Singer(singerPath.getFileName().toString(), albums));
                }
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return singers;
    }

    @Override
    public void saveSingers(List<Singer> singers) {
        // пишем в файл
        try {
            Path path = Paths.get(result.toString(), new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date()) + ".txt");
            Files.write(path, singersToLines(singers), Charset.forName("UTF-8"), StandardOpenOption.CREATE);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
