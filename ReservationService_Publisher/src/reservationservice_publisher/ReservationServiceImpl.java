package reservationservice_publisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;


public class ReservationServiceImpl implements ReservationService {
    private Map<String, Integer> reservations;
    private boolean[] tablesAvailability;

    public ReservationServiceImpl() {
        reservations = new HashMap<>();
        tablesAvailability = new boolean[8];
        for (int i = 0; i < tablesAvailability.length; i++) {
            tablesAvailability[i] = true; // All tables are initially available
        }
    }

    @Override
    public void addReservation(String name, int quantity) {
        int availableTable = findAvailableTable(quantity);
        if (availableTable != -1) {
            reservations.put(name, availableTable + 1);
            for (int i = availableTable; i < availableTable + quantity; i++) {
                tablesAvailability[i] = false;
            }
            System.out.println("Reservation successful for " + name + ". Table number: " + (availableTable + 1));
        } else {
            System.out.println("Reservation not available. All tables are booked.");
        }
    }

    private int findAvailableTable(int quantity) {
        int tableIndex = -1;
        for (int i = 0; i < tablesAvailability.length; i++) {
            if (tablesAvailability[i]) {
                boolean allTablesAvailable = true;
                for (int j = 0; j < quantity; j++) {
                    if (i + j >= tablesAvailability.length || !tablesAvailability[i + j]) {
                        allTablesAvailable = false;
                        break;
                    }
                }
                if (allTablesAvailable) {
                    tableIndex = i;
                    break;
                }
            }
        }
        return tableIndex;
    }

    @Override
    public void removeReservation(String name, int quantity) {
        if (reservations.containsKey(name)) {
            int tableNumber = reservations.get(name);
            for (int i = tableNumber - 1; i < tableNumber - 1 + quantity; i++) {
                tablesAvailability[i] = true;
            }
            reservations.remove(name);
            System.out.println("Reservation for " + name + " removed.");
        } else {
            System.out.println("No reservation found for " + name);
        }
    }

    @Override
    public int getCurrentReservations(String name) {
        return reservations.containsKey(name) ? reservations.get(name) : 0;
    }
    
    @Override
    public void getallReservations() {
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations.");
        } else {
            System.out.println("Customer Name, Availability");
            for (Map.Entry<String, Integer> entry : reservations.entrySet()) {
                String customerName = entry.getKey();
                Integer tableAvailability = entry.getValue();
                System.out.printf("%s,%d\n", customerName, tableAvailability);
            }
        }
    }

    @Override
    public void getAvailableTables() {
        System.out.println("Available Tables:");
        for (int i = 0; i < tablesAvailability.length; i++) {
            System.out.println("Table " + (i + 1) + ": " + (tablesAvailability[i] ? "Yes" : "No"));
        }
    }

    @Override
    public void modifyReservationDetails() {
        System.out.println("Modify reservation details...");
        System.out.print("Enter reservation name: ");
        Scanner scanner = new Scanner(System.in);
        String reservationName = scanner.next();
        
        if (reservations.containsKey(reservationName)) {
            System.out.print("Enter new quantity of people: ");
            int newQuantity = scanner.nextInt();
            
            int oldQuantity = reservations.get(reservationName);
            int availableTable = findAvailableTable(newQuantity - oldQuantity);
            
            if (availableTable != -1) {
                reservations.put(reservationName, availableTable + 1);
                for (int i = availableTable; i < availableTable + (newQuantity - oldQuantity); i++) {
                    tablesAvailability[i] = false;
                }
                System.out.println("Reservation details modified successfully for " + reservationName + ". Table number: " + (availableTable + 1));
            } else {
                System.out.println("Not enough tables available to modify the reservation.");
            }
        } else {
            System.out.println("No reservation found for " + reservationName);
        }
    }
    
    @Override
    public void printReservationsToCSV() {
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Enter the folder path to save this CSV (e.g., C:\\Users\\ASUS\\Desktop\\): ");
	    String folderPath = scanner.nextLine();

	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String fileName = "RESERVATIONS_" + dateTimeFormat.format(new Date()) + ".csv";

	    String filePath = folderPath + fileName;

	    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

	        writer.println("Customer Name , Availability");

	        for (Map.Entry<String, Integer> entry : reservations.entrySet()) {
	            String customerName = entry.getKey();
	            Integer tableAvailability = entry.getValue();
	            writer.printf("%s,%d\n", customerName, tableAvailability);
	        }

	        System.out.println("CSV created successfully at: " + filePath);
	    } catch (IOException e) {
	        System.out.println("Error writing to CSV file: " + e.getMessage());
	    }
	}

    @Override
    public void importReservations() {
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.print("Enter the folder path to import this CSV (e.g., C:\\Users\\ASUS\\Desktop\\reservation.csv): ");
	    String filePath = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String tableName = parts[0].trim();
                    Integer availability = Integer.parseInt(parts[1].trim());
                    reservations.put(tableName, availability);
                }
            }
            System.out.println("CSV imported successfully from: " + filePath);
            System.out.println("You can view updated reservations via  : " + filePath);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error importing CSV file: " + e.getMessage());
        }
    }
}
