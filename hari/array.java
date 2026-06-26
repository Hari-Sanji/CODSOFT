import java.util.Scanner;

public class array {
    int a[] = new int[10];
    Scanner sc = new Scanner(System.in);
    int n, i, pos, item;

    void arr() {
        System.out.print("Enter number of elements: ");
        n = sc.nextInt();

        System.out.println("Enter elements:");
        for(i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
    }

    void insert() {
        System.out.print("Enter position to insert: ");
        pos = sc.nextInt();

        System.out.print("Enter element: ");
        item = sc.nextInt();

        for(i = n; i >= pos; i--) {
            a[i] = a[i - 1];
        }
        a[pos - 1] = item;
        n++;
    }

    void delete() {
        System.out.print("Enter position to delete: ");
        pos = sc.nextInt();

        for(i = pos - 1; i < n ; i++) {
            a[i] = a[i + 1];
        }
        n--;
    }

    void display() {
        System.out.println("Array elements:");
        for(i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        array a = new array();
        a.arr();
        a.insert();
        a.delete();
        a.display();
    }
}