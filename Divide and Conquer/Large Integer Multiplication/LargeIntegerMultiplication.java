import java.math.BigInteger;
import java.util.Random;

public class LargeIntegerMultiplication {
    
    // Define a threshold for regular multiplication
    private static final int THRESHOLD = 10; 

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LargeIntegerMultiplication <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        if (n % 6 != 0 || n <= 0) {
            System.out.println("Please provide a positive integer n that is a multiple of 6.");
            return;
        }

        // Generate two random n-digit integers
        BigInteger A = generateRandomBigInteger(n);
        BigInteger B = generateRandomBigInteger(n);
        
        // Compute using original multiplication
        BigInteger product1 = traditionalMultiply(A, B);
        
        // Compute using modified multiplication
        BigInteger product2 = largeIntegerProduct(A, B);
        
        // Verify results
        if (product1.equals(product2)) {
            System.out.println("A: " + A);
            System.out.println("B: " + B);
            System.out.println("Product using Standard Multipication: " + product1);
            System.out.println("Product using Large Integer Multiplication: " + product2);
            System.out.println("Both methods produced the same result.");
        } else {
            System.out.println("Error: Different results from the two methods.");
        }
    }

    private static BigInteger generateRandomBigInteger(int n) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(rand.nextInt(9) + 1); // First digit (1-9)
        for (int i = 1; i < n; i++) {
            sb.append(rand.nextInt(10)); // Remaining digits (0-9)
        }
        return new BigInteger(sb.toString());
    }

    private static BigInteger traditionalMultiply(BigInteger u, BigInteger v) {
        return u.multiply(v);
    }

    private static BigInteger largeIntegerProduct(BigInteger u, BigInteger v) {
        int n = Math.max(u.toString().length(), v.toString().length());
        
        if (n <= THRESHOLD) {
            return traditionalMultiply(u, v);
        }

        int m = n / 3;

        BigInteger[] partsU = splitNumber(u, m);
        BigInteger[] partsV = splitNumber(v, m);

        BigInteger x = partsU[0];
        BigInteger y = partsU[1];
        BigInteger z = partsU[2];
        BigInteger w = partsV[0];
        BigInteger t = partsV[1];
        BigInteger r = partsV[2];

        BigInteger p1 = largeIntegerProduct(x, w);
        BigInteger p2 = largeIntegerProduct(y, t);
        BigInteger p3 = largeIntegerProduct(z, r);

        // Combine results
        return p1.multiply(BigInteger.TEN.pow(2 * m))
                .add(p2.multiply(BigInteger.TEN.pow(m)))
                .add(p3);
    }

    private static BigInteger[] splitNumber(BigInteger number, int m) {
        String numStr = number.toString();
        int length = numStr.length();
        
        // Pad the string if necessary
        while (numStr.length() < 3 * m) {
            numStr = "0" + numStr;
        }

        String part1 = numStr.substring(0, m);
        String part2 = numStr.substring(m, 2 * m);
        String part3 = numStr.substring(2 * m, 3 * m);

        return new BigInteger[] { new BigInteger(part1), new BigInteger(part2), new BigInteger(part3) };
    }
}
