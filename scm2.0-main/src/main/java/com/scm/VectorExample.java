package com.scm;


//Java 16 introduced a new incubator module, jdk.incubator.vector, to express vector computations that compile to optimal vector instructions on supported hardware.
// import jdk.incubator.vector.*;

public class VectorExample {

    public static void main(String[] args) {
    //     int[] a = {1, 2, 3, 4, 5, 6, 7, 8}; 
    //     int[] b = {10, 20, 30, 40, 50, 60, 70, 80};
    //     int[] result = new int[a.length];

    //     try (var scope = VectorScope.create()) {
    //         IntVector species = IntVector.SPECIES_256; 
    //         for (int i = 0; i < a.length; i += species.length()) {
    //             IntVector va = IntVector.fromArray(species, a, i);
    //             IntVector vb = IntVector.fromArray(species, b, i);
    //             IntVector vc = va.add(vb);
    //             vc.intoArray(result, i);
    //         }
    //     }

    //     // Handle remaining elements (if any)
    //     for (int i = a.length - species.length(); i < a.length; i++) {
    //         result[i] = a[i] + b[i];
    //     }

    //     // Print the result
    //     System.out.println(Arrays.toString(result)); 
    }
}
