import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ServerMain {

    public static void main(String[] args) {
	// write your code here
        Server s = new Server();
        try {
            FileReader reader = new FileReader("src/main/java/core.json");
            Gson gsonParser = new Gson();
            s = gsonParser.fromJson(reader, Server.class);

        } catch (FileNotFoundException e) {
            System.out.println("ServerMain.java : main : " + e);
        }
        /*
        Server s = new Server();
        */
        Thread serverThread = new Thread(s);
        serverThread.start();

        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            System.out.println("ServerMain.java : main : " + e);
        }
        System.out.println("end");

    }
}
