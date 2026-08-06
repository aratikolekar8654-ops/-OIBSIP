package ATMInterface;


public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(new Account("user1", 1234, 5000));
        bank.addAccount(new Account("user2", 4321, 3000));

        ATM atm = new ATM();
        if (atm.login(bank)) {
            atm.showMenu(bank);
        }
    }
}
