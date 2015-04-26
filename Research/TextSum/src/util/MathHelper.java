package util;

import java.util.ArrayList;

public class MathHelper {
    public static double magnitude(Integer[] vector)
    {
        double magnitude = 0;
        for (Integer v : vector) {
            magnitude += Math.pow(v, 2);
        }
        magnitude = Math.sqrt(magnitude);
        return magnitude;
    }

    public static double magnitude(ArrayList<Integer> vector)
    {
        Integer[] arr = new Integer[vector.size()];
        vector.toArray(arr);
        return magnitude(arr);
    }

    public static double dotProduct(Integer[] vector1, Integer[] vector2)
    {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must be of the same length. Vector 1: " + vector1.length + " != Vector 2: " + vector2.length);
        }
        double dotProduct = 0;
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
        }
        return dotProduct;
    }

    public static double dotProduct(ArrayList<Integer> vector1, ArrayList<Integer> vector2)
    {
        Integer[] v1 = new Integer[vector1.size()];
        vector1.toArray(v1);
        Integer[] v2 = new Integer[vector2.size()];
        vector2.toArray(v2);
        return dotProduct(v1, v2);
    }

}