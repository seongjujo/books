package jo.seongju.books.naver.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Seongju Jo. On 2020.02.29 23:04
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverBook {

    private String title;

    private String author;

    private int price;

    private String publisher;

    private String isbn;

    private String description;

    private String link;

    @JsonProperty("image")
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "NaverBook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
