package ru.isys.trainings.task7.figure.triangle;

import ru.isys.trainings.task7.figure.Figure;
import ru.isys.trainings.task7.figure.NegativeValueException;

import java.util.Comparator;

import static java.lang.Math.sin;

public abstract class Triangle implements Figure, Comparable<Triangle> {

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

    @Override
    public int compareTo(Triangle triangle){
        double delta = getPerimeter() - triangle.getPerimeter();
        return delta > 0 ? 1 : delta < 0 ? -1 : 0;
    }

    public static class AreaComparator implements Comparator<Triangle> {
        @Override
        public int compare(Triangle t1, Triangle t2) {
            double delta = t1.getArea() - t2.getArea();
            return delta > 0 ? 1 : delta < 0 ? -1 : 0;
        }
    }
}
