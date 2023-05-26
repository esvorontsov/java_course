package ru.isys.trainings.task7;

import ru.isys.trainings.task7.figure.Figure;
import ru.isys.trainings.task7.figure.NegativeValueException;
import ru.isys.trainings.task7.figure.rectangle.Rectangle;
import ru.isys.trainings.task7.figure.rectangle.Square;
import ru.isys.trainings.task7.figure.triangle.EquilateralTriangle;
import ru.isys.trainings.task7.figure.triangle.RectangularTriangle;
import ru.isys.trainings.task7.figure.triangle.Triangle;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        //method1();
        method2();
    }

    /**
     * Реализация п.1 задания 7
     */
    private static void method1(){
        try {
            ArrayList<Rectangle> rectangles = new ArrayList<>(Arrays.asList(
                    new Square(5),
                    new Rectangle(3, 4),
                    new Rectangle(4, 3),
                    new Square(2),
                    new Square(1)
            ));

            System.out.println("Rectangles natural sorting by area");
            Collections.sort(rectangles);
            rectangles.forEach((r) -> figureToString(r));

            ArrayList<Triangle> triangles = new ArrayList<>(Arrays.asList(
                    new RectangularTriangle(3, 5),
                    new RectangularTriangle(1, 1),
                    new RectangularTriangle(4, 8),
                    new EquilateralTriangle(5),
                    new EquilateralTriangle(3),
                    new EquilateralTriangle(1)
            ));

            System.out.println("Triangles natural sorting by perimeter");
            Collections.sort(triangles);
            triangles.forEach((t) -> figureToString(t));

            System.out.println("Triangles sorting by area with comparator");
            Collections.sort(triangles, new Triangle.AreaComparator());
            triangles.forEach((t) -> figureToString(t));

            System.out.println("Rectangles sorting by perimeter with treeMap");
            TreeMap<Double, Rectangle> treeMap = new TreeMap<>();
            for(Rectangle r: rectangles) {

                treeMap.put(r.getPerimeter(), r);
            }
            for(Map.Entry<Double, Rectangle> entry : treeMap.entrySet()) {
                figureToString(entry.getValue());
            }

        }
        catch (NegativeValueException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void figureToString(Figure figure){
        System.out.println(figure);
        System.out.println(String.format("area=%.2f perimeter=%.2f", figure.getArea(), figure.getPerimeter()));
        System.out.println("");
    }

    /**
     * Реализация п.2 задания 7
     */
    private static void method2(){
        // готовим списки
        ArrayList<String> arrayList = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++){
            arrayList.add(UUID.randomUUID().toString());
        }
        LinkedList<String> linkedList = new LinkedList<>(arrayList);

        // сравниваем
        compareTime("добавление в начало списка:: ArrayList: %d ns, LinkedList: %d ns",
            () -> { arrayList.add(0, UUID.randomUUID().toString()); },
            () -> { linkedList.addFirst(UUID.randomUUID().toString()); }
        );
        compareTime("добавление в середину списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.add(500000, UUID.randomUUID().toString()); },
                () -> { linkedList.add(500000, UUID.randomUUID().toString()); }
        );
        compareTime("добавление в конец списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.add(UUID.randomUUID().toString()); },
                () -> { linkedList.addLast(UUID.randomUUID().toString()); }
        );

        compareTime("удаление c начала списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.remove(0); },
                () -> { linkedList.removeFirst(); }
        );
        compareTime("удаление из середины списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.remove(500000); },
                () -> { linkedList.remove(500000); }
        );
        compareTime("удаление с конца списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.remove(arrayList.size() - 1); },
                () -> { linkedList.removeLast(); }
        );

        compareTime("получение элемента c начала списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.get(0); },
                () -> { linkedList.getFirst(); }
        );
        compareTime("получение элемента из середины списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.get(500000); },
                () -> { linkedList.get(500000); }
        );
        compareTime("получение элемента с конца списка:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { arrayList.get(arrayList.size() - 1); },
                () -> { linkedList.getLast(); }
        );

        // готовим итераторы
        ListIterator<String> arrayListIterator = arrayList.listIterator();
        ListIterator<String> linkedListIterator = linkedList.listIterator();
        Iterator<String> arrayIterator = arrayList.iterator();
        Iterator<String> linkedIterator = linkedList.iterator();

        compareTime("проход по всему списку циклом for:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { for (String s : arrayList) {} },
                () -> { for (String s : linkedList) {} }
        );
        compareTime("проход по всему списку итератором listIterator:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { while(arrayListIterator.hasNext()){ arrayListIterator.next(); } },
                () -> { while(linkedListIterator.hasNext()){ linkedListIterator.next(); } }
        );
        compareTime("проход по всему списку итератором iterator:: ArrayList: %d ns, LinkedList: %d ns",
                () -> { while (arrayIterator.hasNext()) { arrayIterator.next(); } },
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
