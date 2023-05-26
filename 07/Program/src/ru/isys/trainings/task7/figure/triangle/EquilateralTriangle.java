package ru.isys.trainings.task7.figure.triangle;

import ru.isys.trainings.task7.figure.NegativeValueException;

public class EquilateralTriangle extends Triangle{
    public EquilateralTriangle(double side) throws NegativeValueException {
        super(side, side, 60);
    }

    @Override
    public double getPerimeter(){
        return getSideA() * 3;
    }

    @Override
    public String toString() {
        return String.format("equilateral triangle with side: %.2f", getSideA());
    }
}
