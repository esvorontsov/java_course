package ru.isys.task16.dao;

import ru.isys.task16.entities.Singer;

import java.util.ArrayList;
import java.util.List;

public class AbstractSingerDao implements SingerDAO {
    @Override
    public List<Singer> findSingers() {
        return null;
    }

    @Override
    public void saveSingers(List<Singer> singers) {
        //
    }

    public List<String> singersToLines(List<Singer> singers){
        List<String> lines = new ArrayList<>();
        singers.stream().forEach(singer -> {
            lines.add(String.format("Singer: %s", singer.getName()));
            singer.getAlbums().stream().forEach(album -> {
                lines.add(String.format("  Album: %s", album.getName()));
                album.getSongs().stream().forEach(song -> {
                    lines.add(String.format("    Song: %s, %d", song.getName(), song.getSize()));
                });
            });
            lines.add("");
        });
        return lines;
    }
}
