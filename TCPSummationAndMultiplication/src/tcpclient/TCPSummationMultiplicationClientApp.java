package tcpclient;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This is a controller class for the client-side application
 * 
 * This class demonstrate the usage of DataInputStream and DataOutputStream.
 * 
 * @author Mirza Sahid Afridi
 *
 */

public class TCPSummationMultiplicationClientApp {

	public static void main(String [] args)
	{
		// construct object from java.util.Scanner
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\tExecuting TCP Summation Client App");
		
		// receive input for three numbers at client-side
		System.out.print("\tEnter first number: ");
		int number1 = sc.nextInt();
		
		System.out.print("\tEnter second number: ");
		int number2 = sc.nextInt();
		
		System.out.print("\tEnter third number: ");
		int number3 = sc.nextInt();
		
		try {
			
			// 1. Define server information
			int serverPortNo = 8087;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 2. Bind to the server, request for connection
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			// 3. Send data to the server
			OutputStream outStream = socket.getOutputStream();
			DataOutputStream dosOutput = new DataOutputStream(outStream);
			
			// write the input to the stream for sending request
			dosOutput.writeInt(number1);
			dosOutput.writeInt(number2);
			dosOutput.writeInt(number3);
			
			// 4. Process response from the server
			InputStream inStream = socket.getInputStream();
			DataInputStream disInput = new DataInputStream(inStream);
			
			// read the data retrieved from the server
			int result1 = disInput.readInt();
			int result2 = disInput.readInt();
			
			// 5. Further processing - display the result
			System.out.println("\tSending to the server: " + number1 + ", " 
					+ number2 + ", " + number3);
			System.out.println("\tReceiving from server: ");
			System.out.println("\tSum: " + result1);
			System.out.println("\tMultiplication: " + result2);
			
			// Close all the closable
			disInput.close();
			dosOutput.close();
			socket.close();
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		// indication for end execution
		System.out.println("\tEnd of execution at TCPSummationClientApp");
		
	}
}
