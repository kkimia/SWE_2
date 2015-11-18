import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by kimia on 11/18/15.
 */
public class Terminal {
    private String id;
    private String type;
    private String serverIP;
    private int portNumber;
    private String outLogPath;
    private LinkedList<Transaction> terminalTransactions;
    private Socket terminalSocket;
    private BufferedReader inputData;
    private PrintWriter outputData;

    public BufferedReader getInputData() {
        return inputData;
    }

    public PrintWriter getOutputData() {
        return outputData;
    }

    public void addToTerminalTransactions(Transaction t) {
        terminalTransactions.add(t);
    }

    public Socket getTerminalSocket() {
        return terminalSocket;
    }

    public Terminal(String i, String t, String ip,String port, String olp){
        id = i;
        type = t;
        serverIP = ip;
        portNumber = Integer.parseInt(port);
        outLogPath = olp;
        terminalTransactions = new LinkedList<Transaction>();
        try{
            terminalSocket = new Socket(serverIP, portNumber);
            System.out.println("Terminal is now connected to server.");
        }
        catch (IOException e){
            System.out.println("Client.java : constructor : " + e);
        }
    }
    public void setInputGetter(){
        try {
            inputData = new BufferedReader(new InputStreamReader(terminalSocket.getInputStream()));

        }
        catch (IOException e) {
            System.out.println("Terminal.java :  : " + e);
        }
    }

    public void setOutputSender(){
        try {
            outputData = new PrintWriter(new OutputStreamWriter(terminalSocket.getOutputStream()));
        }
        catch (IOException e) {
            System.out.println("Terminal.java : setOutputSender : " + e);
        }
    }
    public void closeTerminalSocket(){
        try {
            outputData.close();
            inputData.close();
            terminalSocket.close();
        }
        catch (IOException e) {
            System.out.println("Client.java : closeClientSocket : " + e);
        }
    }

}
