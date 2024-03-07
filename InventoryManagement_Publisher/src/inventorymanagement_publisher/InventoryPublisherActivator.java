package inventorymanagement_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class InventoryPublisherActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Inventory Service Started......");
		InventoryService inventory = new InventoryServiceImpl();
		
		serviceRegistration = context.registerService(InventoryService.class.getName(), inventory, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Inventory Service Stopped.....");
		serviceRegistration.unregister();
	}

}
