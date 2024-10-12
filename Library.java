class Library {
    static class Node {
        Book data;
        Node next;

        public Node(Book data) {
            this.data = data;
            this.next = null;
        }
    }

    Node bookListHead;
    Node waitingListHead;
    Node borrowingHistoryTop;

    // Using LinkedList to add books
    public void addBook(Book book) {
        Node newNode = new Node(book);
        newNode.next = bookListHead;
        bookListHead = newNode;
    }

    //Using Queue for checking out and returning books
    public void checkoutBook(String ISBN) {
        Node current = bookListHead;
        while (current != null) {
            if (current.data.ISBN.equals(ISBN) && current.data.isAvailable) {
                current.data.isAvailable = false;

                Node newNode = new Node(current.data);
                newNode.next = waitingListHead;
                waitingListHead = newNode;

                return;
            }
            current = current.next;
        }
        System.out.println("Book with ISBN " + ISBN + " is not available.");
    }

    public void returnBook(String ISBN) {
        Node current = bookListHead;
        while (current != null) {
            if (current.data.ISBN.equals(ISBN) && !current.data.isAvailable) {
                current.data.isAvailable = true;

                // Check if there are people waiting for this book
                if (waitingListHead != null) {
                    Node nextBookInQueue = waitingListHead;
                    waitingListHead = waitingListHead.next;

                    Node newNode = new Node(nextBookInQueue.data);
                    newNode.next = borrowingHistoryTop;
                    borrowingHistoryTop = newNode;
                }
                return;
            }
            current = current.next;
        }
        System.out.println("Invalid return: Book with ISBN " + ISBN + " not found.");
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        Node current = bookListHead;
        while (current != null) {
            if (current.data.isAvailable) {
                System.out.println(current.data.title + " by " + current.data.author + "(ISBN: " + current.data.ISBN + ")");
            }
            current = current.next;
        }
    }

    public void displayCheckedOutBooks() {
        System.out.println("Checked Out Books:");
        Node current = waitingListHead;
        while (current != null) {
            System.out.println(current.data.title + " by " + current.data.author);
            current = current.next;
        }
    }

    public void displayBorrowingHistory() {
        System.out.println("Borrowing History:");
        Node current = borrowingHistoryTop;
        while (current != null) {
            System.out.println(current.data.title + " by " + current.data.author);
            current = current.next;
        }
    }
}