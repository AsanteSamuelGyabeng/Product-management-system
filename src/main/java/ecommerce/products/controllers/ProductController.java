package ecommerce.products.controllers;

import ecommerce.products.exceptions.ResourceNotFoundException;
import ecommerce.products.models.Product;
import ecommerce.products.models.mongo.Products;
import ecommerce.products.repository.ProductRepository;
import ecommerce.products.repository.mongorepo.MongoProductRepository;
import ecommerce.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {

@Autowired
   private ProductRepository productRepository;
   private MongoProductRepository mongoProductRepository;
   private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @createProduct - create or add a new product
     * @param product
     * @return
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }


    /**
     * @searchProductByPrice - search product by price using binary tree
     * @param price
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProductsByPrice(
            @RequestParam BigDecimal price,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Product> products = productService.searchProductsByPrice(price, page, size, sort,direction);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    /**
     * Get products by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id)throws ResourceNotFoundException{
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Get all products
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) throws ResourceNotFoundException {
        Page<Product> products = productService.getAllProducts(PageRequest.of(page,size));
        return ResponseEntity.ok(products);
    }


    /**
     * @updateProduct - updates the product
     * @param id
     * @param product
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ResourceNotFoundException {
            Product updatedProduct = productService.updateShopProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
    }

    /**
     * @deleteProduct - deletes the product
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ResourceNotFoundException{
            boolean deletedProduct = productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
    }




}
