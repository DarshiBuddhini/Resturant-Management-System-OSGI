package foodorderingmanagement_publisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FoodOrderingServiceImpl implements FoodOrderingService{
	
private final Map<String, Double> bill = new HashMap<>();

    private MenuItem[] menuItems = {
        new MenuItem("E001", "Chicken Pizza", 800.00),
        new MenuItem("E002", "Chicken Fried Rice", 600.00),
        new MenuItem("E003", "Veg Fried Rice", 500.00),
        new MenuItem("E004", "Chicken Kottu", 700.00),
        new MenuItem("E005", "Veg Kottu", 500.00),
        new MenuItem("E006", "Chocolate Milkshake", 300.00)
    };
	
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void addPrice(String code, int quantity) {
	    boolean itemFound = false;

	    for (MenuItem menuItem : menuItems) {
	        if (code.equalsIgnoreCase(menuItem.itemCode)) {
	            double totalPrice = quantity * menuItem.itemPrice;

	            if (bill.containsKey(menuItem.itemCode)) {
	                double existingTotalPrice = bill.get(menuItem.itemCode);
	                bill.put(menuItem.itemCode, existingTotalPrice + totalPrice);
	            } else {
	                bill.put(menuItem.itemCode, totalPrice);
	            }

	            itemFound = true;
	            break;
	        }
	    }

	    if (!itemFound) {
	        System.out.println("\nFood Item not found\n");
	        return;
	    }

	    System.out.println("\n-----------------------");
	    System.out.println("| Added Successfully! |");
	    System.out.println("-----------------------");

	    System.out.print("Do you want to add more items? (yes/no): ");
	    String userChoice = scanner.next().toLowerCase();

	    if (userChoice.equals("yes")) {
	        System.out.print("Enter Product Code: ");
	        String productName = scanner.next();

	        System.out.print("Enter quantity: ");
	        int newQuantity = scanner.nextInt();

	        addPrice(productName, newQuantity);
	    } else if (userChoice.equals("no")) {
	        return;
	    } else {
	        System.out.println("Invalid choice. Returning to the menu.\n");
	        return;
	    }
	}


	@Override
	public void removePrice(String code, int quantity) {
	    boolean itemFound = false;

	    for (MenuItem menuItem : menuItems) {
	        if (code.toLowerCase().equals(menuItem.itemCode.toLowerCase())) {
	            double currentPrice = bill.getOrDefault(menuItem.itemCode, 0.0);
	            double newPrice = quantity * menuItem.itemPrice;

	            bill.put(menuItem.itemCode, Math.max(0, currentPrice - newPrice));

	            itemFound = true;
	            System.out.println("\n-------------------------");
	            System.out.println("| Deleted Successfully! |");
	            System.out.println("-------------------------");
	            break;
	        }
	    }

	    if (!itemFound) {
	        System.out.println("\nFood Item not found\n");
	    }
	}

	@Override
	public double getTotal() {
		double total = 0.0;
		for (Map.Entry<String, Double> entry : bill.entrySet()) {
            total +=  entry.getValue();
        }
		return total;	
	}
	
	@Override
	public void getMenu() {
	    System.out.println("\nMenu:");
	    System.out.println("------------------------------------------------------------");
	    System.out.println("|   Code   |      Item                  |    Price (LKR)   |");
	    System.out.println("------------------------------------------------------------");

	    for (MenuItem menuItem : menuItems) {
	        System.out.printf("|   %-6s | %-25s | %-16.2f |\n", menuItem.itemCode, menuItem.itemName, menuItem.itemPrice);
	    }

	    System.out.println("------------------------------------------------------------");
	    System.out.println("  * Prices are in Sri Lankan Rupees (LKR)");
	    System.out.println("  * All prices are inclusive of taxes");
	    System.out.println("  * Enjoy your meal!");
	    System.out.println("------------------------------------------------------------");
	    System.out.println("");
	}


	@Override
	public void getTotalBill(double discount) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String currentDateTime = dateFormat.format(new Date());

	    System.out.println("-------------------------------------------------------------------------------------------------------------");
	    System.out.println("|                                               Bill                                                        |");
	    System.out.println("-------------------------------------------------------------------------------------------------------------");
	    System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-8s |\n", "ITEM CODE", "ITEM NAME", "UNIT PRICE(LKR)", "QUANTITY", "TOTAL");

	    Iterator<Map.Entry<String, Double>> iterator = bill.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<String, Double> entry = iterator.next();
	        String itemCode = entry.getKey();
	        String itemName = getItemName(itemCode);
	        double itemPrice = entry.getValue();
	        double unitPrice = getItemUnitPrice(itemCode);
	        int itemQuantity = (int) (itemPrice / unitPrice);

	        if (itemQuantity > 0) {
	            System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-8s |\n", itemCode, itemName, unitPrice, itemQuantity, itemPrice);
	        } else {
	            iterator.remove();
	        }
	    }

	    System.out.println("------------------------------------------------------------------------------------------------------------");

	    double totalBeforeDiscount = getTotal();
	    double totalAfterDiscount = totalBeforeDiscount - discount;

	    System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-8s |\n", "Subtotal : ", "", "", "", totalBeforeDiscount);
	    System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-8s |\n", "Discount : ", "", "", "", discount);
	    System.out.println("------------------------------------------------------------------------------------------------------------");
	    System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-8s |\n", "Total : ", "", "", "", totalAfterDiscount);
	    System.out.println("------------------------------------------------------------------------------------------------------------");
	    System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-8s |\n", "Date : ", "", "", "", currentDateTime);
	    System.out.println("------------------------------------------------------------------------------------------------------------");
	}
	
	@Override
	public String getItemName(String code) {
	    for (MenuItem menuItem : menuItems) {
	        if (code.equalsIgnoreCase(menuItem.itemCode)) {
	            return menuItem.itemName;
	        }
	    }
	    return "N/A";
	}
	
	@Override
	public double getItemUnitPrice(String code) {
	    for (MenuItem menuItem : menuItems) {
	        if (code.equalsIgnoreCase(menuItem.itemCode)) {
	            return menuItem.itemPrice;
	        }
	    }
	    return 0.0;
	}

	@Override
	public double clacDiscount(double percentage) {
		return (percentage * getTotal()) / 100.0;
	}
	
	@Override
	public void importMenuItemsFromCSV() {
        List<MenuItem> newMenuItems = new ArrayList<>();

        System.out.print("Enter the folder path to import this CSV (e.g., C:\\Users\\ASUS\\Desktop\\menuitems.csv): ");
	    String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        	// Skip the header line
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String itemCode = parts[0].trim();
                    String itemName = parts[1].trim();
                    double itemPrice = Double.parseDouble(parts[2].trim());

                    newMenuItems.add(new MenuItem(itemCode, itemName, itemPrice));
                }
            }

            menuItems = newMenuItems.toArray(new MenuItem[0]);
            System.out.println("Menu items imported successfully!");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

	@Override
	public void printBill(double percentage) {

	    System.out.print("Enter the folder path to save the bill (e.g., C:\\Users\\ASUS\\Desktop\\): ");
	    String folderPath = scanner.nextLine();

	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String fileName = "Bill_" + dateTimeFormat.format(new Date()) + ".csv";

	    String filePath = folderPath + fileName;

	    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

	        writer.println("ITEM CODE,ITEM NAME,UNIT PRICE(LKR),QUANTITY,TOTAL(LKR)");

	        for (Map.Entry<String, Double> entry : bill.entrySet()) {
	            String itemCode = entry.getKey();
	            String itemName = getItemName(entry.getKey());
	            double unitPrice = getItemUnitPrice(entry.getKey());
	            double itemPrice = entry.getValue();
	            int itemQuantity = (int) (itemPrice / unitPrice);
	            writer.printf("%s,%s,%.2f,%d,%.2f\n", itemCode, itemName, unitPrice, itemQuantity, itemPrice);
	        }

	        double totalBeforeDiscount = getTotal();
	        double totalAfterDiscount = totalBeforeDiscount - clacDiscount(percentage);

	        writer.printf("Subtotal,,,,%.2f\n", totalBeforeDiscount);
	        writer.printf("Discount,,,,%.2f\n", clacDiscount(percentage));
	        writer.printf("Total,,,,%.2f\n", totalAfterDiscount);

	        System.out.println("Bill created successfully at: " + filePath);
	    } catch (IOException e) {
	        System.out.println("Error writing to CSV file: " + e.getMessage());
	    }
	}


	
}
