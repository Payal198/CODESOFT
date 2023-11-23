package CurrencyConverter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    private String baseCurrency;
    private String targetCurrency;
    private double exchangeRate;

    public CurrencyConverter(String baseCurrency, String targetCurrency) {
        this.baseCurrency = baseCurrency.toUpperCase();
        this.targetCurrency = targetCurrency.toUpperCase();
        fetchExchangeRate();
    }

    private void fetchExchangeRate() {
        try {
            URL url = new URL(API_URL + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder response = new StringBuilder();

                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                // Parse exchange rate directly from the response
                String ratePrefix = "\"" + targetCurrency + "\":";
                int rateIndex = response.indexOf(ratePrefix);
                int endIndex = response.indexOf(",", rateIndex);
                exchangeRate = Double.parseDouble(response.substring(rateIndex + ratePrefix.length(), endIndex));
            } else {
                System.out.println("Failed to fetch exchange rates. Please try again later.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double convert(double amount) {
        return amount * exchangeRate;
    }

    public void displayResult(double amount) {
        System.out.println("Converted amount: " + convert(amount) + " " + targetCurrency);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the base currency code: ");
        String baseCurrency = scanner.nextLine();

        System.out.print("Enter the target currency code: ");
        String targetCurrency = scanner.nextLine();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        CurrencyConverter converter = new CurrencyConverter(baseCurrency, targetCurrency);
        converter.displayResult(amount);

        scanner.close();
    }
}
