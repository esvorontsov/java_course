package ru.isys.trainings.task7.figure.rectangle;

import ru.isys.trainings.task7.figure.NegativeValueException;

public class Square extends Rectangle {
    public Square(double side) throws NegativeValueException {
        super(side, side);
    }

    @Override
    public String toString() {
        return String.format("square with side: %.2f", getSideA());
    }
}
