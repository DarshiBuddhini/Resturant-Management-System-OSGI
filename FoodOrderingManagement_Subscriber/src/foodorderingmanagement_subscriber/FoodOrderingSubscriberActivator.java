package foodorderingmanagement_subscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import foodorderingmanagement_publisher.FoodOrderingService;

public class FoodOrderingSubscriberActivator implements BundleActivator {

Scanner scanner = new Scanner(System.in);
	
	private ServiceReference<FoodOrderingService> billingServiceRef;
    private FoodOrderingService billingService;
    public double discountPercentage = 15.00;


	public void start(BundleContext context) throws Exception {
		String operation = "start", cutomerType;
		double discount;
		
		billingServiceRef = context.getServiceReference(FoodOrderingService.class);
		billingService = context.getService(billingServiceRef);
		
		System.out.println("");
		System.out.println("------------------------------------------------");
		System.out.println("|      Welcome to Food Ordering Management     |");
		System.out.println("------------------------------------------------");
		
		System.out.println("");
		cutomerType = "";
		while (!cutomerType.equalsIgnoreCase("L") && !cutomerType.equalsIgnoreCase("N")) {
		    System.out.print("Enter Customer Type(Loyalty-L / Normal-N) : ");
		    cutomerType = scanner.next();

		    if (!cutomerType.equalsIgnoreCase("L") && !cutomerType.equalsIgnoreCase("N")) {
		        System.out.println("Invalid input. Please enter 'L' for Loyalty or 'N' for Normal. Try again.");
		    }
		}
		
		while(!operation.equalsIgnoreCase("stop")) {
			
			System.out.println("\nPlease select a task");
			System.out.println("");
			System.out.println("a. Add Food Item.");
			System.out.println("b. Remove Food Item.");
			System.out.println("c. Get Total.");
			System.out.println("d. Get Discount.");
			System.out.println("e. Get Total Bill.");
			System.out.println("f. Print Bill.");
			System.out.println("g. Type \"Stop\" to End.");
			System.out.println("");
			
			System.out.print("Enter the Task : ");
			operation = scanner.next();
			System.out.println("");
			
			if(operation.equalsIgnoreCase("a")) {
				
				System.out.println("\nMenu:");
				System.out.println("------------------------------------------------------------");
				System.out.println("|   Code   |      Item                  |    Price (LKR)   |");
				System.out.println("------------------------------------------------------------");
				System.out.println("|   E001   | Chicken Pizza            |      800.00        |");
				System.out.println("|   E002   | Chicken Fried Rice       |      600.00        |");
				System.out.println("|   E003   | Veg Fried Rice           |      500.00        |");
				System.out.println("|   E004   | Chicken Kottu            |      700.00        |");
				System.out.println("|   E005   | Veg Kottu                |      500.00        |");
				System.out.println("|   E006   | Chocolate Milk Shake     |      300.00        |");
				System.out.println("------------------------------------------------------------");
				System.out.println("  * Prices are in Sri Lankan Rupees (LKR)");
				System.out.println("  * All prices are inclusive of taxes");
				System.out.println("  * Enjoy your meal!");
				System.out.println("------------------------------------------------------------");
				System.out.println("");

			    System.out.print("Enter Product Code: ");
			    String productName = scanner.next();
			    
			    System.out.print("Enter quantity: ");
			    int quantity = scanner.nextInt();

			    billingService.addPrice(productName, quantity);
			    
			}else if(operation.equalsIgnoreCase("b")) {
				
				System.out.print("Enter product: ");
			    String productName = scanner.next();
			    
			    System.out.print("Enter quantity: ");
			    int quantity = scanner.nextInt();

			    billingService.removePrice(productName, quantity);
			    
			}else if(operation.equalsIgnoreCase("c")) {
				
				System.out.println("Total : " + billingService.getTotal());
				
				
			}else if(operation.equalsIgnoreCase("d")) {
				if(cutomerType.equalsIgnoreCase("L")) {
					discount = billingService.clacDiscount(discountPercentage);
				}else {
					discount = 0;
				}
				
				System.out.println("Discount : " + discount);
				
			}else if(operation.equalsIgnoreCase("e")) {
				
				if(cutomerType.equalsIgnoreCase("L")) {
					discount = billingService.clacDiscount(discountPercentage);
				}else {
					discount = 0;
				}
				
				System.out.println("");
				billingService.getTotalBill(discount);
				
			}else if(operation.equalsIgnoreCase("f")) {
				
				if(cutomerType.equalsIgnoreCase("L")) {
					billingService.printBill(discountPercentage);
				}else {
					billingService.printBill(0);
				}
					
			}else {
				
				break;
				
			}
		}
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Food Ordering Management....");
		context.ungetService(billingServiceRef);
	}

}
