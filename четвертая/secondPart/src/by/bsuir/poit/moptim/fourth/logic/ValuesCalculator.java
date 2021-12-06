package by.bsuir.poit.moptim.fourth.logic;

import java.util.List;
import java.util.stream.Collectors;

public class ValuesCalculator {
    private static final int[] orderCosts = new int[]{5000, 7000, 2000, 200, 800};
    private static final int[] needs = new int[]{6, 110, 7, 5, 4};
    private static final int[] maintenanceCosts = new int[]{15, 8, 20, 4, 8};
    private static final int[] productArea = new int[]{10, 5, 2, 3, 4};

    public static List<Double> addVectors(List<Double> first, List<Double> second) {
        for (int i = 0; i < first.size(); i++) {
            second.set(i, second.get(i) + first.get(i));
        }
        return second;
    }

    public static Double calculateFunction(List<Double> vector) {
        double sum = 0;
        for (int i = 0; i < vector.size(); i++) {
            sum += calculateValue(i, vector.get(i));
        }
        return sum;
    }

    private static Double calculateValue(int pos, double value) {
        return orderCosts[pos] * needs[pos] / value + 1 / 2 * maintenanceCosts[pos] * value;
    }

    public static List<Double> multiplyVector (List<Double> vector, Double value) {
        return vector.stream().map(v -> v * value).collect(Collectors.toList());
    }

    public static Double calculateArea(List<Double> vector) {
        double area = 0.0;
        for (int i = 0; i < vector.size(); i++) {
            area += vector.get(i) * productArea[i];
        }
        return area;
    }

    public static boolean checkValues(List<Double> vector) {
        return vector.stream().filter(value -> value > 1).count() == vector.size();
    }
}
