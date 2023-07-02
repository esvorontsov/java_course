package ru.isys.task16.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isys.task16.aspects.Timed;
import ru.isys.task16.dao.SingerDAO;
import ru.isys.task16.entities.Singer;

import java.util.List;

@Service
public class SingerService {
    private SingerDAO singerDao;

    public SingerService(SingerDAO singerDao){
        this.singerDao = singerDao;
    }

    @Timed
    public void filterBySongName(String songName) {
        List<Singer> result = singerDao.findSingers().stream()
                .filter(s -> s.getAlbums().stream()
                        .anyMatch(a -> a.getSongs().stream()
                                .anyMatch(song -> song.getName().toLowerCase().contains(songName.toLowerCase()))))
                .toList();
        singerDao.saveSingers(result);
    }

    @Timed
    public void filterByMinAlbumsCount(int minAlbumsCount) {
        List<Singer> result = singerDao.findSingers().stream()
                .filter(s -> s.getAlbums().size() >= minAlbumsCount)
                .toList();
        singerDao.saveSingers(result);
    }
}
