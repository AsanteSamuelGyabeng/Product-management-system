package ecommerce.products.binarytree;

import ecommerce.products.models.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductBinaryTree {
    private ProductTreeNode root;

    public void add(Product product) {
        root = addRecursive(root, product);
    }

    private ProductTreeNode addRecursive(ProductTreeNode current, Product product) {
        if (current == null) {
            return new ProductTreeNode(product);
        }

        int comparison = product.getPrice().compareTo(current.getProducts().get(0).getPrice());
        if (comparison < 0) {
            current.setLeft(addRecursive(current.getLeft(), product));
        } else if (comparison > 0) {
            current.setRight(addRecursive(current.getRight(), product));
        } else {
            // If prices are equal, add product to the list in the current node
            current.getProducts().add(product);
        }
        return current;
    }


    public List<Product> search(BigDecimal price) {
        List<Product> matchingProducts = new ArrayList<>();
        searchRecursive(root, price, matchingProducts);
        return matchingProducts;
    }



    private void searchRecursive(ProductTreeNode current, BigDecimal price, List<Product> matchingProducts) {
        if (current == null) {
            return;
        }

        int comparison = price.compareTo(current.getProducts().get(0).getPrice());
        if (comparison == 0) {
            matchingProducts.addAll(current.getProducts());
        }

        if (comparison <= 0) {
            searchRecursive(current.getLeft(), price, matchingProducts);
        }
        if (comparison >= 0) {
            searchRecursive(current.getRight(), price, matchingProducts);
        }
    }



    public void delete(BigDecimal price) {
        root = deleteRecursive(root, price);
    }




    private ProductTreeNode deleteRecursive(ProductTreeNode current, BigDecimal price) {
        if (current == null) {
            return null;
        }

        int comparison = price.compareTo(current.getProducts().get(0).getPrice());
        if (comparison == 0) {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            Product smallestProduct = findSmallestProduct(current.getRight());
            current.setProducts(new ArrayList<>(List.of(smallestProduct)));
            current.setRight(deleteRecursive(current.getRight(), smallestProduct.getPrice()));
            return current;
        }

        if (comparison < 0) {
            current.setLeft(deleteRecursive(current.getLeft(), price));
            return current;
        }

        current.setRight(deleteRecursive(current.getRight(), price));
        return current;
    }



    private Product findSmallestProduct(ProductTreeNode root) {
        return root.getLeft() == null ? root.getProducts().get(0) : findSmallestProduct(root.getLeft());
    }
}
