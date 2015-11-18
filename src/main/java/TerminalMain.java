import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by kimia on 11/17/15.
 */
public class TerminalMain {
    public static void main(String[] args) {
        XMLParser parser = new XMLParser("src/main/java/terminal.xml");
        parser.setRootElement();
        Terminal myTerminal = new Terminal(parser.getElementAttribute(parser.getRootElement(), "id"),
                                            parser.getElementAttribute(parser.getRootElement(), "type"),
                                            parser.getElementAttribute(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(1)),"ip"),
                                            parser.getElementAttribute(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(1)),"port"),
                                            parser.getElementAttribute(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(3)),"path"));
        int numberOfTransactions = parser.getElementChildNodes(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(5))).getLength();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(myTerminal.getTerminalSocket().getOutputStream());

            for(int i = 1 ; i < numberOfTransactions ; i = i + 2){
                Transaction transaction = new Transaction(
                        parser.getElementAttribute(parser.castNodeToElement(parser.getElementChildNodes(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(5))).item(i)),"id"),
                        parser.getElementAttribute(parser.castNodeToElement(parser.getElementChildNodes(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(5))).item(i)),"type"),
                        parser.getElementAttribute(parser.castNodeToElement(parser.getElementChildNodes(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(5))).item(i)),"amount"),
                        parser.getElementAttribute(parser.castNodeToElement(parser.getElementChildNodes(parser.castNodeToElement(parser.getElementChildNodes(parser.getRootElement()).item(5))).item(i)), "deposit")
                );

                oos.writeObject(transaction);
                myTerminal.addToTerminalTransactions(transaction);
            }

            oos.writeObject(null);
        } catch (IOException e) {
            System.out.println("TerminalMain.java : main : " + e);
        }


    }

}
