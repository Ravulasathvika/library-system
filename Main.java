
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("    Library Management System v1.0   ");
        System.out.println("========================================");
        System.out.println("System started...");

        LibraryManager manager = new LibraryManager();
        Scanner sc = new Scanner(System.in);

        // ── Preloaded data for testing ─────────────────────────────
        manager.addBook(new Book(1, "Java Basics", "James Gosling"));
        manager.addBook(new Book(2, "Spring Boot Guide", "Rod Johnson"));
        manager.addBook(new Book(3, "Clean Code", "Robert Martin"));
        manager.registerUser(new User(101, "Gowtham"));
        manager.registerUser(new User(102, "Priya"));
        System.out.println();

        while (true) {
            System.out.println("─────────────────────────────────────────");
            System.out.println(" 1. Add Book          5. Search Book");
            System.out.println(" 2. Register User     6. Show All Books");
            System.out.println(" 3. Issue Book        7. My Issued Books");
            System.out.println(" 4. Return Book       8. Exit");
            System.out.println("─────────────────────────────────────────");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Book ID   : ");
                    int bid = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Title     : ");
                    String btitle = sc.nextLine().trim();
                    System.out.print("Author    : ");
                    String bauthor = sc.nextLine().trim();
                    manager.addBook(new Book(bid, btitle, bauthor));
                    break;

                case 2:
                    System.out.print("User ID   : ");
                    int uid = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Name      : ");
                    String uname = sc.nextLine().trim();
                    manager.registerUser(new User(uid, uname));
                    break;

                case 3:
                    System.out.print("Book ID   : ");
                    int issueBid = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("User ID   : ");
                    int issueUid = Integer.parseInt(sc.nextLine().trim());
                    manager.issueBook(issueBid, issueUid);
                    break;

                case 4:
                    System.out.print("Book ID   : ");
                    int retBid = Integer.parseInt(sc.nextLine().trim());
                    manager.returnBook(retBid);
                    break;

                case 5:
                    System.out.print("Keyword   : ");
                    String kw = sc.nextLine().trim();
                    manager.searchBook(kw);
                    break;

                case 6:
                    manager.showAllBooks();
                    break;

                case 7:
                    System.out.print("User ID   : ");
                    int myUid = Integer.parseInt(sc.nextLine().trim());
                    manager.showIssuedByUser(myUid);
                    break;

                case 8:
                    System.out.println("Goodbye! 📚");
                    sc.close();
                    return;

                default:
                    System.out.println("[!] Invalid option. Try 1–8.");
            }
            System.out.println();
        }
    }
}
