package ecommerce.products.controllers;

import ecommerce.products.models.Product;
import ecommerce.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saveProduct = productRepository.save(product);
        return ResponseEntity.ok(saveProduct);
    }

    /**
     * Get products by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all products
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(products);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productRepository.findById(id).map(
                existingProduct -> {
                    existingProduct.productUpdateDetails(product);
                    return ResponseEntity.ok(productRepository.save(existingProduct));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
            return productRepository.findById(id).map(product -> {
                productRepository.delete(product);
                return ResponseEntity.ok("Product deleted successfully");
            }).orElse(
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found"));
    }
}
