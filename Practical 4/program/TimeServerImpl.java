package program;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

import program.AppConstants;


public class TimeServerImpl extends UnicastRemoteObject implements TimeServer {

	private static final long serialVersionUID = -6810169856453308607L;

	private LocalTime time;

	public TimeServerImpl(LocalTime time) throws RemoteException {
		this.time = time;
	}

	@Override
	public LocalTime gettime() throws RemoteException {
		return time;
	}

	@Override
	public void ajustHours(LocalTime hoursClient, long diffNanos) throws RemoteException {
		long horarioLocalNanos = hoursClient.toNanoOfDay();
		long thisNanos = this.gettime().toNanoOfDay();
		long newNanos = thisNanos - horarioLocalNanos;
		newNanos = newNanos * -1 + diffNanos + thisNanos;
		LocalTime newLocalTime = LocalTime.ofNanoOfDay(newNanos);
		System.out.println("Updated Hours: " + AppConstants.formatter.format(newLocalTime));
		this.time = newLocalTime;
	}

}
