package ru.isys.trainings.task7.figure;

import ru.isys.trainings.task7.figure.rectangle.Rectangle;
import ru.isys.trainings.task7.figure.triangle.Triangle;

public interface Figure {
    public double getArea();
    public double getPerimeter();

    default void checkValue(double value) throws NegativeValueException {
        if (value <= 0){
            throw new NegativeValueException(value);
        }
    }
}
