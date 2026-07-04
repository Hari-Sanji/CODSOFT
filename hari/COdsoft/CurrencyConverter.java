package COdsoft;

import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Available Currencies:");
        System.out.println("1. INR");
        System.out.println("2. USD");
        System.out.println("3. EUR");
        System.out.print("Enter Base Currency: ");
        String from = sc.next().toUpperCase();
        System.out.print("Enter Target Currency: ");
        String to = sc.next().toUpperCase();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        double rate = 0;

        if (from.equals("INR") && to.equals("USD"))
            rate = 0.012;
        else if (from.equals("USD") && to.equals("INR"))
            rate = 83.50;
        else if (from.equals("INR") && to.equals("EUR"))
            rate = 0.011;
        else if (from.equals("EUR") && to.equals("INR"))
            rate = 90.50;
        else if (from.equals("USD") && to.equals("EUR"))
            rate = 0.92;
        else if (from.equals("EUR") && to.equals("USD"))
            rate = 1.09;
        else if (from.equals(to))
            rate = 1;
        if (rate == 0) {
            System.out.println("Invalid Currency Selection");
        } else {
            double convertedAmount = amount * rate;
            System.out.printf("Converted Amount: %.2f %s%n",
                    convertedAmount, to);
        }
        sc.close();
    }
}
