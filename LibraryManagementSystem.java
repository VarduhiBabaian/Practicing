public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add some books
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            library.addBook(new Book("Girq" + i, "Hexinak" + i, "ISBN" + i));
        }
        long endTime = System.nanoTime();
        System.out.println("Time taken to add 1000 books: " + (endTime - startTime) + " ns");

        // Another set
        long startTime1 = System.nanoTime();
        for (int i = 1000; i < 20000; i++) {
            library.addBook(new Book("Girq" + i, "Hexinak" + i, "ISBN" + i));
        }
        long endTime1 = System.nanoTime();
        System.out.println("Time taken to add 19000 books: " + (endTime1 - startTime1) + " ns");

        // Checkout and return books
        library.checkoutBook("ISBN1");

        // Measure time for checking out books
        startTime = System.nanoTime();
        library.checkoutBook("ISBN3");
        endTime = System.nanoTime();
        System.out.println("Time taken to check out a book: " + (endTime - startTime) + " ns");

        library.returnBook("ISBN1");

        // Display lists
        library.displayAvailableBooks();
        library.displayCheckedOutBooks();

        // Borrowing history
        library.displayBorrowingHistory();
    }
}