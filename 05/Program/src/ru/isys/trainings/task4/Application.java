package ru.isys.trainings.task4;

import ru.isys.trainings.task4.figure.Figure;
import ru.isys.trainings.task4.figure.rectangle.Rectangle;
import ru.isys.trainings.task4.figure.rectangle.Square;
import ru.isys.trainings.task4.figure.triangle.EquilateralTriangle;
import ru.isys.trainings.task4.figure.triangle.RectangularTriangle;

public class Application {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(2, 3);
        figureTosString(r);
        r.setSideA(5);
        r.setSideB(6);
        figureTosString(r);

        figureTosString(new Square(5));
        figureTosString(new RectangularTriangle(3, 4));
        figureTosString(new EquilateralTriangle(3));
    }

    private static void figureTosString(Figure figure){
        System.out.println(figure);
        System.out.println(String.format("area=%.2f perimeter=%.2f", figure.getArea(), figure.getPerimeter()));
        System.out.println("");
    }
}
