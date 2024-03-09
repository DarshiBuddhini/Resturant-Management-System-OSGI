package foodorderingmanagement_publisher;

public interface FoodOrderingService {
	public void addPrice(String name, int quantity);
	public void removePrice(String name, int quantity);
	public double getTotal();
	public double clacDiscount(double discountPar);
	public void getTotalBill(double discount);
	public String getItemName(String itemCode);
	public void printBill(double discountPar);
	void getMenu();
	double getItemUnitPrice(String code);
	void importMenuItemsFromCSV();

}
