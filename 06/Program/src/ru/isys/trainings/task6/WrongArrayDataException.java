package ru.isys.trainings.task6;

public class WrongArrayDataException extends Exception {

    private int ind1;
    private int ind2;
    private String value;

    public WrongArrayDataException(int ind1, int ind2, String value){
        this.ind1 = ind1;
        this.ind2 = ind2;
        this.value = value;
    }

    @Override
    public String getMessage(){
        return toString();
    }

    @Override
    public String toString(){
        return String.format("There is not a number in the array cell[%d, %d] = %s", ind1, ind2, value);
    }
}
