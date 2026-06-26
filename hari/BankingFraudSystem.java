import java.util.*;

// Graph class
class BankingGraph {
    private Map<String, List<String>> graph = new HashMap<>();
    private Map<String, Integer> transactionCount = new HashMap<>();

    // Add account if not exists
    private void addAccount(String acc) {
        graph.putIfAbsent(acc, new ArrayList<>());
        transactionCount.putIfAbsent(acc, 0);
    }

    // Add transaction
    public void addTransaction(String from, String to) {
        addAccount(from);
        addAccount(to);

        graph.get(from).add(to);

        transactionCount.put(from, transactionCount.get(from) + 1);

        System.out.println("Transaction added: " + from + " -> " + to);
    }

    // Remove transaction
    public void removeTransaction(String from, String to) {
        if (graph.containsKey(from)) {
            graph.get(from).remove(to);
            System.out.println("Transaction removed: " + from + " -> " + to);
        } else {
            System.out.println("Transaction not found.");
        }
    }

    // Detect cycles using DFS
    private boolean dfs(String node, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(node)) return true;

        if (visited.contains(node)) return false;

        visited.add(node);
        recStack.add(node);

        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (dfs(neighbor, visited, recStack)) return true;
        }

        recStack.remove(node);
        return false;
    }

    // Check fraud
    public void detectFraud() {
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();

        boolean cycleFound = false;

        for (String node : graph.keySet()) {
            if (dfs(node, visited, recStack)) {
                cycleFound = true;
                break;
            }
        }

        if (cycleFound) {
            System.out.println("⚠ Fraud Alert: Circular transaction detected!");
        } else {
            System.out.println("No circular fraud detected.");
        }

        // High transaction activity
        for (String acc : transactionCount.keySet()) {
            if (transactionCount.get(acc) > 3) {
                System.out.println("⚠ Suspicious Activity: " + acc + " has high transaction frequency.");
            }
        }
    }

    // Display graph
    public void display() {
        System.out.println("Transaction Network:");
        for (String acc : graph.keySet()) {
            System.out.println(acc + " -> " + graph.get(acc));
        }
    }
}

// Main class
public class BankingFraudSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankingGraph bg = new BankingGraph();

        int choice;
        do {
            System.out.println("\n1. Add Transaction\n2. Remove Transaction\n3. Display Network\n4. Detect Fraud\n5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("From Account: ");
                    String from = sc.nextLine();
                    System.out.print("To Account: ");
                    String to = sc.nextLine();
                    bg.addTransaction(from, to);
                    break;

                case 2:
                    System.out.print("From Account: ");
                    from = sc.nextLine();
                    System.out.print("To Account: ");
                    to = sc.nextLine();
                    bg.removeTransaction(from, to);
                    break;

                case 3:
                    bg.display();
                    break;

                case 4:
                    bg.detectFraud();
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}