import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    // Predefined exchange rates (for simulation purposes)
    private static Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Define exchange rates against the base currency (USD in this case)
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85); // 1 USD = 0.85 EUR
        exchangeRates.put("GBP", 0.73); // 1 USD = 0.73 GBP
        exchangeRates.put("JPY", 110.42); // 1 USD = 110.42 JPY
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Currency Converter");

        // Select base currency
        System.out.print("Enter the base currency ( USD, EUR, GBP, JPY): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        // Select target currency
        System.out.print("Enter the target currency ( USD, EUR, GBP, JPY): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Fetch exchange rate
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate != -1) {
            // Input amount to convert
            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            // Perform currency conversion
            double convertedAmount = amount * exchangeRate;

            // Display result
            System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);
        } else {
            System.out.println("Invalid currency selection");
        }

        scanner.close();
    }

    // Method to fetch exchange rate from the predefined map
    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        if (exchangeRates.containsKey(baseCurrency) && exchangeRates.containsKey(targetCurrency)) {
            double baseRate = exchangeRates.get(baseCurrency);
            double targetRate = exchangeRates.get(targetCurrency);
            return targetRate / baseRate;
        } else {
            return -1; // Indicates invalid currency selection
        }
    }
}
