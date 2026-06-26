import java.util.Scanner;
import java.util.Stack;

public class BalancedValidator {

    public static boolean isBalanced(String code) {
        Stack<Character> stack = new Stack<>();

        for (char ch : code.toCharArray()) {

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }

            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n--- Balanced Symbol Validator ---");
            System.out.print("Enter code/expression: ");
            String input = sc.nextLine();

            if (isBalanced(input)) {
                System.out.println("✅ Symbols are BALANCED");
            } else {
                System.out.println("❌ Symbols are NOT BALANCED");
            }

            System.out.print("Do you want to modify and revalidate? (yes/no): ");
            choice = sc.nextLine().toLowerCase();

        } while (choice.equals("yes"));

        System.out.println("Exiting program...");
        sc.close();
    }
}