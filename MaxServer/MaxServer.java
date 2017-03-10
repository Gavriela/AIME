import java.io.*;
import java.net.*;
import oscP5.*;
import netP5.*;


public class MaxServer {
	private ServerSocket in;
	OscP5 oscP5;
	NetAddress out;
	
	public MaxServer(ServerSocket _in, NetAddress _out) {
		in = _in;
		oscP5 = new OscP5(this, 54321);
		out = _out;
	}
	
	public void start() {
		try {
			Socket client = in.accept();
			InputStream is = client.getInputStream();
			
			while (client.isConnected()) {
				String outString = "";
				int c = is.read();
				if (c==-1) {break;}
				if ((char) c != '\n') {
					outString += (char) c;
				}
				else {
					OscMessage message = new OscMessage(outString);
					oscP5.send(message);
				}
			}
		} catch (Exception e) {System.out.println("bloc 1 : " + e);}
	}

	public static void main(String[] args) {
		try {
			MaxServer serv = new MaxServer(new ServerSocket(7300), new NetAddress("127.0.0.1", 7400));
			serv.start();
		} catch (Exception e) {System.out.println("bloc 2 : " + e);}
	}

}
