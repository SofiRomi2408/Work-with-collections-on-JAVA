import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderReader {

    private static final Pattern PRODUCT_PATTERN = Pattern.compile("\\[(.*?),(\\d+),(\\d+\\.\\d{2})\\]");
    private static final Pattern ORDER_PATTERN = Pattern.compile("^(\\d+),\\s*([^,]+),\\s*(.*),\\s*(cash|card)$");

    public static List<Order> readOrdersFromFile(String fileName) {
        Map<String, Order> orderMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher orderMatcher = ORDER_PATTERN.matcher(line);
                if (orderMatcher.matches()) {
                    String orderNumber = orderMatcher.group(1).trim();
                    String customerName = orderMatcher.group(2).trim();
                    String productsPart = orderMatcher.group(3).trim();
                    String paymentMethod = orderMatcher.group(4).trim().toLowerCase();

                    
                    if (!paymentMethod.equals("cash") && !paymentMethod.equals("card")) {
                        System.err.println("Invalid payment method: " + paymentMethod);
                        continue; 
                    }

                    List<Product> products = new ArrayList<>();
                    Matcher productMatcher = PRODUCT_PATTERN.matcher(productsPart);
                    while (productMatcher.find()) {
                        String productName = productMatcher.group(1).trim();
                        int productCount = Integer.parseInt(productMatcher.group(2).trim());
                        double productPrice = Double.parseDouble(productMatcher.group(3).trim());
                        products.add(new Product(productName, productCount, productPrice));
                    }

                    if (products.isEmpty()) {
                        System.err.println("No valid products found in line: " + line);
                        continue; 
                    }

                    Order order = new Order(orderNumber, customerName, products, paymentMethod);
                    orderMap.put(orderNumber, order);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
        return new ArrayList<>(orderMap.values());
    }
}
