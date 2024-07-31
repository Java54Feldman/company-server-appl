package telran.employees.net;


import java.io.*;
import telran.employees.*;
import telran.io.Persistable;
import telran.net.*;

public class CompanyServerAppl {

	private static final String FILE_NAME = "employeesTest.data";
	private static final int PORT = 5000;

	public static void main(String[] args) {
		Company company = new CompanyMapsImpl();
		try {
			((Persistable)company).restore(FILE_NAME);
		} catch (Exception e) {

		}
		Protocol protocol = new CompanyProtocol(company);
		TcpServer tcpServer = new TcpServer(protocol, PORT);
		//start TcpServer as a thread
		Thread serverThread = new Thread(tcpServer);
		serverThread.start();
		//cycle with asking a user to enter shutdown for exit from the server
		//regular while cycle with no using cli-view
		//by entering "shutdown" you should call method shutdown of the TcpServer
		//after shutdown you should perform saving the data into the file
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter 'shutdown' to stop the server");
            try {
				String input = reader.readLine();
				if ("shutdown".equalsIgnoreCase(input)) {
				    tcpServer.shutdown();
				    ((Persistable)company).save(FILE_NAME);
				    System.out.println("Server saved data and closed connection");
				    break;
				}
			} catch (Exception e) {
				System.out.println("Error saving data: " + e.getMessage());
			}
        }

	}
	
}
