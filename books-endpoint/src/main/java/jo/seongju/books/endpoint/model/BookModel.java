package jo.seongju.books.endpoint.model;

import jo.seongju.books.core.book.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seongju Jo. On 2020.03.03 07:04
 */
public class BookModel {

    private String author;

    private String title;

    private String contents;

    private String isbn;

    private int price;

    private String url;

    public BookModel(Book book) {
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.contents = book.getContents();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
        this.url = book.getUrl();
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public static BookModel create(Book book) {
        if (book == null) {
            return null;
        }

        return new BookModel(book);
    }

    public static List<BookModel> create(List<Book> books) {

        if (books == null || books.isEmpty()) {
            return null;
        }

        List<BookModel> models = new ArrayList<>(books.size());

        for (Book book : books) {
            models.add(create(book));
        }

        return models;
    }
}
