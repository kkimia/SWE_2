import java.io.Serializable;

/**
 * Created by kimia on 11/18/15.
 */
public class Transaction implements Serializable{

    private String id;
    private String type;
    private String amount;
    private String deposit;

    public Transaction(String i, String t, String a, String d){
        id = i;
        type = t;
        amount = a;
        deposit = d;

    }

    public String getDeposit() {
        return deposit;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void printTransaction(){
        System.out.println("");
        System.out.println("TRANSACTION");
        System.out.println("id : " + id );
        System.out.println("type : " + type);
        System.out.println("amount : " + amount);
        System.out.println("deposit : " + deposit);
    }

}