package foodorderingmanagement_publisher;

class MenuItem {
    String itemCode;
    String itemName;
    double itemPrice;

    public MenuItem(String itemCode, String itemName, double itemPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}