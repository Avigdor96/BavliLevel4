package Level4;

public class Bavli {
    private String name;
    private String path;
    private Book[] books;
    private String[] booksNames;

    public Bavli() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public String[] getBooksNames() {
        return booksNames;
    }

    public void setBooksNames(String[] booksNames) {
        this.booksNames = booksNames;
    }
}
