package administrationservice_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class AdministrationPublisherActivator implements BundleActivator {

	ServiceRegistration<?> servicePublishRegistration;
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("\nAdministration Service Started.....");
		AdministrationService servicepublisher = new AdministrationServiceImpl();
		servicePublishRegistration = bundleContext.registerService(AdministrationService.class.getName(), servicepublisher, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Administration Service Stopped.....");
		servicePublishRegistration.unregister();
		
	}

}
