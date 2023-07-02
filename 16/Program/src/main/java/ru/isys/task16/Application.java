package ru.isys.task16;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.isys.task16.dao.SingerDAO;
import ru.isys.task16.dao.file.SingerFileDao;
import ru.isys.task16.dao.jdbc.SingerJdbcDao;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class Application {
    public static void main(String[] args) throws InterruptedException{
        SpringApplication.run(Application.class);
    }

    @Value("${dao.type}")
    private String daoType;

    @Bean
    public SingerDAO singerDao() {
        switch (daoType) {
            case "file": return new SingerFileDao();
            case "jdbc": return new SingerJdbcDao();
            default: return new SingerFileDao();
        }
    }

}
