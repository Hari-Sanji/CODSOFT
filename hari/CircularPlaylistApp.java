import java.util.Scanner;

// Node class
class Song {
    String title;
    Song next;

    public Song(String title) {
        this.title = title;
        this.next = null;
    }
}

// Circular Playlist Class
class Playlist {
    private Song head = null;
    private Song tail = null;

    // Add song
    public void addSong(String title) {
        Song newSong = new Song(title);

        if (head == null) {
            head = tail = newSong;
            tail.next = head;
        } else {
            tail.next = newSong;
            tail = newSong;
            tail.next = head;
        }

        System.out.println(title + " added to playlist.");
    }

    // Remove song
    public void removeSong(String title) {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song current = head;
        Song prev = tail;

        do {
            if (current.title.equalsIgnoreCase(title)) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else {
                    prev.next = current.next;
                    if (current == head) head = current.next;
                    if (current == tail) tail = prev;
                }
                System.out.println(title + " removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Song not found.");
    }

    // Display playlist
    public void display() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song temp = head;
        System.out.println("Playlist:");
        do {
            System.out.print(temp.title + " -> ");
            temp = temp.next;
        } while (temp != head);

        System.out.println("(loops back)");
    }

    // Play songs continuously (demo loop)
    public void play(int cycles) {
        if (head == null) {
            System.out.println("No songs to play.");
            return;
        }

        Song temp = head;
        int count = 0;

        System.out.println("Playing playlist:");
        while (count < cycles) {
            System.out.println("Now playing: " + temp.title);
            temp = temp.next;

            if (temp == head) count++;
        }
    }
}

// Main Class
public class CircularPlaylistApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();

        int choice;
        do {
            System.out.println("\n1. Add Song\n2. Remove Song\n3. Display\n4. Play\n5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter song name: ");
                    playlist.addSong(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter song to remove: ");
                    playlist.removeSong(sc.nextLine());
                    break;

                case 3:
                    playlist.display();
                    break;

                case 4:
                    System.out.print("Enter number of cycles: ");
                    int cycles = sc.nextInt();
                    playlist.play(cycles);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}