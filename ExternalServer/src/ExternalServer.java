import com.cycling74.max.*;
import java.io.*;
import java.net.*;

public class ExternalServer extends MaxObject {
	private ServerSocket in;
	private Socket client;
	private InputStream is;
	private String outString;
	private float[] data;
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
					System.out.println("message received : " + outString);
					data = new float[outString.split(" ").length];
					for (int i=0 ; i<data.length ; i++) {
						data[i] = Float.parseFloat(outString.split(" ")[i]);
					}
					outlet(0, data);
					outString = "";
				}
			}
			
			System.out.println("client disconnected");
		} catch (Exception e) {}
	}
}

