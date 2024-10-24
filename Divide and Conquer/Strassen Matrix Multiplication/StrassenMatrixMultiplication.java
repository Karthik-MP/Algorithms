/*
 * Binghamton University 2024 - School of Computing
 * Author: Karthik Maganahalli Prakash
 * Strassen Martix Multiplication
 */

import java.util.Arrays;
import java.util.Random;

public class StrassenMatrixMultiplication {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("StrassenMatrixMultiplication provide value of n");
            return;
        }

        int n = Integer.parseInt(args[0]);
        if ((n & (n - 1)) != 0 || n < 1 || n > 1024) {
            System.out.println(n + " is not a valid value for n. n should be a power of 2 in the range 1, 1024.");
            return;
        }

        int maxValue = (int) Math.sqrt(Integer.MAX_VALUE / n);
        int[][] A = generateRandomMatrix(n, maxValue);
        int[][] B = generateRandomMatrix(n, maxValue);

        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);

        int[][] standardResult = standardMatrixMultiply(A, B);
        int[][] strassenResult = strassenMatrixMultiply(A, B);

        System.out.println("The standard matrix multiplication A*B = ");
        printMatrix(standardResult);
        System.out.println("The Strassens multiplication A*B = ");
        printMatrix(strassenResult);
    }

    private static int[][] generateRandomMatrix(int n, int maxValue) {
        Random rand = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(maxValue);
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(row -> {
                    Arrays.stream(row)
                            .forEach(val -> System.out.print(val + "\t"));
                    System.out.println();
                });
    }

    private static int[][] standardMatrixMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] strassenMatrixMultiply(int[][] A, int[][] B) {
        int n = A.length;
        if (n == 1) {
            return new int[][] { { A[0][0] * B[0][0] } };
        }

        int halfSize = n / 2;
        int[][] A11 = new int[halfSize][halfSize];
        int[][] A12 = new int[halfSize][halfSize];
        int[][] A21 = new int[halfSize][halfSize];
        int[][] A22 = new int[halfSize][halfSize];

        int[][] B11 = new int[halfSize][halfSize];
        int[][] B12 = new int[halfSize][halfSize];
        int[][] B21 = new int[halfSize][halfSize];
        int[][] B22 = new int[halfSize][halfSize];

        // Divide matrices into quarters
        for (int i = 0; i < halfSize; i++) {
            for (int j = 0; j < halfSize; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + halfSize];
                A21[i][j] = A[i + halfSize][j];
                A22[i][j] = A[i + halfSize][j + halfSize];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + halfSize];
                B21[i][j] = B[i + halfSize][j];
                B22[i][j] = B[i + halfSize][j + halfSize];
            }
        }

        int[][] M1 = strassenMatrixMultiply(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] M2 = strassenMatrixMultiply(addMatrices(A21, A22), B11);
        int[][] M3 = strassenMatrixMultiply(A11, subtractMatrices(B12, B22));
        int[][] M4 = strassenMatrixMultiply(A22, subtractMatrices(B21, B11));
        int[][] M5 = strassenMatrixMultiply(addMatrices(A11, A12), B22);
        int[][] M6 = strassenMatrixMultiply(subtractMatrices(A21, A11), addMatrices(B11, B12));
        int[][] M7 = strassenMatrixMultiply(subtractMatrices(A12, A22), addMatrices(B21, B22));

        int[][] C = new int[n][n];
        for (int i = 0; i < halfSize; i++) {
            for (int j = 0; j < halfSize; j++) {
                C[i][j] = M1[i][j] + M4[i][j] - M5[i][j] + M7[i][j];
                C[i][j + halfSize] = M3[i][j] + M5[i][j];
                C[i + halfSize][j] = M2[i][j] + M4[i][j];
                C[i + halfSize][j + halfSize] = M1[i][j] + M3[i][j] - M2[i][j] + M6[i][j];
            }
        }

        return C;
    }

    private static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    private static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }
}
