package jo.seongju.books.core.book;

import java.io.Serializable;

/**
 * Created by Seongju Jo. On 2020.02.29 18:15
 */
public class Book implements Serializable {

    private String author;

    private String title;

    private String contents;

    private String isbn;

    private int price;

    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
