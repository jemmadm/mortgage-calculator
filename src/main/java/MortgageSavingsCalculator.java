import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MortgageSavingsCalculator {

    private double propertyValue;
    private double depositPercent;
    private double currentSavings;
    private double monthlySavingsAmount;
    private String depositDateAsString;

    public MortgageSavingsCalculator(double propertyValue, double depositPercent, double currentSavings, String depositDateAsString) {
        this.propertyValue = propertyValue;
        this.depositPercent = depositPercent;
        this.currentSavings = currentSavings;
        this.depositDateAsString = depositDateAsString;
    }

    public MortgageSavingsCalculator(double propertyValue, double depositPercent, double currentSavings, double monthlySavingsAmount) {
        this.propertyValue = propertyValue;
        this.depositPercent = depositPercent;
        this.currentSavings = currentSavings;
        this.monthlySavingsAmount = monthlySavingsAmount;
    }

    public String depositDateNeeded() {

        Period monthsToSave = Period.between(LocalDate.now(), LocalDate.parse(depositDateAsString, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        int totalMonthsToSave = monthsToSave.getYears() * 12 + monthsToSave.getMonths();
        String monthlyPaymentsNeededForDate = "To have your deposit by " + depositDateAsString + ", you will need to save £" +
                new DecimalFormat("##.##").format(getDepositNeededAfterDeducingSavings(propertyValue, depositPercent, currentSavings) / totalMonthsToSave) +
                " per month";
        System.out.println(monthlyPaymentsNeededForDate);
        return monthlyPaymentsNeededForDate;
    }

    private double getDepositNeededAfterDeducingSavings(double propertyValue, double depositPercent, double currentSavings) {
        return depositPercent / 100 * propertyValue - currentSavings;
    }

    public String yearlyAndMonthlyPayments() {
        String timeToSave = new DecimalFormat("##.##").format((getDepositNeededAfterDeducingSavings(propertyValue, depositPercent, currentSavings)) / monthlySavingsAmount / 12);
        String[] time = timeToSave.split("\\.");
        double[] savingTime = new double[2];
        savingTime[0] = Double.parseDouble(time[0]);
        savingTime[1] = Double.parseDouble(time[1]);
        String timeToSaveDeposit = "It will take you " + (int) savingTime[0] + " years " + (int) Math.ceil((savingTime[1] / 100) * 12) + " months to save the deposit needed";
        System.out.println(timeToSaveDeposit);
        return timeToSaveDeposit;
    }

    public static void main(String[] args) {
        System.out.println("Mortgage Calculator!");
        System.out.println("Would you like to work out your mortgage deposit by the monthly amount you can save or with a specific date in mind?");
        Scanner input = new Scanner(System.in);

        try {
            String amountOrDate = input.next();
            System.out.println("What is the value of the property?");
            double propertyValue = input.nextDouble();
            System.out.println("What percent size of deposit needed?");
            double depositPercent = input.nextDouble();
            System.out.println("How much do you currently have saved?");
            double currentSavingsInput = input.nextDouble();

            if (amountOrDate.equalsIgnoreCase("monthly") || amountOrDate.equalsIgnoreCase("m")) {
                System.out.println("How much can you save a month?");
                double monthlySavingsAmount = input.nextDouble();
                MortgageSavingsCalculator mortgageSavingsCalculator = new MortgageSavingsCalculator(propertyValue, depositPercent, currentSavingsInput, monthlySavingsAmount);
                mortgageSavingsCalculator.yearlyAndMonthlyPayments();
            } else {
                System.out.println("When do you want the deposit by? Please use the format 13-08-2020");
                String depositDate = input.next();
                MortgageSavingsCalculator mortgageSavingsCalculator = new MortgageSavingsCalculator(propertyValue, depositPercent, currentSavingsInput, depositDate);
                mortgageSavingsCalculator.depositDateNeeded();
            }
        } finally {
            input.close();
        }
    }
}