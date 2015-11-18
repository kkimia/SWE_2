import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by kimia on 11/18/15.
 */
public class TerminalHandler implements Runnable {
    private Socket terminalSocket;
    private int id = -1;
    private boolean running = true;
    private BufferedReader inputData;
    private PrintWriter outputData;
    private LinkedList<Deposit> deposits;

    TerminalHandler(Socket s, int i, LinkedList<Deposit> ds) {
        terminalSocket = s;
        id = i;
        deposits = ds;
    }

    public void setInputGetter() {
        try {
            inputData = new BufferedReader(new InputStreamReader(terminalSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println("TerminalHandler.java : setInputGetter : " + e);
        }
    }

    public void setOutputSender() {
        try {
            outputData = new PrintWriter(new OutputStreamWriter(terminalSocket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("TerminalHandler.java : setOutputSender : " + e);
        }
    }

    public void closeServerSocket() {
        try {
            outputData.close();
            inputData.close();
            terminalSocket.close();
            System.out.println("Server socket is now closed.");
        } catch (IOException e) {
            System.out.println("Server.java : closeClientSocket : " + e);
        }
    }

    public void run() {
        System.out.println("Terminal ID : " + id);

        try {
            int transactionCounter = 0;
            ObjectInputStream ois = new ObjectInputStream(terminalSocket.getInputStream());
            while (running) {
                transactionCounter++;
                Transaction t = (Transaction) ois.readObject();
                if (t == null) {
                    running = false;
                    break;
                }
                System.out.println("Transaction No. " + transactionCounter);
                t.printTransaction();
                for(int i = 0 ; i < deposits.size(); i++){
                    deposits.get(i).printDeposit();
                    if(deposits.get(i).getId().equals(t.getDeposit())){
                        if(t.getType().equals("deposit"))
                            deposits.get(i).setInitialBalance(deposits.get(i).getInitialBalance().add(new BigDecimal(t.getAmount())));
                        else
                            deposits.get(i).setInitialBalance(deposits.get(i).getInitialBalance().remainder(new BigDecimal(t.getAmount())));
                        deposits.get(i).printDeposit();
                    }
                }


            }

        }catch(Exception e){
            System.out.println("TerminalHandler.java : run : " + e);
        }
    }
}
