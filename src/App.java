import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        
      Scanner scanner = new Scanner(System.in);
       List<Order> orders = OrderReader.readOrdersFromFile("orders.txt");

       if (orders.isEmpty()) {
        System.out.println("No orders found.");
        scanner.close();
        return;
    }


       for(Order order : orders){
        System.out.println(order);
       }
       while (true) {
        System.out.println("\nSelect an option:");
        System.out.println("1. Divide orders by payment method");
        System.out.println("2. Group orders by customers");
        System.out.println("3. Calculate total sold units per product");
        System.out.println("4. Sort orders by total value");
        System.out.println("5. List all unique products");
        System.out.println("6. Find order with highest total value");
        System.out.println("7. Exit");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Choose method:");
            System.out.println("1. Use Stream API");
            System.out.println("2. Use basic loops");
            int methodChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (methodChoice) {
                case 1:
                    divideOrdersWithStreamAPI(orders);
                    break;
                case 2:
                    divideOrdersWithBasicLoops(orders);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } else if (choice == 2) {
            System.out.println("Choose method:");
            System.out.println("1. Use Stream API");
            System.out.println("2. Use basic loops");
            int methodChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (methodChoice) {
                case 1:
                    groupOrdersByCustomersWithStreamAPI(orders);
                    break;
                case 2:
                    groupOrdersByCustomersWithBasicLoops(orders);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } else if (choice == 3) {
            System.out.println("Choose method:");
            System.out.println("1. Use Stream API");
            System.out.println("2. Use basic loops");
            int methodChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (methodChoice) {
                case 1:
                    calculateTotalUnitsPerProductWithStreamAPI(orders);
                    break;
                case 2:
                    calculateTotalUnitsPerProductWithBasicLoops(orders);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } else if (choice == 4) {
            System.out.println("Choose method:");
            System.out.println("1. Use Stream API");
            System.out.println("2. Use basic loops");
            int methodChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("Sort order:");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            int sortOrder = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (methodChoice) {
                case 1:
                    sortOrdersByTotalValueWithStreamAPI(orders, sortOrder);
                    break;
                case 2:
                    sortOrdersByTotalValueWithBasicLoops(orders, sortOrder);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } else if (choice == 5) {
            System.out.println("Choose method:");
            System.out.println("1. Use Stream API");
            System.out.println("2. Use basic loops");
            int methodChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (methodChoice) {
                case 1:
                    listUniqueProductsWithStreamAPI(orders);
                    break;
                case 2:
                    listUniqueProductsWithBasicLoops(orders);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } else if (choice == 6) {
            System.out.println("\nChoose method:");
            System.out.println("1. Use Stream API");
            System.out.println("2. Use basic loops");
            int methodChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (methodChoice) {
                case 1:
                    findOrderWithHighestTotalValueWithStreamAPI(orders);
                    break;
                case 2:
                    findOrderWithHighestTotalValueWithBasicLoops(orders);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } else if (choice == 7) {
            System.out.println("Exiting...");
            break;
        } else {
            System.out.println("Invalid choice, please try again.");
        }
    }

    scanner.close(); 
}

  private static void divideOrdersWithStreamAPI(List<Order> orders) {
    Map<Boolean, List<Order>> partitionedOrders = orders.stream()
        .collect(Collectors.partitioningBy(order -> 
            Optional.ofNullable(order.getPaymentMethod())
                    .map(method -> method.equalsIgnoreCase("card"))
                    .orElse(false)
        ));

    List<Order> cardOrders = partitionedOrders.get(true);   
    List<Order> cashOrders = partitionedOrders.get(false);  

    System.out.println("\nOrders paid by card:");
    cardOrders.forEach(System.out::println);

    System.out.println("\nOrders paid by cash:");
    cashOrders.forEach(System.out::println);
  }


  private static void divideOrdersWithBasicLoops(List<Order> orders) {
    List<Order> cardOrders = new ArrayList<>();
    List<Order> cashOrders = new ArrayList<>();

    for (Order order : orders) {
        Optional<String> paymentMethodOpt = Optional.ofNullable(order.getPaymentMethod());
        if (paymentMethodOpt.isPresent()) {
            String paymentMethod = paymentMethodOpt.get().toLowerCase();
            if (paymentMethod.equals("card")) {
                cardOrders.add(order);
            } else if (paymentMethod.equals("cash")) {
                cashOrders.add(order);
            }
        }
    }

    System.out.println("\nOrders paid by card:");
    for (Order order : cardOrders) {
        System.out.println(order);
    }

    System.out.println("\nOrders paid by cash:");
    for (Order order : cashOrders) {
        System.out.println(order);
    }
}


   private static void groupOrdersByCustomersWithStreamAPI(List<Order> orders) {
    Map<String, List<Order>> ordersByCustomer = orders.stream()
        .collect(Collectors.groupingBy(order -> 
            Optional.ofNullable(order.getCustomerName()).orElse("Unknown")
        ));

    for (Map.Entry<String, List<Order>> entry : ordersByCustomer.entrySet()) {
        System.out.println("\nCustomer: " + entry.getKey());
        entry.getValue().forEach(System.out::println);
    }
  }


  private static void groupOrdersByCustomersWithBasicLoops(List<Order> orders) {
      Map<String, List<Order>> ordersByCustomer = new HashMap<>();

      for (Order order : orders) {
          String customerName = Optional.ofNullable(order.getCustomerName()).orElse("Unknown");
          if (!ordersByCustomer.containsKey(customerName)) {
              ordersByCustomer.put(customerName, new ArrayList<>());
          }
          ordersByCustomer.get(customerName).add(order);
      }

      for (Map.Entry<String, List<Order>> entry : ordersByCustomer.entrySet()) {
          System.out.println("\nCustomer: " + entry.getKey());
          for (Order order : entry.getValue()) {
              System.out.println(order);
          }
      }
  }


  private static void calculateTotalUnitsPerProductWithStreamAPI(List<Order> orders) {
    Map<String, Integer> productTotals = orders.stream()
        .flatMap(order -> order.getProducts().stream()) 
        .collect(Collectors.groupingBy(
            product -> Optional.ofNullable(product.getProductName()).orElse("Unknown"),
            Collectors.summingInt(Product::getProductCount)
        ));

    productTotals.forEach((productName, totalUnits) -> 
        System.out.println("\n" + productName + " - " + totalUnits + " pieces" ));
  }


  private static void calculateTotalUnitsPerProductWithBasicLoops(List<Order> orders) {
    Map<String, Integer> productTotals = new HashMap<>();

    for (Order order : orders) {
        for (Product product : order.getProducts()) {
            String productName = Optional.ofNullable(product.getProductName()).orElse("Unknown");
            int count = productTotals.getOrDefault(productName, 0);
            productTotals.put(productName, count + product.getProductCount());
        }
    }

    for (Map.Entry<String, Integer> entry : productTotals.entrySet()) {
        System.out.println("\n" + entry.getKey() + " - " + entry.getValue() + " pieces");
    }
  }


  private static void sortOrdersByTotalValueWithStreamAPI(List<Order> orders, int sortOrder) {
    List<Order> sortedOrders = orders.stream()
        .sorted(Comparator.comparingDouble(App::calculateTotalValue)
                .thenComparing(order -> order.getOrderNumber())) 
        .collect(Collectors.toList());

    if (sortOrder == 2) { 
        Collections.reverse(sortedOrders);
    }

    sortedOrders.forEach(order -> {
        double totalValue = calculateTotalValue(order);
        System.out.println("\n" + order + " \n Total Value: " + totalValue);
    });
  }


  private static void sortOrdersByTotalValueWithBasicLoops(List<Order> orders, int sortOrder) {
    orders.sort((o1, o2) -> {
        double total1 = calculateTotalValue(o1);
        double total2 = calculateTotalValue(o2);
        return Double.compare(total1, total2);
    });

    if (sortOrder == 2) { 
        Collections.reverse(orders);
    }

    for (Order order : orders) {
        double totalValue = calculateTotalValue(order);
        System.out.println("\n" + order + " \n Total Value: " + totalValue);
    }
  }



  private static double calculateTotalValue(Order order) {
    return order.getProducts().stream()
        .mapToDouble(product -> 
            Optional.ofNullable(product)
                    .map(p -> p.getProductPrice() * p.getProductCount())
                    .orElse(0.0)
        )
        .sum();
  }


  private static void listUniqueProductsWithStreamAPI(List<Order> orders) {
    Set<String> uniqueProducts = orders.stream()
        .flatMap(order -> order.getProducts().stream())
        .map(product -> Optional.ofNullable(product.getProductName()).orElse("Unknown"))
        .collect(Collectors.toSet());

    System.out.println("\nUnique products:");
    uniqueProducts.forEach(System.out::println);
  }


  private static void listUniqueProductsWithBasicLoops(List<Order> orders) {
    Set<String> uniqueProducts = new HashSet<>();

    for (Order order : orders) {
        for (Product product : order.getProducts()) {
            String productName = Optional.ofNullable(product.getProductName()).orElse("Unknown");
            uniqueProducts.add(productName);
        }
    }

    System.out.println("\nUnique products:");
    for (String productName : uniqueProducts) {
        System.out.println(productName);
    }
  }

  private static void findOrderWithHighestTotalValueWithStreamAPI(List<Order> orders) {

    double highestTotalValue = orders.stream()
        .mapToDouble(App::calculateTotalValue)
        .max()
        .orElse(Double.MIN_VALUE);


    List<Order> highestValueOrders = orders.stream()
        .filter(order -> calculateTotalValue(order) == highestTotalValue)
        .collect(Collectors.toList());

    if (highestValueOrders.size() > 1) {
        System.out.println("\nMultiple orders with the highest total value:");
    } else if (highestValueOrders.size() == 1) {
        System.out.println("\nOrder with the highest total value:");
    } else {
        System.out.println("No orders found.");
        return;
    }
    
    highestValueOrders.forEach(order -> {
        System.out.println(order);
        System.out.println("Total value: " + calculateTotalValue(order));
    });
  }

  private static void findOrderWithHighestTotalValueWithBasicLoops(List<Order> orders) {
    if (orders.isEmpty()) {
        System.out.println("No orders found.");
        return;
    }

    double highestValue = calculateTotalValue(orders.get(0));
    List<Order> highestValueOrders = new ArrayList<>();

    for (Order order : orders) {
        double currentValue = calculateTotalValue(order);
        if (currentValue > highestValue) {
            highestValue = currentValue;
            highestValueOrders.clear();
            highestValueOrders.add(order);
        } else if (currentValue == highestValue) {
            highestValueOrders.add(order);
        }
    }

    if (highestValueOrders.size() > 1) {
        System.out.println("\nMultiple orders with the highest total value:");
    } else if (highestValueOrders.size() == 1) {
        System.out.println("\nOrder with the highest total value:");
    } else {
        System.out.println("No orders found.");
        return;
    }

    for (Order order : highestValueOrders) {
        System.out.println(order);
        System.out.println("Total value: " + highestValue);
    }
  }


  }
