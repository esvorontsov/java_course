import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    /**
     * Набор допустимых вариантов работы
     */
    private enum FactoryCase { FILE, DB, FILE_TO_DB }

    /**
     * Выбор варианта
     * @return
     */
    private static FactoryCase getFactoryCase(){
        Scanner sc = new Scanner(System.in);
        String str;
        while (true){
            System.out.println("введите один из вариантов: ");
            System.out.println("file - загрузка из файловой системы");
            System.out.println("db - загрузка из бд");
            System.out.println("file_to_db - наполнение бд из файловой системы");
            str = sc.nextLine();

            try {
                str = (str == null) ? "" : str;
                FactoryCase result = FactoryCase.valueOf(str.toUpperCase());
                return result;
            }
            catch (IllegalArgumentException e){
                System.out.println(String.format("'%s' - не допустимое значение, попробуйте еще раз", str));
            }
        }
    }

    /**
     * Возвращает dao
     * @param factoryCase - выбранный вариант
     * @return
     */
    private static SingerDAO getSingerDao(FactoryCase factoryCase){
        switch (factoryCase){
            case FILE:
                return SingerFileDaoFactory.getInstance().createSingerDao();

            case DB:
                return SingerJdbcDaoFactory.getInstance().createSingerDao();

            case FILE_TO_DB:
                SingerDAO dao = SingerFileDaoFactory.getInstance().createSingerDao();
                return new SingerJdbcDao(dao.findSingers());
        }
        return null;
    }

    /**
     * Выводит список в консоль
     * @param dao
     * @param singers
     */
    private static void singersToConsole(SingerDAO dao, List<Singer> singers){
        dao.singersToLines(singers).forEach(s -> System.out.println(s));
    }

    public static void main(String[] args){
        while (true) {
            SingerDAO dao = getSingerDao(getFactoryCase());
            System.out.println("исходое содержимое библиотеки:");
            singersToConsole(dao, dao.findSingers());

            SingerService service = new SingerService(dao);
            System.out.println("фильтруем по кол-ву альбомов >= 2:");
            List<Singer> l1 = service.filterByMinAlbumsCount(2);
            dao.saveSingers(l1);
            singersToConsole(dao, l1);

            System.out.println("фильтруем по наличию в названии песни строки 'song8':");
            List<Singer> l2 = service.filterBySongName("song8");
            dao.saveSingers(l2);
            singersToConsole(dao, l2);

            new Scanner(System.in).nextLine();
        }
    }
}
