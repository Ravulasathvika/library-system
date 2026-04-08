
class Book {

    int     bookId;
    String  title;
    String  author;
    boolean isIssued;

    Book(int bookId, String title, String author) {
        this.bookId   = bookId;
        this.title    = title;
        this.author   = author;
        this.isIssued = false;
    }
}
