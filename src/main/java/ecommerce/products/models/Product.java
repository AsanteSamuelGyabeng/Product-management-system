package ecommerce.products.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name="stocks" , nullable = false)
    private int stocks;
    private BigDecimal price;
    private boolean available;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    //default constructor
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
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


    public void productUpdateDetails(Product product){
        this.name = product.getName();
        this.description = product.getDescription();
        this.stocks = product.getStocks();
        this.price = product.getPrice();
        this.available = product.isAvailable();
    }


}
