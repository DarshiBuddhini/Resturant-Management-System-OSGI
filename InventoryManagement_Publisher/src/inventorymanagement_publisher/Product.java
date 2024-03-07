package inventorymanagement_publisher;

public class Product {
	private String name;
    private double unitPrice;
    private int quantity;
    private int reOrderLevel;
    
	public Product(String name, double unitPrice, int quantity, int reOrderLevel) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.reOrderLevel = reOrderLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReOrderLevel() {
		return reOrderLevel;
	}

	public void setReOrderLevel(int reOrderLevel) {
		this.reOrderLevel = reOrderLevel;
	}
	public String toString() {
	        return "Product [name=" + name + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", reOrderLevel=" + reOrderLevel + "]";
	    }
}
