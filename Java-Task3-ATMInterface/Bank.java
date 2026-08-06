package ATMInterface;
import java.util.HashMap;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();

    public void addAccount(Account acc) {
        accounts.put(acc.getAccountId(), acc);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
}
