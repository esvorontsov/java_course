package ru.isys.task16;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isys.task16.services.SingerService;

@Component
public class ScheduledTask {

    private SingerService service;

    public ScheduledTask(SingerService service){
        this.service = service;
    }

    @Scheduled(fixedRate = 20000)
    public void filterByMinAlbumsCount() {
        System.out.println("фильтруем по кол-ву альбомов >= 2");
        service.filterByMinAlbumsCount(2);
        System.out.println("выполнено");
        System.out.println();
    }

    @Scheduled(fixedRate = 20000, initialDelay = 10000)
    public void filterBySongName() {
        System.out.println("фильтруем по наличию в названии песни строки 'song8'");
        service.filterBySongName("song8");
        System.out.println("выполнено");
        System.out.println();
    }
}
