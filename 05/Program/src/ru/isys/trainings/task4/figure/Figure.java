package ru.isys.trainings.task4.figure;

public interface Figure {
    public double getArea();
    public double getPerimeter();

    default void checkValue(double value) throws NegativeValueException {
        if (value <= 0){
            throw new NegativeValueException(value);
        }
    }
}
