package ATMInterface;
public class Account {
    private String accountId;
    private int pin;
    private double balance;

    public Account(String accountId, int pin, double balance) {
        this.accountId = accountId;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountId() {
        return accountId;
    }
}
