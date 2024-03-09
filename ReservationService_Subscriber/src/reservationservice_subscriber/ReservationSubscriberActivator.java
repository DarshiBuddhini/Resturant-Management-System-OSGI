
package reservationservice_subscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import reservationservice_publisher.ReservationService;

import java.util.Scanner;

public class ReservationSubscriberActivator implements BundleActivator {

    Scanner scanner = new Scanner(System.in);

    private ServiceReference<ReservationService> reservationServiceRef;
    private ReservationService reservationService;

    public void start(BundleContext context) throws Exception {

        String operation = "start";

        reservationServiceRef = context.getServiceReference(ReservationService.class);
        reservationService = context.getService(reservationServiceRef);

        System.out.println("\n================================= ");
        System.out.println("   Welcome to Reservation Management   	 ");
        System.out.println("=================================");

        System.out.println("\nPlease reserve the table you want.");

        while (!operation.equalsIgnoreCase("stop")) {

            System.out.println("--------------------------------");
            System.out.println("|️ a. Add a reservation.        |");
            System.out.println("| ‍️b. Remove a reservation.     |");
            System.out.println("| c. Get current reservations.  |");
            System.out.println("| d. Check available tables.   |");
            System.out.println("| e. Modify reservation details.|");
            System.out.println("| f. Export reservations to TXT.|");
            System.out.println("| g. Import reservations from TXT.|");
            System.out.println("| h. Get all reservations.|");
            System.out.println("| i. Type \"Stop\" to End.       |");
            System.out.println("--------------------------------");

            System.out.print("\nEnter the operation : ");
            operation = scanner.next();

            if (operation.equalsIgnoreCase("a")) {

                System.out.print("Enter Reservation name: ");
                String reservationName = scanner.next();

                System.out.print("Enter quantity of people: ");
                int quantity = scanner.nextInt();

                reservationService.addReservation(reservationName, quantity);

            } else if (operation.equalsIgnoreCase("b")) {

                System.out.print("Enter Reservation name: ");
                String reservationName = scanner.next();

                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();

                reservationService.removeReservation(reservationName, quantity);

            } else if (operation.equalsIgnoreCase("c")) {

                System.out.print("Enter reservation name: ");
                String reservationName = scanner.next();

                System.out.println(reservationName + " : " + reservationService.getCurrentReservations(reservationName));

            } else if (operation.equalsIgnoreCase("d")) {

                reservationService.getAvailableTables();

            } else if (operation.equalsIgnoreCase("e")) {

                reservationService.modifyReservationDetails();

            } else if (operation.equalsIgnoreCase("f")) {

                reservationService.printReservationsToCSV();

            } else if (operation.equalsIgnoreCase("g")) {

                reservationService.importReservations();
            }else if (operation.equalsIgnoreCase("h")) {

                reservationService.getallReservations();
            } else {
                break;
            }
        }

        System.out.println("\n------------------------------------------");
        System.out.println("|          Thank you for using our        |");
        System.out.println("|        Reservation Management App!      |");
        System.out.println("------------------------------------------");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop Reservation App....");
        context.ungetService(reservationServiceRef);
    }
}
