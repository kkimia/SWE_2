import java.math.BigDecimal;

/**
 * Created by kimia on 11/18/15.
 */
public class Deposit {
    private String customer;
    private String id;
    private BigDecimal initialBalance;
    private BigDecimal upperBound;

    Deposit(String name, String i, BigDecimal ib, BigDecimal ub){
        customer = name;
        id = i;
        initialBalance = ib;
        upperBound = ub;
    }

    public String getCustomerName() {
        return customer;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
    public void printDeposit(){
        System.out.println("");
        System.out.println("DEPOSIT");
        System.out.println("customerName : " + customer );
        System.out.println("id : " + id);
        System.out.println("initialBalance : " + initialBalance);
        System.out.println("upperBound : " + upperBound);
    }
}
