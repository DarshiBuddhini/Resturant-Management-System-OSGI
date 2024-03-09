
package reservationservice_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ReservationPublisherActivator implements BundleActivator {

    ServiceRegistration<ReservationService> serviceRegistration;

    public void start(BundleContext context) throws Exception {
        System.out.println("Reservation Management Service Started......");
        ReservationService reservationService = new ReservationServiceImpl();

        serviceRegistration = context.registerService(ReservationService.class, reservationService, null);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Reservation Management Service Stopped.....");
        serviceRegistration.unregister();
    }

}
