package ecommerce.products.models;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Data
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int stocks;
    private BigDecimal price;
    private boolean available;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    public Product() {
    }

    public Product(Long id, String name, String description, int stocks, BigDecimal price, boolean available, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stocks = stocks;
        this.price = price;
        this.available = available;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getStocks() {
        return stocks;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
