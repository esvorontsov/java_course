package ru.isys.trainings.task4.figure;

public class NegativeValueException extends Exception {
    private double value;

    public NegativeValueException(double value) {
        this.value = value;
    }

    @Override
    public String getMessage(){
        return String.format("The parameter has a negative value: %.2f", value);
    }

    @Override
    public String toString(){
        return getMessage();
    }
}
