package util;

import java.util.List;

public class MathHelper {
    public static double magnitude(List<Integer> vector)
    {
        double magnitude = 0;
        for (int i = 0; i < vector.size(); i++) {
            magnitude += Math.pow(vector.get(i), 2);
        }
        magnitude = Math.sqrt(magnitude);
        return magnitude;
    }

    public static double dotProduct(List<Integer> vector1, List<Integer> vector2)
    {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Vectors must be of the same length. Vector 1: " + vector1.size() + " != Vector 2: " + vector2.size());
        }
        double dotProduct = 0;
        for (int i = 0; i < vector1.size(); i++) {
            dotProduct += vector1.get(i) * vector2.get(i);
        }
        return dotProduct;
    }
}