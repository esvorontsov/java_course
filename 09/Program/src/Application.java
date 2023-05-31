import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args){
        // исходная коллекция
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(getStr().split("\\r?\\n")));

        // список имен
        List<String> names = lines.stream()
                .filter(s -> s.trim() != "")
                .filter(s -> !s.contains("Имена"))
                .map(s -> s.split(" ")[0])
                .distinct()
                .sorted()
                .toList();

        // имена по количеству гласных
        printNamesWithVowelsCount(names, 3);
        printNamesWithVowelsCount(names, 4);
        printNamesWithVowelsCount(names, 5);

        // самые длинные и короткие имена
        Map<Integer, List<String>> byLengthGroups = names.stream().collect(Collectors.groupingBy(s -> s.length()));
        List<String> maxLengthNames = byLengthGroups.get(byLengthGroups.keySet().stream().max(Comparator.comparingInt(c -> c)).get());
        List<String> minLengthNames = byLengthGroups.get(byLengthGroups.keySet().stream().min(Comparator.comparingInt(c -> c)).get());

        System.out.println("самые длинные имена:");
        System.out.println(maxLengthNames);
        System.out.println();

        System.out.println("самые короткие имена:");
        System.out.println(minLengthNames);
        System.out.println();

        System.out.println(String.format("первое имя, начинающеемя с буквы Е: %s",
                names.stream().filter(s -> s.startsWith("Е")).findFirst().get()));
        System.out.println();

        ArrayList<String> vowels = getVowels();
        System.out.println(String.format("все имена содержат гласные: %s",
                names.stream()
                        .map(s -> Stream.of(s.split("")).anyMatch(l -> vowels.contains(l)))
                        .allMatch(b -> b) ? "да" : "нет"
        ));
        System.out.println();

        System.out.println("первые 10 имен с количеством символов:");
        names.stream().limit(10)
            .collect(Collectors.toMap(s -> s, s -> s.length()))
            .entrySet().forEach(e -> System.out.println(String.format("%s => %d", e.getKey(), e.getValue())));
        System.out.println();

        System.out.println("список имен по количеству символов:");
        names.stream()
            .collect(Collectors.groupingBy(s -> s.length(), TreeMap::new, Collectors.toList()))
            .entrySet().forEach(e -> System.out.println(String.format("%s => %s", e.getKey(), e.getValue())));
        System.out.println();

        System.out.println("списки имен, начинающихся на гласную/согласную:");
        names.stream()
                .collect(Collectors.partitioningBy(s -> vowels.contains(s.substring(0, 1).toLowerCase())))
                .entrySet().forEach(e -> System.out.println(String.format("%s => %s", e.getKey() ? "гласная" : "согласная", e.getValue())));
        System.out.println();
    }

    /**
     * возвращает список гласных букв
     */
    private static ArrayList<String> getVowels() {
        return new ArrayList<>(Arrays.asList("а", "о", "у", "и", "э", "ы", "е", "ё", "ю", "я"));
    }

    /**
     * выводит в консоль список имен по количестве гласных букв
     * @param names список имен
     * @param vowelsCount кол-во гласных в имени
     */
    private static void printNamesWithVowelsCount(List<String> names, int vowelsCount){
        ArrayList<String> vowels = getVowels();

        List<String> result = names.stream().filter(s -> Stream.of(s.split(""))
                        .map(l -> vowels.contains(l.toLowerCase()) ? l : "")
                        .reduce((x,y)->x + y).get().length() == vowelsCount)
                .collect(Collectors.toList());

        System.out.println(String.format("Имена с количеством гласных = %d:", vowelsCount));
        System.out.println(result);
        System.out.println();
    }

    /**
     * Считывает текстовый файл с именами одной строкой
     */
    private static String getStr(){
        try {
            String path = new File("").getAbsolutePath().concat("/files/data.txt");
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, Charset.forName("UTF-8"));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return "";
        }
    }
}
