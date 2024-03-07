package foodorderingmanagement_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class FoodOrderingPublisherActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Food Ordering Management Service Started......");
		FoodOrderingService inventory = new FoodOrderingServiceImpl();
		
		serviceRegistration = context.registerService(FoodOrderingService.class.getName(), inventory, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Food Ordering Management Service Stopped.....");
		serviceRegistration.unregister();
	}

}
