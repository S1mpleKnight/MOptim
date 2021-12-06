package by.bsuir.poit.moptim.fourth.starter;

import by.bsuir.poit.moptim.fourth.entity.TemporaryResult;
import by.bsuir.poit.moptim.fourth.logic.ValuesCalculator;
import by.bsuir.poit.moptim.fourth.logic.VectorGenerator;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int MAX_AREA = 1350;
    private static final int COMPONENT_AMOUNT = 5;
    private static final int TIMES_TO_FAIL = 5; //M
    private static final int MAX_ITERATION_VALUE = 100; //N
    private static final double MIN_STEP_VALUE = 0.5; //R
    private static final double COMPRESSION_RATIO = 0.8; //b
    private static final double START_STEP_VALUE = 15.0; //t0
    private static int successIterations = 0; //k
    private static TemporaryResult result = new TemporaryResult(Arrays.asList(150.0,150.0,150.0,150.0,150.0)); //x0

    public static void main(String[] args) {
        double stepValue = START_STEP_VALUE;
        int failTimes = 0;
        while (successIterations < MAX_ITERATION_VALUE && ValuesCalculator.calculateArea(result.getVector()) > MAX_AREA && ValuesCalculator.checkValues(result.getVector())) {
            List<Double> vector = getCalculatedVector(stepValue);
            TemporaryResult temp = new TemporaryResult(vector);
            showTempResult(temp, failTimes, stepValue);
            if (temp.compareTo(result) > 0) {
                result = temp;
                successIterations++;
                failTimes = 0;
            } else {
                failTimes++;
                if (failTimes == TIMES_TO_FAIL && stepValue > MIN_STEP_VALUE) {
                    stepValue = Math.max(stepValue * COMPRESSION_RATIO, MIN_STEP_VALUE);
                    failTimes = 0;
                } else if (failTimes == TIMES_TO_FAIL && stepValue == MIN_STEP_VALUE){
                    break;
                }
            }
        }
        System.out.println("End");
        showTempResult(result, failTimes, stepValue);
    }

    private static void showTempResult(TemporaryResult temp, int failTimes, double stepValue) {
        System.out.println("Function value: " + temp.getFunctionValue() + " Area: " + ValuesCalculator.calculateArea(temp.getVector()));
        System.out.println("Failed: " + failTimes + " Step: " + stepValue);
        for (int i = 0; i < temp.getVector().size(); i++) {
            System.out.print("X" + i + " : " + temp.getVector().get(i) + " | ");
        }
        System.out.println("Success: " + successIterations + "\n");
    }

    private static List<Double> getCalculatedVector(double stepValue) {
        List<Double> vector = VectorGenerator.getVector(5);
        vector = ValuesCalculator.multiplyVector(vector, stepValue);
        return ValuesCalculator.addVectors(result.getVector(), vector);
    }
}
