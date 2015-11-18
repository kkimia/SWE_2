
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by kimia on 11/17/15.
 */
public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket server;
    private int port;
    LinkedList<Deposit> deposits = new LinkedList<Deposit>();
    private String outLog;
    File logFile;


    public void createLogFile(){
        logFile = new File("src/main/java/"+outLog);
        if(!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Server.java : createLogFile : "  + e);
            }
        }
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is ready... ");
            createLogFile();
            FileWriter writer = new FileWriter(logFile.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Server started at : ");
            Date date=new Date();
            bufferedWriter.append(new SimpleDateFormat("yyyy.MM.dd  HH:mm").format(date));
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Server.java : constructor : " + e);
        }
        try {
            int id = 0;
            while (true) {
                server = serverSocket.accept();
                System.out.println("A client is now connected... ");
                TerminalHandler terminalHandler = new TerminalHandler(server, id++, deposits);
                Thread handlerThread = new Thread(terminalHandler);
                handlerThread.start();
            }
        }
        catch (IOException e) {
            System.out.println("Server.java : constructor : " + e);
        }
    }

}
