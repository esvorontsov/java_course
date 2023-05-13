package ru.isys.trainings.task4.figure.rectangle;

public class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }

    @Override
    public String toString() {
        return String.format("square with side: %.2f", getSideA());
    }
}
