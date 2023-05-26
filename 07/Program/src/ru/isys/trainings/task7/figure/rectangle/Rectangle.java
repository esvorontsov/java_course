package ru.isys.trainings.task7.figure.rectangle;

import ru.isys.trainings.task7.figure.Figure;
import ru.isys.trainings.task7.figure.NegativeValueException;
import ru.isys.trainings.task7.figure.triangle.Triangle;

public class Rectangle implements Figure, Comparable<Rectangle> {
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

    @Override
    public int compareTo(Rectangle rectangle){
        double delta = getArea() - rectangle.getArea();
        return delta > 0 ? 1 : delta < 0 ? -1 : 0;
    }
}
