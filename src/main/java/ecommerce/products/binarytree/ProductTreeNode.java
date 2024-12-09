package ecommerce.products.binarytree;

import ecommerce.products.models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ProductTreeNode {
    private List<Product> products; // List to handle duplicate prices
    private ProductTreeNode left;
    private ProductTreeNode right;

    public ProductTreeNode(Product product) {
        this.products = new ArrayList<>();
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProductTreeNode getLeft() {
        return left;
    }

    public void setLeft(ProductTreeNode left) {
        this.left = left;
    }

    public ProductTreeNode getRight() {
        return right;
    }

    public void setRight(ProductTreeNode right) {
        this.right = right;
    }
}
