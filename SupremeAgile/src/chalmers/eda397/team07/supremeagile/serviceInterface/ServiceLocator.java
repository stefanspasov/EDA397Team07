package chalmers.eda397.team07.supremeagile.serviceInterface;

import java.util.HashMap;
import java.util.Map;

import chalmers.eda397.team07.supremeagile.service.RepositoryService;

public class ServiceLocator {
	private static Map<Class, Class> services;

	public static void registerServices() {
		services = new HashMap<Class, Class>();
		services.put(IRepositoryService.class, RepositoryService.class);
	}

	public static <T> T getService(Class<T> serviceInterface)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		for (Map.Entry<Class, Class> service : services.entrySet()) {
			if (serviceInterface.getName() == service.getKey().getName()) {
				return (T) service.getValue().newInstance();
			}
		}
		throw new ClassNotFoundException(String.format(
				"The current service '%1$s' could not be located.",
				serviceInterface.getName()));
	}
}
