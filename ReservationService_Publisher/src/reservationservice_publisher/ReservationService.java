

package reservationservice_publisher;

import java.util.HashMap;
import java.util.Map;

public interface ReservationService {
    void addReservation(String name, int quantity);
    void removeReservation(String name, int quantity);
    int getCurrentReservations(String name);
    void getAvailableTables();
    void modifyReservationDetails();
    void importReservations();
	void printReservationsToCSV();
	void getallReservations();
}

