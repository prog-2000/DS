import java.net.*; 
import java.rmi.*; 
public class AddServer {
public static void main(String args[]) { 
try {
//AddServerImpl addServerImpl = new AddServerImpl(); 

AddServerImpl Impl = new AddServerImpl(); 

Naming.rebind("AddServer", Impl);

//Naming.rebind("AddServer", addServerImpl);
//Naming.rebind 

System.out.println("Server is start");

}
catch(Exception e) 
{
 System.out.println("Exception: " + e);
}
}
}

