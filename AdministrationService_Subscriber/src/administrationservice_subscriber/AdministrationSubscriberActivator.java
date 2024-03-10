package administrationservice_subscriber;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import administrationservice_publisher.AdministrationService;


public class AdministrationSubscriberActivator implements BundleActivator {

	ServiceReference<?> serviceReference;
	Scanner scanner = new Scanner(System.in);

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("\nStart Administration Service Management App...");
		serviceReference = bundleContext.getServiceReference(AdministrationService.class.getName());
		AdministrationService servicePublish = (AdministrationService) bundleContext.getService(serviceReference);
		
		
		// Outer loop for managing different types of entities
        while (true) {
            System.out.println("\n---------------------------------------------");
            System.out.println("| Welcome to Administration Services System |");
            System.out.println("---------------------------------------------");
            System.out.println("Select the type of entity you want to manage:");
            System.out.println("1. Employee");
            System.out.println("2. Customer");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Employee management
                employeeManagement(servicePublish);
            } else if (choice == 2) {
                // Customer management
                customerManagement(servicePublish);
            } else if (choice == 3) {
                // Exit the program
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("-----------------------------------------");
        System.out.println("|             Thank you!!!              |");
        System.out.println("-----------------------------------------");

    }
	
	
	private void employeeManagement(AdministrationService servicePublish) {
        String action;
        int id;
        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("| Welcome to Employee Management System |");
            System.out.println("-----------------------------------------");
            System.out.println("a. To add an employee enter 'ADD'");
            System.out.println("b. To remove an employee enter 'REMOVE'");
            System.out.println("c. To view all employees enter 'VIEW'");
            System.out.println("d. To view an employee enter 'VIEWEMPLOYEE'");
            System.out.println("e. To export to a CSV file enter 'EXPORT'");
            System.out.println("e. To import from a CSV file enter 'IMPORT'");
            System.out.println("f. To exit enter 'EXIT'" + "\n");

            // enter your choice
            System.out.print("Enter: ");
            action = scanner.next();
            System.out.println();

            switch (action.toLowerCase()) {
                case "add":
                    // Add employee
                    System.out.print("Enter the id of the employee: ");
                    id = scanner.nextInt();
                    if (servicePublish.contains(id)) {
                        System.out.println("Employee already exists! " + "\n");
                    } else {
                        System.out.print("Enter the name of the employee: ");
                        String name = scanner.next();
                        System.out.print("Enter the contact number of the employee: ");
                        String contact = scanner.next();

                        while (contact.length() != 10) {
                            System.out.print("Incorrect contact number. Please enter again: ");
                            contact = scanner.next();
                        }

                        System.out.print("Enter the email address of the customer: ");
						String employeeEmail = scanner.next();
						String emailPattern = "^[a-zA-Z0-9]+@(?:gmail\\.com)$";
						
						while (!checkEmail(employeeEmail, emailPattern)) {
					            System.out.print("Incorrect email address. Please enter again: ");
					            employeeEmail = scanner.next();
					    }
						System.out.print("Enter the category of the employee: ");
	                    String category = scanner.next();
	                    
	                    System.out.print("Enter the basic salary of the employee: ");
                        double salary = scanner.nextDouble();
	                     

                        System.out.println();

                        servicePublish.addEmployees(id, name, contact, employeeEmail, category, salary);
                        System.out.println("Employee is Added" + "\n");
                    }
                    break;

                case "remove":
                    // Remove employee
                    System.out.print("Enter the id of the employee you want to remove: ");
                    id = scanner.nextInt();
                    if (!servicePublish.contains(id)) {
                        System.out.println("Employee does not exist" + "\n");
                    } else {
                        if (servicePublish.removeEmployees(id)) {
                            System.out.println("Employee is removed" + "\n");
                        }
                    }
                    break;

                case "view":
                    // View all employees
                    servicePublish.viewEmployees();
                    break;

                case "export":
                    // Export to CSV
                    System.out.print("Enter the file path: ");
                    String path = scanner.next();
                    servicePublish.importToCsv(path);
                    break;
                    
                case "import":
                    // Import from CSV
                	System.out.print("Enter the file path: ");
                    String filePath = scanner.next();
                    servicePublish.importEmployeesFromCSV(filePath);
                    break;

                case "viewemployee":
                    // View a specific employee
                    System.out.print("Enter the id of the employee: ");
                    int eid = scanner.nextInt();
                    servicePublish.getAnEmployee(eid);
                    break;

                case "exit":
                    // Exit the employee management
                    break;

                default:
                    System.out.println("Please enter a valid input! ");
            }

        } while (!action.equalsIgnoreCase("exit"));
    }
	
	
	 private void customerManagement(AdministrationService servicePublish) {
		 String action;
	     
	     
	        do {
	            System.out.println("\n----------------------------------------");
	            System.out.println("| Welcome to Customer Management System |");
	            System.out.println("-----------------------------------------");
	            System.out.println("a. To add an customer enter 'ADD'");
	            System.out.println("b. To remove an customer enter 'REMOVE'");
	            System.out.println("c. To view all customers enter 'VIEW'");
	            System.out.println("d. To view a customer enter 'VIEWCUSTOMER'");
	            System.out.println("e. To export to a CSV file enter 'EXPORT'");
	            System.out.println("f. To exit enter 'EXIT'" + "\n");

	            // enter your choice
	            System.out.print("Enter: ");
	            action = scanner.next();
	            System.out.println();

	            switch (action.toLowerCase()) {
	                case "add":
	                    // Add customer
	                	System.out.print("Enter the id of the customer: ");
						int customerId = scanner.nextInt();
						
						if(servicePublish.containsCustomer(customerId)) {
							System.out.println("Customer already exists! "+"\n");
							
						}else {
							
							System.out.print("Enter the name of the customer: ");
							String customerName = scanner.next();
							System.out.print("Enter the contact number of the customer: ");
							String customerContact = scanner.next();
						    
						    while(customerContact.length()!=10) {
						    	System.out.print("Incorrect contact number.Please enter again: ");
						    	customerContact = scanner.next();
						    }
							System.out.print("Enter the email address of the customer: ");
							String customerEmail = scanner.next();
							String emailPattern = "^[a-zA-Z0-9]+@(?:gmail\\.com)$";
							
							 while (!checkEmail(customerEmail, emailPattern)) {
						            System.out.print("Incorrect email address. Please enter again: ");
						            customerEmail = scanner.next();
						        }
						  
							System.out.println();
						    
							servicePublish.addCustomers(customerId, customerName, customerContact, customerEmail);
							System.out.println("Customer is Added"+"\n");
	                    }
	                    break;

	                case "remove":
	                    // Remove customer
	                	System.out.print("Enter the id of the customer you want to remove: ");
						int removeCustomerId = scanner.nextInt();
						
						if(!servicePublish.containsCustomer(removeCustomerId)) {
							System.out.println("Customer does not exist"+"\n");
							
						}else {
							
							if(servicePublish.removeCustomers(removeCustomerId)) {
								System.out.println("Customer is removed"+"\n");
							}
						}		                    
						break;

	                case "view":
	                    // View all customers
	                	servicePublish.viewCustomers();
	                    break;

	                case "export":
	                    // Export to CSV
	                	System.out.print("Enter the file path: ");
						String path = scanner.next();
						
						servicePublish.importCustomerToCsv(path);
	                    break;

	                case "viewcustomer":
	                    // View a specific customer
	                	System.out.print("Enter the id of the customer: ");
						int cid = scanner.nextInt();
						servicePublish.getAnCustomer(cid);
	                    break;

	                case "exit":
	                    // Exit the customer management
	                    break;

	                default:
	                    System.out.println("Please enter a valid input! ");
	            }

	        } while (!action.equalsIgnoreCase("exit"));
	    }
	 
	 public static boolean checkEmail(String email, String emailPattern) {
			 Pattern pattern = Pattern.compile(emailPattern);
		     Matcher matcher = pattern.matcher(email);
		     return matcher.matches();
	    }
		


	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Stop Employee Service App...");
		bundleContext.ungetService(serviceReference);
	}

}
