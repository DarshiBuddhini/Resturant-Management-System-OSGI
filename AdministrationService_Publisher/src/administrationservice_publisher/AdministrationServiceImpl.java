package administrationservice_publisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdministrationServiceImpl implements AdministrationService{
	
	//Manage employees

	HashMap<Integer,Administration> employee = new HashMap<>();

	@Override
	public String ServicePublisher() {
		return "Execute the publish service of ServicePublish";
	}

	
	@Override
	public void viewEmployees() {
		
		System.out.printf("| %-18s | %-18s | %-18s | %-18s | %-18s|%-18s|%n", "Employee ID", "Employee Name", "Contact Number","Email", "Category", "Salary" );
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		
		for(Map.Entry<Integer, Administration> entry:employee.entrySet()) {
			System.out.printf("| %-18s | %-18s | %-18s | %-18s | %-18s|%-18s|%n", entry.getValue().getId(), entry.getValue().getName(),  entry.getValue().getContact(),entry.getValue().getEmployeeEmail(),entry.getValue().getEmployeeCategory(),entry.getValue().getEmployeeSalary());	
		
			System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		}
		
		System.out.println();
	}

	@Override
	public void addEmployees(int id, String name, String contact, String employeeEmail, String category, double salary) {
		Administration emp = new Administration(id, name,  contact,  employeeEmail, category, salary);
		employee.put(id, emp);
	}

	@Override
	public boolean removeEmployees(int id) {
		
		if(!employee.containsKey(id)) {
			return false;
		}else {
			employee.remove(id);
			return true;
		}	
		
	}

	@Override
	public boolean contains(int id) {
		
		if(employee.containsKey(id)) {
			return true;
		}
		return false;
		
	}

	@Override
	public void getAnEmployee(int id) {
		
		if(!employee.containsKey(id)) {
			System.out.println("Employee does not exist");
			
		}else {
			
			System.out.printf("| %-18s | %-18s | %-18s | %-18s | %-18s |%-18s |%n", "Employee ID", "Employee Name", "Contact Number", "Email", "Category", "Salary");
			
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			
			for(Map.Entry<Integer, Administration> entry:employee.entrySet()) {
				if(entry.getKey()==id) {
					System.out.printf("| %-18s | %-18s | %-18s | %-18s | %-18s |%-18s |%n", entry.getValue().getId(), entry.getValue().getName(),  entry.getValue().getContact(),entry.getValue().getEmployeeEmail(),entry.getValue().getEmployeeCategory(),entry.getValue().getEmployeeSalary());	
				}
			}
			System.out.println();
		}
	}
	
	@Override
	public void importToCsv(String path) {
	   
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String fileName = "Employees_" + dateTimeFormat.format(new Date()) + ".csv";

	    String filePath = path + fileName;

	    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

	        writer.println("Employee ID,Employee Name,Contact Number,Email,Category,Salary");

	        for (Integer key : employee.keySet()) {
	        	String name = employee.get(key).getName();
                String contact = employee.get(key).getContact();
                String employeeEmail = employee.get(key).getEmployeeEmail();
                String category = employee.get(key).getEmployeeCategory();
                double salary = employee.get(key).getEmployeeSalary();
                
	            writer.printf("%d,%s,%s,%s,%s,%.2f\n", key, name,contact,employeeEmail,category,salary);
	        }

	        System.out.println("***Exported successfully at: " + filePath);
	    } catch (IOException e) {
	        System.out.println("***Error writing to CSV file: " + e.getMessage());
	    }
	}
	
	
	@Override
	public void importEmployeesFromCSV(String filePath) {

	    File file = new File(filePath);

	    try (Scanner scanner = new Scanner(file)) {
	        scanner.nextLine();
	        
	        Scanner userScanner = new Scanner(System.in);
	        System.out.print("Enter employee category : ");
            String categoryFilter = userScanner.nextLine().trim();
            System.out.println("");

	        System.out.format("| %-15s | %-20s | %-15s | %-25s | %-10s |\n",
	                "Employee ID", "Name", "Category", "Email", "Salary");
	        
	        System.out.println("-----------------------------------------------------------------------------------------------------");

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] fields = line.split(",");
	            
	            // Parse the data from the CSV file
	            int id = Integer.parseInt(fields[0]);
	            String name = fields[1];
	            String email = fields[3];
	            String category = fields[4];
	            double salary = Double.parseDouble(fields[5]);

	            if (category.equalsIgnoreCase(categoryFilter)) {
                    // Print the imported details in a table if category matches
                    System.out.format("| %-15d | %-20s | %-15s | %-25s | %-10.2f |\n",
                            id, name, category, email, salary);
                }
	        }

	        userScanner.close();
	        System.out.println("\n\n***Employee details imported successfully from " + filePath);

	    } catch (FileNotFoundException e) {
	        System.out.println("***Error opening file: " + e.getMessage());
	    }
	}

	
	
	
	//Manage customer
	
	HashMap<Integer,AdministrationCustomer> customer = new HashMap<>();

	@Override
	public void viewCustomers() {
		System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", "Customer ID", "Customer Name", "Customer Number","Age" );
		
		System.out.println("----------------------------------------------------------------------------------------");
		
		for(Map.Entry<Integer, AdministrationCustomer> entry:customer.entrySet()) {
			System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", entry.getValue().getCustomerId(), entry.getValue().getCustomerName(),  entry.getValue().getCustomerContact(),entry.getValue().getCustomerEmail());	
		
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		System.out.println();
		
	}

	@Override
	public void addCustomers(int customerId, String customerName, String customerContact, String customerEmail) {
		AdministrationCustomer cus = new AdministrationCustomer(customerId, customerName,  customerContact,  customerEmail);
		customer.put(customerId, cus);
		
	}

	@Override
	public boolean removeCustomers(int customerId) {
		if(!customer.containsKey(customerId)) {
			return false;
		}else {
			customer.remove(customerId);
			return true;
		}
	}

	@Override
	public boolean containsCustomer(int customerId) {
		if(customer.containsKey(customerId)) {
			return true;
		}
		return false;
	}

	@Override
	public void getAnCustomer(int customerId) {
		if(!customer.containsKey(customerId)) {
			System.out.println("Customer does not exist");
			
		}else {
			
			System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", "Customer ID", "Customer Name", "Contact Number","Age" );
			
			System.out.println("----------------------------------------------------------------------------------------");
			
			for(Map.Entry<Integer, AdministrationCustomer> entry:customer.entrySet()) {
				if(entry.getKey()==customerId) {
					System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", entry.getValue().getCustomerId(), entry.getValue().getCustomerName(),  entry.getValue().getCustomerContact(),entry.getValue().getCustomerEmail());	
				}
			}
			System.out.println();
		}
		
	}

	@Override
	public void importCustomerToCsv(String cusPath) {
		
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String fileName = "Customers_" + dateTimeFormat.format(new Date()) + ".csv";

	    String filePath = cusPath + fileName;

	    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

	        writer.println("Customer ID,Customer Name,Contact Number,Email");

	        for (Integer key : customer.keySet()) {
	        	String name = customer.get(key).getCustomerName();
                String contact = customer.get(key).getCustomerContact();
                String customerEmail = customer.get(key).getCustomerEmail();
                
	            writer.printf("%d,%s,%s,%s\n", key, name, contact, customerEmail);
	        }

	        System.out.println("***Exported successfully at: " + filePath);
	    } catch (IOException e) {
	        System.out.println("***Error writing to CSV file: " + e.getMessage());
	    }
		
	}

	
	
	
	
}
