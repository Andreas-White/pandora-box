package tools;

import java.text.DecimalFormat;

public class MortgageCalculator {

    public static void main(String[] args) {
        // Input values (replace with your actual values)
        int loanTermInMonths = 600; // Total time of the mortgage in months
        double interestRate = 0.05; // Annual interest rate (replace with decimal value)
        double loanAmount = 100000.0; // Amount borrowed

        // Calculate monthly interest rate
        double monthlyInterestRate = interestRate / 12.0;

        // Calculate monthly payment
        double monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, loanTermInMonths);

        System.out.println("Monthly Payment: " + formatCurrency(monthlyPayment));

        // Print monthly payment breakdown for each month
        printMonthlyPayments(loanAmount, monthlyInterestRate, loanTermInMonths, monthlyPayment);
    }

    public static double calculateMonthlyPayment(double loanAmount, double monthlyInterestRate, int loanTermInMonths) {
        double numerator = loanAmount * monthlyInterestRate;
        double denominator = 1 - Math.pow(1 + monthlyInterestRate, -loanTermInMonths);
        return numerator / denominator;
    }

    public static void printMonthlyPayments(double loanAmount, double monthlyInterestRate, int loanTermInMonths, double monthlyPayment) {
        double remainingBalance = loanAmount;
        double totalInterestPaid = 0.0;

        DecimalFormat moneyFormat = new DecimalFormat("'$'#,##0.00");

        System.out.println("\nMonth\t\tPayment\t\t\tPrincipal\t\tInterest\t\tRemaining Balance");
        for (int month = 1; month <= loanTermInMonths; month++) {
            double interestPayment = remainingBalance * monthlyInterestRate;
            double principalPayment = monthlyPayment - interestPayment;

            remainingBalance -= principalPayment;
            totalInterestPaid += interestPayment;

            System.out.println(STR."\{month}\t\t\t\{moneyFormat.format(monthlyPayment)}\t\t\t\{moneyFormat.format(principalPayment)}\t\t\t\{moneyFormat.format(interestPayment)}\t\t\t\{moneyFormat.format(remainingBalance)}");
        }

        System.out.println(STR."\n\nTotal Interest Paid: \{moneyFormat.format(totalInterestPaid)}");
    }

    public static String formatCurrency(double amount) {
        DecimalFormat moneyFormat = new DecimalFormat("'$'#,##0.00");
        return moneyFormat.format(amount);
    }
}
