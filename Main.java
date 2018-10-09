import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.ArrayList;

public class Main {
  public static void main(String args[]) throws InterruptedException {
    String hostname = System.getenv("TEST_HOSTNAME");
    String portStr = System.getenv("TEST_PORT");    
    String debugStr = System.getenv("DEBUG");
    String delayStr = System.getenv("DELAY");
    String exitOnFailStr = System.getenv("EXIT_ON_FAIL");
    boolean debug = false; // Debug off by default
    int delay = 10; // Default 10s delay between each query
    int port = 8080; // Default is port 8080 but TEST_PORT env var must be set.
    boolean exitOnFail = true; // Default to exit application upon a NoRouteToHostException
    ArrayList<Socket> sockets = new ArrayList<Socket>();

    if (hostname == null) {
        System.err.println("TEST_HOSTNAME environment variable must be set!");
        System.exit(1);
    }
    if (portStr == null) {
        System.err.println("TEST_PORT environment variable must be set!");
        System.exit(1);
    } else {
        port = Integer.parseInt(portStr);
    }
    if (debugStr != null && debugStr.equals("true")) {
        debug = true;
    }
    if (delayStr != null) {
        delay = Integer.parseInt(delayStr);
    }

    while (true) {
      try {
        Socket s = new Socket(hostname, port);
        sockets.add(s);
        if (debug) {
          Date now = new Date();
          System.out.println(now.toString());
	    }	
      } catch (Exception e) {
          Date now = new Date();
	      System.err.println(now.toString());
          e.printStackTrace();
      } finally {
    	Thread.sleep(delay * 100);
      }
    }
  }
}
