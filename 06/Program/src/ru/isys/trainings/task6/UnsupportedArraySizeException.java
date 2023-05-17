package ru.isys.trainings.task6;

public class UnsupportedArraySizeException extends RuntimeException {

    private int size1;
    private int size2;

    public UnsupportedArraySizeException(int size1, int size2){
        this.size1 = size1;
        this.size2 = size2;
    }

    @Override
    public String toString(){
        return String.format("Array dimensions do not match: %d <> %d", size1, size2);
    }

    @Override
    public String getMessage(){
        return toString();
    }
}
