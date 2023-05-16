package ru.isys.trainings.task4.figure.triangle;

import ru.isys.trainings.task4.figure.Figure;
import ru.isys.trainings.task4.figure.NegativeValueException;

import static java.lang.Math.sin;

public abstract class Triangle implements Figure {

    private double sideA;
    private double sideB;
    private double angle;

    public Triangle(double sideA, double sideB, double angle) throws NegativeValueException {
        checkValue(sideA);
        this.sideA = sideA;
        checkValue(sideB);
        this.sideB = sideB;
        checkValue(angle);
        this.angle = angle;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public double getArea() {
        return sideA * sideB * sin(angle * Math.PI / 180) / 2;
    }

    @Override
    public String toString() {
        return String.format("triangle with sides: %.2f and %.2f and angle between them: %.2f", sideA, sideB, angle);
    }
}
