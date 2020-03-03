package jo.seongju.books.kakao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by Seongju Jo. On 2020.02.29 21:35
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoBook {

    private String[] authors;

    private String title;

    private String contents;

    private String isbn;

    private int price;

    @JsonProperty("sale_price")
    private int salePrice;

    private String publisher;

    private String[] translators;

    private String url;

    @JsonProperty("thumbnail")
    private String thumbnailUrl;

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
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

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getTranslators() {
        return translators;
    }

    public void setTranslators(String[] translators) {
        this.translators = translators;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "KakaoBook{" +
                "authors=" + Arrays.toString(authors) +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", publisher='" + publisher + '\'' +
                ", translators=" + Arrays.toString(translators) +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
