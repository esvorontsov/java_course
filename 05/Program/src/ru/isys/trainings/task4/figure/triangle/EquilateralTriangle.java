package ru.isys.trainings.task4.figure.triangle;

public class EquilateralTriangle extends Triangle{
    public EquilateralTriangle(double side) {
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
