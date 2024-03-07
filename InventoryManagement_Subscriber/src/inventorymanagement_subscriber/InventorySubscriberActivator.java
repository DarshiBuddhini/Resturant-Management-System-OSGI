package inventorymanagement_subscriber;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import inventorymanagement_publisher.Product;
import inventorymanagement_publisher.InventoryService;


public class InventorySubscriberActivator implements BundleActivator {

	Scanner scanner = new Scanner(System.in);
	
	private ServiceReference<InventoryService> inventoryServiceRef;
    private InventoryService inventoryService;

	public void start(BundleContext context) throws Exception {
		
		String operation = "start";
		
		inventoryServiceRef = context.getServiceReference(InventoryService.class);
		inventoryService = context.getService(inventoryServiceRef);
		
		System.out.println("\n----------------------------------------------");
		System.out.println("|   Welcome to Inventory Management System   |");
		System.out.println("----------------------------------------------");
		
		while(!operation.equalsIgnoreCase("stop")) {
			
			System.out.println("\nWhat would you like to do?\n");
			System.out.println("a. Add a product.");
			System.out.println("b. Dispatch a product.");
			System.out.println("c. Remove a product.");
			System.out.println("d. Print the Inventory Details.");
			System.out.println("e. Get product quantity.");
			System.out.println("f. Export to csv.");
			System.out.println("g. Import from a csv.");
			System.out.println("h. View out of inventory Products.");
			System.out.println("i. Search for a product by name.");
			System.out.println("j. Type \"stop\" to end.");
			System.out.println();
			
			System.out.print("Enter your choice: ");
			operation = scanner.next();
			
			if(operation.equalsIgnoreCase("a")) {
				
				double unitPrice = 0;
				int rLevel = 0;
				
			    System.out.print("\nEnter product name: ");
			    String productName = scanner.next();
			    
			    System.out.print("Enter quantity: ");
			    int quantity = scanner.nextInt();
			    
			    Product product = inventoryService.getProductByName(productName);
			    
			    if(product == null) {
			    	System.out.print("Enter Unit Price: ");
			    	unitPrice = scanner.nextDouble();
			    	
			    	System.out.print("Enter Reorder Level: ");
			    	rLevel = scanner.nextInt();
			    }

			    inventoryService.addProduct(productName, quantity, rLevel, unitPrice);
			    
			}else if(operation.equalsIgnoreCase("b")) {
				
				System.out.print("Enter product name: ");
			    String productName = scanner.next();
			    
			    System.out.print("Enter quantity: ");
			    int quantity = scanner.nextInt();

			    inventoryService.dispatchProduct(productName, quantity);
			    
			}else if(operation.equalsIgnoreCase("c")) {
				
				System.out.print("Enter product name: ");
			    String productName = scanner.next();
			    
			    inventoryService.deleteProduct(productName);
			    
			}else if(operation.equalsIgnoreCase("d")) {
				
				inventoryService.getAllData();
			       
			}else if(operation.equalsIgnoreCase("e")) {
				
				System.out.print("Enter product name: ");
			    String productName = scanner.next();
			    
			    System.out.println("");
			    System.out.println(productName + " : " + inventoryService.getQuantity(productName));
			       
			}else if(operation.equalsIgnoreCase("f")) {
				
				System.out.print("Enter the CSV file path : ");
				String filePath = scanner.next();
				
			    inventoryService.exportToCSV(filePath);  
			    
			}else if(operation.equalsIgnoreCase("g")) {
				
				System.out.print("Enter the CSV file path : ");
				String filePath = scanner.next();
				
			    inventoryService.importInventory(filePath); 
			    
			}else if(operation.equalsIgnoreCase("h")) {
				
			    inventoryService.reorderProducts();; 
			    
			}else if (operation.equalsIgnoreCase("i")) {
				
				  System.out.println("\nSearching for a Product\n");
				    System.out.print("Enter product name: ");
				    String productName = scanner.next();

				    List<Product> searchResults = inventoryService.searchProductByName(productName);

				    if (searchResults.isEmpty()) {
				        System.out.println("No products found with the given name.");
				    } else {
				        System.out.println("\nSearch Results:\n");
				        for (Product product : searchResults) {
				            System.out.println(product.toString()); // Explicitly call toString() method
				        }
				    }
				    
			}else {
				break;
			}
		}
		
		System.out.println("\n------------------------------------------");
		System.out.println("|          Thank you for using our        |");
		System.out.println("|        Inventory Management System      |");
		System.out.println("------------------------------------------");
		inventoryService.printInventoryToCSV(); 
		

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Inventory App....");
		inventoryService.printInventoryToCSV(); 
		context.ungetService(inventoryServiceRef);
	}

}
