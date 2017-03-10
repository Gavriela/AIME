import java.io.*;
import java.net.*;
import java.util.*;


public class MaxServer {
	private ServerSocket in;
	private DatagramSocket out;
	private byte[] buf;
	
	public MaxServer(ServerSocket _in, DatagramSocket _out) {
		in = _in;
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
					buf = outString.getBytes();
					DatagramPacket packet = new DatagramPacket(buf, buf.length);
					out.send(packet);
				}
			}
		} catch (Exception e) {}
	}

	public static void main(String[] args) {
		try {
			MaxServer serv = new MaxServer(new ServerSocket(9999), new DatagramSocket(7400, InetAddress.getByName("127.0.0.1")));
			serv.start();
		} catch (Exception e) {}
	}

}
