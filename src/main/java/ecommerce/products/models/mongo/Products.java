package ecommerce.products.models.mongo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Data
@Setter
@Document(collection = "products")
public class Products {

    @Id
    private String id;
    private String name;
    private String description;
    private int stocks;
    private String category;
    private BigDecimal price;
    private boolean available;
    private LocalDateTime dateCreated;


    public Products() {
    }

    public Products(String id, String name, String description, int stocks, String category, BigDecimal price, boolean available, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stocks = stocks;
        this.category = category;
        this.price = price;
        this.available = available;
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(String description) {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getCategory(String category) {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
