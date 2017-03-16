import java.net.*;
import java.io.*;

public class DataSender {
	private static Socket sock;
	private static OutputStream out;
	private static String s;
	
	public static void main(String[] args) {
		try {
			sock = new Socket("127.0.0.1", 7300);
			out = sock.getOutputStream();
			s = "867.0 867.0 867.0 1.0\n";
			out.write(s.getBytes());
			sock.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
