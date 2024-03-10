package administrationservice_publisher;

public interface AdministrationService {
	public String ServicePublisher();
	
	public void viewCustomers();
	public void addCustomers(int customerId, String customerName, String customerContact, String customerEmail);
	public boolean removeCustomers(int customerId);
	public boolean containsCustomer(int customerId);
	public void getAnCustomer(int customerId);
	public void importCustomerToCsv(String cusPath);
	
	public void viewEmployees();
	public void addEmployees(int id, String name, String contact, String employeeEmail, String category, double salary);
	public boolean removeEmployees(int id);
	public boolean contains(int id);
	public void getAnEmployee(int id);
	public void importToCsv(String path);
	public void importEmployeesFromCSV(String filePath);
}
