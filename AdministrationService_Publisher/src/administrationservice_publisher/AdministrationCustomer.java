package administrationservice_publisher;

public class AdministrationCustomer {
	
	private int customerId;
	private String customerName;
	private String customerContact;
	private String customerEmail;
	
	public AdministrationCustomer(int customerId, String customerName, String customerContact, String customerEmail) {
		
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.customerEmail = customerEmail;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}
