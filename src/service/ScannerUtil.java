package service;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner = new Scanner(System.in);

    private ScannerUtil() {}

    public static void reset() {
        scanner = new Scanner(System.in);
    }

    public static int getInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            if (!scanner.hasNext()) {
                System.err.println("!!! SCANNER ERROR: Закінчилися дані для введення числа. !!!");
                throw new java.util.NoSuchElementException("Немає даних для getInt");
            }
            System.out.println("Будь ласка, введіть число!");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public static String getString(String message) {
        System.out.print(message);
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    public static double getDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            if (!scanner.hasNext()) {
                System.err.println("!!! SCANNER ERROR: Закінчилися дані для введення double. !!!");
                throw new java.util.NoSuchElementException("Немає даних для getDouble");
            }
            System.out.println("Введіть коректне число!");
            scanner.next();
        }
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }
}