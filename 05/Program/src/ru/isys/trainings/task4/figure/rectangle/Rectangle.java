package ru.isys.trainings.task4.figure.rectangle;

import ru.isys.trainings.task4.figure.Figure;
import ru.isys.trainings.task4.figure.NegativeValueException;

public class Rectangle implements Figure {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) throws NegativeValueException {
        checkValue(sideA);
        this.sideA = sideA;
        checkValue(sideB);
        this.sideB = sideB;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(int side) throws NegativeValueException {
        checkValue(side);
        this.sideA = side;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(int side) throws NegativeValueException {
        checkValue(side);
        this.sideB = side;
    }

    @Override
    public double getArea(){
        return sideA * sideB;
    }

    @Override
    public double getPerimeter(){
        return 2 * (sideA + sideB);
    }

    @Override
    public String toString() {
        return String.format("rectangle with sides: %.2f and %.2f", sideA, sideB);
    }
}
