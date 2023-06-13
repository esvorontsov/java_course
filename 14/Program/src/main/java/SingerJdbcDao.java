import java.sql.*;
import java.util.*;

public class SingerJdbcDao extends AbstractSingerDao {

    public SingerJdbcDao(){}

    public SingerJdbcDao(List<Singer> singers){
        fillSingers(singers, false);
    }

    /**
     * Возвращает соединение с бд
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Music";
        return DriverManager.getConnection(url, "postgres", "vorontsov33");
    }

    /**
     * Возвращает словарь наименований
     * @param conn - соединение с бд
     * @param sql - sql запрос
     * @param idColumn - колонка идентификатора
     * @param nameColumn - колонка наименования
     * @return
     */
    private Map<Integer, String> getNames(Connection conn, String sql, String idColumn, String nameColumn){
        Map<Integer, String> names = new HashMap<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id  = resultSet.getInt(idColumn);
                String name = resultSet.getString(nameColumn);
                names.put(id, name);
            }
        } catch (SQLException e) {
            System.err.format("getNames:: sql: %s, SQL State: %s, message: %s", sql, e.getSQLState(), e.getMessage());
        }
        return names;
    }

    /**
     * Возвращает словарь наименований исполнителей
     * @param conn - соединение с бд
     * @return
     */
    private Map<Integer, String> getSingerNames(Connection conn){
        return getNames(conn, "select id, name from singers", "id", "name");
    }

    /**
     * Возвращает словарь наименований альбомов
     * @param conn - соединение с бд
     * @return
     */
    private Map<Integer, String> getAlbumNames(Connection conn){
        return getNames(conn, "select id, name from albums", "id", "name");
    }

    /**
     * Возвращает словарь альбомов
     * @param conn - соединение с бд
     * @return
     */
    private Map<Integer, List<Integer>> getSingerAlbumIds(Connection conn){
        Map<Integer, List<Integer>> result = new HashMap<>();

        String sql = "select id, singerId from albums";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id  = resultSet.getInt("id");
                Integer singerId = resultSet.getInt("singerId");

                if (!result.containsKey(singerId)){
                    result.put(singerId, new ArrayList<Integer>());
                }
                result.get(singerId).add(id);
            }
        } catch (SQLException e) {
            System.err.format("getSingerAlbumIds:: sql: %s, SQL State: %s, message: %s", sql, e.getSQLState(), e.getMessage());
        }
        return result;
    }

    /**
     * Возвращает словарь песен
     * @param conn - соединение с бд
     * @return
     */
    private Map<Integer, List<Song>> getAlbumSongs(Connection conn){
        Map<Integer, List<Song>> result = new HashMap<>();

        String sql = "select name, size, albumId from songs";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Long size = resultSet.getLong("size");
                Integer albumId = resultSet.getInt("albumId");

                if (!result.containsKey(albumId)){
                    result.put(albumId, new ArrayList<Song>());
                }
                result.get(albumId).add(new Song(name, size));
            }
        } catch (SQLException e) {
            System.err.format("getAlbumSongs:: SQL State: %s, message: %s", e.getSQLState(), e.getMessage());
        }
        return result;
    }

    @Override
    public List<Singer> findSingers() {
        List<Singer> result = new ArrayList<>();

        try (Connection conn = getConnection()) {

            Map<Integer, String> singerNames = getSingerNames(conn);
            Map<Integer, String> albumNames = getAlbumNames(conn);
            Map<Integer, List<Integer>> singerAlbumIds = getSingerAlbumIds(conn);
            Map<Integer, List<Song>> albumSongs = getAlbumSongs(conn);

            result.addAll(singerAlbumIds.entrySet().stream()
                .map(s -> new Singer(
                    singerNames.get(s.getKey()),
                    s.getValue().stream().map(a -> new Album(
                        albumNames.get(a),
                        albumSongs.get(a).stream().toList()
                    )).toList()
                )).toList());

        } catch (SQLException e) {
            System.err.format("findSingers:: SQL State: %s, message: %s", e.getSQLState(), e.getMessage());
        }
        return result;
    }

    @Override
    public void saveSingers(List<Singer> singers) {
        fillSingers(singers, true);
    }

    private void fillSingers(List<Singer> singers, boolean filtration) {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            // очищаем таблицы
            try (Statement statement = conn.createStatement()) {
                statement.execute(String.format("delete from %s", getSongsTblName(filtration)));
                statement.execute(String.format("delete from %s", getAlbumsTblName(filtration)));
                statement.execute(String.format("delete from %s", getSingersTblName(filtration)));

                // добавляем данные
                for (Singer s: singers) {
                    addSinger(conn, s, filtration);
                }
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                System.err.format("saveSingers:: SQL State: %s, message: %s", e.getSQLState(), e.getMessage());
            }
        } catch (SQLException e) {
            System.err.format("saveSingers:: SQL State: %s, message: %s", e.getSQLState(), e.getMessage());
        }
    }

    private void addSinger(Connection conn, Singer singer, boolean filtration) throws SQLException{
        String sql = String.format(
                "insert into %s (name) values (?)",
                getSingersTblName(filtration)
        );
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, singer.getName());
            ps.executeUpdate();

            try (ResultSet ids = ps.getGeneratedKeys()) {
                if (ids.next()) {
                    Integer singerId = ids.getInt(1);
                    for(Album a: singer.getAlbums()){
                        addAlbum(conn, a, singerId, filtration);
                    }
                } else {
                    throw new SQLException("addSinger:: not exists singerId");
                }
            }
        }
    }

    private void addAlbum(Connection conn, Album album, Integer singerId, boolean filtration) throws SQLException{
        String sql = String.format(
                "insert into %s (name, singerId) values (?, ?)",
                getAlbumsTblName(filtration)
        );
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, album.getName());
            ps.setInt(2, singerId);
            ps.executeUpdate();

            try (ResultSet ids = ps.getGeneratedKeys()) {
                if (ids.next()) {
                    Integer albumId = ids.getInt(1);
                    for (Song s: album.getSongs()){
                        addSong(conn, s, albumId, filtration);
                    }
                } else {
                    throw new SQLException("addAlbum:: not exists albumId");
                }
            }
        }
    }

    private void addSong(Connection conn, Song song, Integer albumId, boolean filtration) throws SQLException{
        String sql = String.format(
            "insert into %s (name, size, albumId) values (?, ?, ?)",
            getSongsTblName(filtration)
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, song.getName());
            ps.setLong(2, song.getSize());
            ps.setInt(3, albumId);
            ps.executeUpdate();
        }
    }

    private String getSingersTblName(boolean filtration){
        return "singers" + (filtration ? "_filtered" : "");
    }

    private String getAlbumsTblName(boolean filtration){
        return "albums" + (filtration ? "_filtered" : "");
    }

    private String getSongsTblName(boolean filtration){
        return "songs" + (filtration ? "_filtered" : "");
    }
}
