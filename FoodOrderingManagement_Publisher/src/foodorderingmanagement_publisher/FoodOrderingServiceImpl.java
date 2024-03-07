package foodorderingmanagement_publisher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FoodOrderingServiceImpl implements FoodOrderingService{
	
private final Map<String, Double> bill = new HashMap<>();
	
	private double chickenPizza = 800.00;
	private double chickenFriedRice = 600.00;
	private double vegFriedRice = 500.00;
	private double chickenKottu = 700.00;
	private double vegKottu = 500.00;
	private double chocolateMilkShake = 300.00;
	
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void addPrice(String name, int quantity) {
		
		if (name.equalsIgnoreCase("E001")) {
			bill.put(name, quantity * chickenPizza);
		}	
		else if(name.equalsIgnoreCase("E002")) {
			bill.put(name, quantity * chickenFriedRice);
		}
		else if(name.equalsIgnoreCase("E003")) {
			bill.put(name, quantity * vegFriedRice);
		}
		else if(name.equalsIgnoreCase("E004")) {
			bill.put(name, quantity * chickenKottu);
		}
		else if(name.equalsIgnoreCase("E005")) {
			bill.put(name, quantity * vegKottu);
		}
		else if(name.equalsIgnoreCase("E006")) {
			bill.put(name, quantity * chocolateMilkShake);
		}
		else {
			System.out.println("\n");
			System.out.println("Food Item not found");
			System.out.println("\n");
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
	public void removePrice(String name, int quantity) {

	    double newPrice = 0, currentPrice = 0;

	    if (name.equalsIgnoreCase("E001")) {
	        newPrice = quantity * chickenPizza;
	        currentPrice = bill.getOrDefault(name, 0.0);
	    } 
	    else if (name.equalsIgnoreCase("E002")) {
	        newPrice = quantity * chickenFriedRice;
	        currentPrice = bill.getOrDefault(name, 0.0);
	    } 
	    else if (name.equalsIgnoreCase("E003")) {
	        newPrice = quantity * vegFriedRice;
	        currentPrice = bill.getOrDefault(name, 0.0);
	    } 
	    else if (name.equalsIgnoreCase("E004")) {
	        newPrice = quantity * chickenKottu;
	        currentPrice = bill.getOrDefault(name, 0.0);
	    } 
	    else if (name.equalsIgnoreCase("E005")) {
	        newPrice = quantity * vegKottu;
	        currentPrice = bill.getOrDefault(name, 0.0);
	    } 
	    else if (name.equalsIgnoreCase("E006")) {
	        newPrice = quantity * chocolateMilkShake;
	        currentPrice = bill.getOrDefault(name, 0.0);
	    } 
	    else {
	    	System.out.println("\n");
	        System.out.println("Food Item not found");
	        System.out.println("\n");
	        return; 
	    }

	    bill.put(name, currentPrice - newPrice);

	    System.out.println("\n-------------------------");
	    System.out.println("| Deleted Successfully! |");
	    System.out.println("-------------------------");
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
	public void getTotalBill(double discount) {
	    // Get the current date and time
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String currentDateTime = dateFormat.format(new Date());

	    System.out.println("--------------------------------------------------------------");
	    System.out.println("|                            Bill                            |");
	    System.out.println("--------------------------------------------------------------");
	    System.out.printf("| %-15s | %-15s | %-10s | %-8s |\n", "ITEM CODE", "ITEM NAME", "QUANTITY", "TOTAL");

	    for (Map.Entry<String, Double> entry : bill.entrySet()) {
	        String itemName = getProductName(entry.getKey());
	        int itemQuantity = getItemQuantity(entry.getKey(), entry.getValue());
	        System.out.printf("| %-15s | %-15s | %-10s | %-8s |\n", entry.getKey(), itemName, itemQuantity, entry.getValue());
	    }

	    System.out.println("--------------------------------------------------------------");

	    double totalBeforeDiscount = getTotal();
	    double totalAfterDiscount = totalBeforeDiscount - discount;

	    System.out.printf("| %-15s | %-15s | %-10s | %-8s |\n", "Subtotal : ", "", "", totalBeforeDiscount);
	    System.out.printf("| %-15s | %-15s | %-10s | %-8s |\n", "Discount : ", "", "", discount);
	    System.out.println("--------------------------------------------------------------");
	    System.out.printf("| %-15s | %-15s | %-10s | %-8s |\n", "Total : ", "", "", totalAfterDiscount);
	    System.out.println("--------------------------------------------------------------");
	    System.out.printf("| %-15s | %-15s | %-10s | %-8s |\n", "Date : ", "", "", currentDateTime);
	    System.out.println("--------------------------------------------------------------");
	}

	
	@Override
	public String getProductName(String itemCode) {
	    switch (itemCode.toUpperCase()) {
	        case "E001":
	            return "Chicken Pizza";
	        case "E002":
	            return "Chicken Fried Rice";
	        case "E003":
	            return "Veg Fried Rice";
	        case "E004":
	            return "Chicken Kottu";
	        case "E005":
	            return "Veg Kottu";
	        case "E006":
	            return "Chocolate Milkshake";
	        default:
	            return "Unknown Product";
	    }
	}
	
	@Override
	public int getItemQuantity(String itemCode , double itemTotalPrice) {
	    switch (itemCode.toUpperCase()) {
	        case "E001":
	            return (int) (itemTotalPrice/chickenPizza);
	        case "E002":
	            return (int) (itemTotalPrice/chickenFriedRice);
	        case "E003":
	            return (int) (itemTotalPrice/vegFriedRice);
	        case "E004":
	            return (int) (itemTotalPrice/chickenKottu);
	        case "E005":
	            return (int) (itemTotalPrice/vegKottu);
	        case "E006":
	            return (int) (itemTotalPrice/chocolateMilkShake);
	        default:
	            return 0;
	    }
	}

	@Override
	public double clacDiscount(double percentage) {
		return (percentage * getTotal()) / 100.0;
	}

	@Override
	public void printBill(double percentage) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Enter the folder path to save the bill (e.g., C:\\Users\\ASUS\\Desktop\\): ");
	    String folderPath = scanner.nextLine();

	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String fileName = "Bill_" + dateTimeFormat.format(new Date()) + ".csv";

	    String filePath = folderPath + fileName;

	    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

	        writer.println("ITEM CODE,ITEM NAME,QUANTITY,TOTAL(LKR)");

	        for (Map.Entry<String, Double> entry : bill.entrySet()) {
	            String itemName = getProductName(entry.getKey());
	            int itemQuantity = getItemQuantity(entry.getKey(), entry.getValue());
	            writer.printf("%s,%s,%d,%.2f\n", entry.getKey(), itemName, itemQuantity, entry.getValue());
	        }

	        double totalBeforeDiscount = getTotal();
	        double totalAfterDiscount = totalBeforeDiscount - clacDiscount(percentage);

	        writer.printf("Subtotal, , ,%.2f\n", totalBeforeDiscount);
	        writer.printf("Discount, , ,%.2f\n", clacDiscount(percentage));
	        writer.printf("Total, , ,%.2f\n", totalAfterDiscount);

	        System.out.println("Bill created successfully at: " + filePath);
	    } catch (IOException e) {
	        System.out.println("Error writing to CSV file: " + e.getMessage());
	    }
	}
	
}
