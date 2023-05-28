package program;

import static program.AppConstants.formatter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;
import program.AppConstants;
import program.TimeServer;
import program.TimeServerImpl;


public class MainClock {

	public static void main(String[] args) {
		try {
			ArrayList<LocalTime> times = new ArrayList<LocalTime>();

			LocalTime localTime = LocalTime.parse("07:00:00", AppConstants.formatter);
			times.add(localTime);
			System.out.println("Local time: " + formatter.format(localTime));

			
			Registry registry1 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_1, AppConstants.SERVER_PORT_1);
			TimeServer ts1 = (TimeServer) registry1.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection with Server 1 successfully established.");
			LocalTime TimeServer1 = ts1.gettime();
			times.add(TimeServer1);
			System.out.println("Server 1 time: " + formatter.format(TimeServer1));

			
			Registry registry2 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_2, AppConstants.SERVER_PORT_2);
			TimeServer ts2 = (TimeServer) registry2.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection with Server 2 successfully established.");
			LocalTime TimeServer2 = ts2.gettime();
			times.add(TimeServer2);
			System.out.println("Server 2 time: " + formatter.format(TimeServer2));


			Registry registry3 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME_3, AppConstants.SERVER_PORT_3);
			TimeServer ts3 = (TimeServer) registry3.lookup(TimeServerImpl.class.getSimpleName());
			System.out.println("Connection with Server 3 successfully established.");
			LocalTime TimeServer3 = ts3.gettime();
			times.add(TimeServer3);
			System.out.println("Server 3 time: " + formatter.format(TimeServer3));

			long nanoLocal = localTime.toNanoOfDay();
			long diffServer1 = TimeServer1.toNanoOfDay() - nanoLocal;
			long diffServer2 = TimeServer2.toNanoOfDay() - nanoLocal;
			long diffServer3 = TimeServer3.toNanoOfDay() - nanoLocal;
			long avgDiff = (diffServer1 + diffServer2 + diffServer3) / 3; 

		
			ts1.ajustHours(localTime, avgDiff);
			ts2.ajustHours(localTime, avgDiff);
			ts3.ajustHours(localTime, avgDiff);
			localTime = localTime.plusNanos(avgDiff);
			
			System.out.println("Updated Hours");

	
			System.out.println("Local Time: " + formatter.format(localTime));
			System.out.println("Server 1 time: " + formatter.format(ts1.gettime()));
			System.out.println("Server 2 time: " + formatter.format(ts2.gettime()));
			System.out.println("Server 3 time: " + formatter.format(ts3.gettime()));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
