package ru.isys.task16.dao;

import ru.isys.task16.entities.Singer;

import java.util.List;

public interface SingerDAO {
    List<Singer> findSingers();

    void saveSingers(List<Singer> singers);

    List<String> singersToLines(List<Singer> singers);
}
