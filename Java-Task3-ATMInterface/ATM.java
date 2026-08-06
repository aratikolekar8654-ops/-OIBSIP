package ATMInterface;
import java.util.*;

public class ATM {
    private Account currentAccount;
    private ArrayList<Transaction> history = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public boolean login(Bank bank) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter User ID: ");
            String id = sc.next();
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            Account acc = bank.getAccount(id);
            if (acc != null && acc.validatePin(pin)) {
                currentAccount = acc;
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid credentials!");
                attempts++;
            }
        }
        System.out.println("Access denied!");
        return false;
    }

    public void showMenu(Bank bank) {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> showHistory();
                case 2 -> withdraw();
                case 3 -> deposit();
                case 4 -> transfer(bank);
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    private void showHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction t : history) {
            System.out.println(t);
        }
    }

    private void withdraw() {
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();
        if (currentAccount.withdraw(amt)) {
            history.add(new Transaction("Withdraw", amt, "Balance left: " + currentAccount.getBalance()));
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    private void deposit() {
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();
        currentAccount.deposit(amt);
        history.add(new Transaction("Deposit", amt, "Balance: " + currentAccount.getBalance()));
        System.out.println("Deposit successful!");
    }

    private void transfer(Bank bank) {
        System.out.print("Enter recipient ID: ");
        String rid = sc.next();
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();

        Account recipient = bank.getAccount(rid);
        if (recipient != null && currentAccount.withdraw(amt)) {
            recipient.deposit(amt);
            history.add(new Transaction("Transfer", amt, "To " + rid));
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed (check balance or recipient)!");
        }
    }
}
