package ru.isys.trainings.task4.figure.triangle;

import static java.lang.Math.sqrt;

public class RectangularTriangle extends Triangle{
    public RectangularTriangle(double sideA, double sideB) {
        super(sideA, sideB, 90);
    }

    @Override
    public double getPerimeter(){
        return getSideA() + getSideB() + sqrt(getSideA() * getSideA() + getSideB() * getSideB());
    }

    @Override
    public String toString() {
        return String.format("rectangular triangle with sides: %.2f and %.2f", getSideA(), getSideB());
    }
}
