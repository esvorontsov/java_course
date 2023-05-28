import java.util.*;
import java.util.function.Consumer;

public class Application {
    public static void main(String[] args) {
        method1();
        method2();
    }

    /**
     * реализация пункта 1 дз
     */
    private static void method1(){
        // работа со списком
        LinkList<String> list = new LinkList<>(Arrays.asList("1", "2", "3", "4", "5"));
        System.out.println(list);
        list.add("6");
        System.out.println(list);
        list.add(1, UUID.randomUUID().toString());
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(String.format("list[%d] = %s", 3, list.get(3)));
        System.out.println(String.format("list.isEmpty() = %b", list.isEmpty()));
        System.out.println(String.format("list.size() = %d", list.size()));
        list.clear();
        System.out.println(list);
        System.out.println(String.format("list.size() = %d", list.size()));
        System.out.println(String.format("list.isEmpty() = %b", list.isEmpty()));

        list.addAll(Arrays.asList("1", "2", "3"));
        System.out.println(list);

        // делегат вывода строки в консоль
        Consumer<String> print = (s) -> System.out.print(String.format("%s ", s));

        // перебор элементов списка
        for (String s: list) { print.accept(s); }
        System.out.println();

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) { print.accept(iterator.next()); }
        System.out.println();

        list.forEach(print);
        System.out.println();
        System.out.println();
    }

    /**
     * реализация пункта 2 дз
     */
    private static void method2(){
        // инициализация списков
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 1000000; i++){
            linkedList.add(UUID.randomUUID().toString());
        }
        System.out.println(String.format("LinkedList.size() = %d", linkedList.size()));
        LinkList<String> list = new LinkList<>(linkedList);
        System.out.println(String.format("LinkList.size() = %d", list.size()));

        // сравниваем
        compareTime("добавление в начало списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.add(0, UUID.randomUUID().toString()); },
                () -> { linkedList.add(0, UUID.randomUUID().toString()); }
        );
        compareTime("добавление в середину списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.add(500000, UUID.randomUUID().toString()); },
                () -> { linkedList.add(500000, UUID.randomUUID().toString()); }
        );
        compareTime("добавление в конец списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.add(UUID.randomUUID().toString()); },
                () -> { linkedList.add(UUID.randomUUID().toString()); }
        );

        compareTime("удаление c начала списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.remove(0); },
                () -> { linkedList.remove(0); }
        );
        compareTime("удаление из середины списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.remove(500000); },
                () -> { linkedList.remove(500000); }
        );
        compareTime("удаление с конца списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.remove(list.size() - 1); },
                () -> { linkedList.remove(linkedList.size() - 1); }
        );

        compareTime("получение элемента c начала списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.get(0); },
                () -> { linkedList.get(0); }
        );
        compareTime("получение элемента из середины списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.get(500000); },
                () -> { linkedList.get(500000); }
        );
        compareTime("получение элемента с конца списка:: LinkList: %d ns, LinkedList: %d ns",
                () -> { list.get(list.size() - 1); },
                () -> { linkedList.get(linkedList.size() - 1); }
        );

        // готовим итераторы
        Iterator<String> listIterator = list.iterator();
        Iterator<String> linkedIterator = linkedList.iterator();

        compareTime("проход по всему списку циклом for:: LinkList: %d ns, LinkedList: %d ns",
                () -> { for (String s : list) {} },
                () -> { for (String s : linkedList) {} }
        );
        compareTime("проход по всему списку итератором iterator:: LinkList: %d ns, LinkedList: %d ns",
                () -> { while (listIterator.hasNext()) { listIterator.next(); } },
                () -> { while (linkedIterator.hasNext()) { linkedIterator.next(); } }
        );
    }

    /**
     * Сравниевает время выполнения двух действий, выводит результат в наносекундах
     * @param format шаблон сообщения, должен содержать два места для результатов выполнения: %d, %d
     * @param action1 первое действие
     * @param action2 второе действие
     */
    private static void compareTime(String format, Action action1, Action action2){
        long time1 = getTime(() -> { action1.apply(); });
        long time2 = getTime(() -> { action2.apply(); });
        System.out.println(String.format(format, time1, time2));
    }

    /**
     * Замеряет время выполнения
     * @param action действие
     * @return время выполнения в наносекундах
     */
    private static long getTime(Action action){
        long startTime = System.nanoTime();
        action.apply();
        return System.nanoTime() - startTime;
    }

    @FunctionalInterface
    public interface Action {
        void apply();
    }


}
