package program;

import static program.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import program.AppConstants;

public class Clock_Two {

	public static void main(String[] args) {
		try {
			
			TimeServer ts2 = new TimeServerImpl(LocalTime.parse("07:10:00", formatter));
			Registry registry2 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_2);
			registry2.rebind(TimeServerImpl.class.getSimpleName(), ts2);
			System.out.println(String.format("Server 2 started on port %s", AppConstants.SERVER_PORT_2));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
