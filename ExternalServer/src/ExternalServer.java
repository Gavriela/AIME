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
			is = client.getInputStream();
			
			while (client.isConnected()) {
				outString = "";
				c = is.read();
				if (c==-1) {break;}
				if ((char) c != '\n') {
					outString += (char) c;
				}
				else {
					outlet(0, outString);
				}
			}
		} catch (Exception e) {}
	}
}
