import com.cycling74.max.*;
import java.io.*;
import java.net.*;

public class ExternalServer extends MaxObject {
	private ServerSocket in;
	private Socket client;
	private InputStream is;
	private String outString;
	private int c;
	
	public ExternalServer(Atom[] args) {
		declareOutlets(new int[] {DataTypes.ALL});
		try {
			in = new ServerSocket(7300);
		} catch(Exception e) {}
	}
	
	public void dblclick() {
		try {
			client = in.accept();
			System.out.println("client connected");
			is = client.getInputStream();
			outString = "";
			
			while (client.isConnected()) {
				c = is.read();
				if (c==-1) {break;}
				if ((char) c != '\n') {
					outString += (char) c;
				}
				else {
					outlet(0, outString);
					System.out.println("message received : " + outString);
					outString = "";
				}
			}
			
			System.out.println("client disconnected");
		} catch (Exception e) {}
	}
}
