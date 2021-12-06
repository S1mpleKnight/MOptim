package by.bsuir.poit.moptim.fourth.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class VectorGenerator {
    private static final Random VALUE_GENERATOR = new Random();
    private static final int LOW_BOUND = -1;

    private static double generateValue() {
        return VALUE_GENERATOR.nextGaussian() * 2 + LOW_BOUND;
    }

    private static List<Double> generateVectorValues(int amount) {
        List<Double> values = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            values.add(generateValue());
        }
        return values;
    }

    public static Double getVectorModulus(List<Double> vector) {
        double sum = 0;
        for (Double a : vector) {
            sum += Math.pow(a, 2);
        }
        return Math.sqrt(sum);
    }

    public static List<Double> getVector(int amount) {
        List<Double> vector = generateVectorValues(amount);
        double modulus = getVectorModulus(vector);
        return vector
                .stream()
                .map(value -> value / modulus)
                .collect(Collectors.toList());
    }
}
