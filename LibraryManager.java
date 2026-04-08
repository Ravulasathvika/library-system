

import java.util.*;

class LibraryManager {

    
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();
    Map<Integer, Integer> issuedBooks = new HashMap<>(); // bookId → userId
    Map<Integer, Integer> userBookCount = new HashMap<>(); // userId → count issued

    static final int MAX_BOOKS_PER_USER = 2;

    
    void addBook(Book book) {
        books.add(book);
        System.out.println("[+] Book added: \"" + book.title + "\"");
    }

    void registerUser(User user) {
        users.add(user);
        userBookCount.put(user.userId, 0);
        System.out.println("[+] User registered: " + user.name);
    }

    
    void issueBook(int bookId, int userId) {
        
        boolean userExists = false;
        for (User u : users) {
            if (u.userId == userId) {
                userExists = true;
                break;
            }
        }
        if (!userExists) {
            System.out.println("[!] User ID " + userId + " not found. Please register first.");
            return;
        }

        
        int count = userBookCount.getOrDefault(userId, 0);
        if (count >= MAX_BOOKS_PER_USER) {
            System.out
                    .println("[!] Limit reached. A user can hold at most " + MAX_BOOKS_PER_USER + " books at a time.");
            return;
        }

        
        for (Book b : books) {
            if (b.bookId == bookId) {
                if (b.isIssued) {
                    System.out.println("[!] Book \"" + b.title + "\" is already issued.");
                    return;
                }
                b.isIssued = true;
                issuedBooks.put(bookId, userId);
                userBookCount.put(userId, count + 1);
                System.out.println("[✓] Book \"" + b.title + "\" issued to user " + userId
                        + ". Books held: " + (count + 1) + "/" + MAX_BOOKS_PER_USER);
                return;
            }
        }
        System.out.println("[!] Book ID " + bookId + " not found.");
    }

    
    void returnBook(int bookId) {
        for (Book b : books) {
            if (b.bookId == bookId) {
                if (!b.isIssued) {
                    System.out.println("[!] Book \"" + b.title + "\" was not issued.");
                    return;
                }
                int userId = issuedBooks.get(bookId);
                b.isIssued = false;
                issuedBooks.remove(bookId);
                userBookCount.put(userId, userBookCount.getOrDefault(userId, 1) - 1);
                System.out.println("[✓] Book \"" + b.title + "\" returned by user " + userId + ".");

                // Fine logic: simulate delay (0–9 days)
                int daysLate = new Random().nextInt(10);
                if (daysLate > 5) {
                    int fine = (daysLate - 5) * 10;
                    System.out.println("[!] Late return by " + (daysLate - 5)
                            + " day(s). Fine: Rs." + fine);
                } else {
                    System.out.println("[✓] Returned on time. No fine.");
                }
                return;
            }
        }
        System.out.println("[!] Invalid book ID for return.");
    }

    
    void searchBook(String keyword) {
        boolean found = false;
        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword.toLowerCase())
                    || b.author.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  " + b.bookId + " | " + b.title
                        + " | Author: " + b.author
                        + " | " + (b.isIssued ? "Issued" : "Available"));
                found = true;
            }
        }
        if (!found)
            System.out.println("[!] No books found for keyword: \"" + keyword + "\"");
    }

    
    void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("[!] No books in library.");
            return;
        }
        System.out.println("\n  ID  | Title                  | Author      | Status");
        System.out.println("  ----|------------------------|-------------|--------");
        for (Book b : books) {
            System.out.printf("  %-4d| %-23s| %-12s| %s%n",
                    b.bookId, b.title, b.author, b.isIssued ? "Issued" : "Available");
        }
    }

    
    void showIssuedByUser(int userId) {
        boolean found = false;
        System.out.println("\n  Books currently issued to user " + userId + ":");
        for (Map.Entry<Integer, Integer> entry : issuedBooks.entrySet()) {
            if (entry.getValue() == userId) {
                for (Book b : books) {
                    if (b.bookId == entry.getKey()) {
                        System.out.println("  -> " + b.bookId + " | " + b.title + " by " + b.author);
                        found = true;
                    }
                }
            }
        }
        if (!found)
            System.out.println("  [!] No books currently issued to user " + userId + ".");
    }
}
