package tcpserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controller.NumberCalculator;

/**
 * TCPSummationServerApp is a simple TCP server application that listens to 
 * client requests,reads three integer numbers from the client, calculates 
 * the sum and multiplication of these numbers, and sends the results
 * back to the client.
 * 
 * 
 * @author Mirza Sahid Afridi
 *
 */
public class TCPSummationMultiplicationServerApp {

	public static void main(String[] args) {
		
		System.out.println("\n\tExecuting TCPSummationServerApp");
		
		try {
			
			// 1. Bind to a port
			int portNo = 8087;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			NumberCalculator numberCalculator  = new NumberCalculator();
			
			System.out.println("\tWaiting for request");
			
			// 2. Listen to request
			while (true) {
				
				// 3. Accept request
				Socket clientRequest = serverSocket.accept();
				
				// 4. Process client request - read two numbers from client
				InputStream inStream = clientRequest.getInputStream();
				DataInputStream dis = new DataInputStream(inStream);
				int number1 = dis.readInt();
				int number2 = dis.readInt();
				int number3 = dis.readInt();
				System.out.println("\tFrom client: " + number1 + ", " 
						+ number2 + ", " 
						+ number3);
				// From client: 101, 2001 
				
				
				// Further processing
				int totalSum = numberCalculator.getSum(number1, number2, number3);
				int totalMulti = numberCalculator.getMultiplication(
						number1, number2, number3);
				
				// 5. Respond to client - return total to client
				OutputStream outStream = clientRequest.getOutputStream();
				DataOutputStream dos = new DataOutputStream(outStream);
				
				
				dos.writeInt(totalSum);
				dos.writeInt(totalMulti);
				System.out.println("\tTo client: ");
				System.out.println("\tTotal Summation: "+ totalSum + 
						" and Multiplication: " + totalMulti);
				
				// To client: 2101
				
				// In string + is not for addition/summation
				// In string + is concatenation, joining two string
				
				
				System.out.println("\n\tWaiting for next request");
				
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}

	}

}






