import java.util.*;

class Transaction {
    int id;
    double amount;
    int priority;

    public Transaction(int id, double amount, int priority) {
        this.id = id;
        this.amount = amount;
        this.priority = priority;
    }

    public String toString() {
        return "Transaction ID: " + id +
               ", Amount: " + amount +
               ", Priority: " + priority;
    }
}

class TransactionComparator implements Comparator<Transaction> {
    public int compare(Transaction t1, Transaction t2) {
        return t2.priority - t1.priority; // Higher priority first
    }
}

public class BankingSystem {

    PriorityQueue<Transaction> pq;
    LinkedList<Transaction> list;

    public BankingSystem() {
        pq = new PriorityQueue<>(new TransactionComparator());
        list = new LinkedList<>();
    }

    public void addTransaction(int id, double amount, int priority) {

        for (Transaction t : list) {
            if (t.id == id) {
                System.out.println("Transaction ID already exists!");
                return;
            }
        }

        Transaction t = new Transaction(id, amount, priority);
        pq.add(t);
        list.add(t);

        System.out.println("Added: " + t);
    }

    // Process Transaction
    public void processTransaction() {
        if (pq.isEmpty()) {
            System.out.println("No transactions to process");
            return;
        }

        Transaction t = pq.poll();
        list.remove(t);

        System.out.println("Processed: " + t);
    }

    public void updateTransaction(int id, double newAmount, int newPriority) {

        Transaction target = null;

        for (Transaction t : list) {
            if (t.id == id) {
                target = t;
                break;
            }
        }

        if (target == null) {
            System.out.println("Transaction not found");
            return;
        }

        pq.remove(target);
        list.remove(target);

        Transaction updated = new Transaction(id, newAmount, newPriority);
        pq.add(updated);
        list.add(updated);

        System.out.println("Updated: " + updated);
    }

    // Cancel Transaction
    public void cancelTransaction(int id) {

        Transaction target = null;

        for (Transaction t : list) {
            if (t.id == id) {
                target = t;
                break;
            }
        }

        if (target == null) {
            System.out.println("Transaction not found");
            return;
        }

        pq.remove(target);
        list.remove(target);

        System.out.println("Cancelled: " + target);
    }
    public void display() {
        if (list.isEmpty()) {
            System.out.println("No transactions available");
            return;
        }

        System.out.println("\n All Transactions:");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankingSystem bank = new BankingSystem();

        int choice;

        do {
            System.out.println("\n===== Banking System Menu =====");
            System.out.println("1. Add Transaction");
            System.out.println("2. Process Transaction");
            System.out.println("3. Update Transaction");
            System.out.println("4. Cancel Transaction");
            System.out.println("5. Display Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();

                    bank.addTransaction(id, amount, priority);
                    break;

                case 2:
                    bank.processTransaction();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();

                    System.out.print("Enter new Amount: ");
                    double newAmount = sc.nextDouble();

                    System.out.print("Enter new Priority: ");
                    int newPriority = sc.nextInt();

                    bank.updateTransaction(uid, newAmount, newPriority);
                    break;

                case 4:
                    System.out.print("Enter ID to cancel: ");
                    int cid = sc.nextInt();

                    bank.cancelTransaction(cid);
                    break;

                case 5:
                    bank.display();
                    break;

                case 6:
                    System.out.println("👋 Exiting program...");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}