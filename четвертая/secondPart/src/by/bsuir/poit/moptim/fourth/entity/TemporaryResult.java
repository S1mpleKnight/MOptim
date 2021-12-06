package by.bsuir.poit.moptim.fourth.entity;

import by.bsuir.poit.moptim.fourth.logic.ValuesCalculator;

import java.util.List;

public class TemporaryResult implements Comparable<TemporaryResult>{
    private final List<Double> vector;
    private final Double functionValue;

    public TemporaryResult(List<Double> vector) {
        this.vector = vector;
        this.functionValue = ValuesCalculator.calculateFunction(vector);
    }

    public Double getFunctionValue() {
        return functionValue;
    }

    public List<Double> getVector() {
        return vector;
    }

    @Override
    public int compareTo(TemporaryResult o) {
        return Double.compare(functionValue, o.getFunctionValue());
    }
}
