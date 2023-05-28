package program;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface TimeServer extends Remote {

	LocalTime gettime() throws RemoteException;

	void ajustHours(LocalTime hoursClient, long nanos) throws RemoteException;
}
