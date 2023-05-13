package ru.isys.trainings.task4.figure;

public interface Figure {
    public double getArea();
    public double getPerimeter();

    default void checkValue(double value){
        if (value <= 0){
            throw new IllegalArgumentException(String.format("illegal value of parameter: %.2f", value));
        }
    }
}
