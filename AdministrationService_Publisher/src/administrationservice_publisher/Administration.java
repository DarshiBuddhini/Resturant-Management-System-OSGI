package administrationservice_publisher;

public class Administration {
	
	private int id;
	private String name;
	private String contact;
	private String employeeEmail;
	private String category;
	private double salary;
	
	public Administration(int id, String name, String contact, String employeeEmail, String category, double salary) {
		
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.employeeEmail = employeeEmail;
		this.category = category;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
	public String getEmployeeCategory() {
		return category;
	}

	public void setEmployeeCategory(String category) {
		this.category = category;
	}
	
	public double getEmployeeSalary() {
		return salary;
	}

	public void setEmployeeSalary(double salary) {
		this.salary = salary;
	}
	
}
