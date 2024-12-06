package ecommerce.products.controllers;

import ecommerce.products.exceptions.ResourceNotFoundException;
import ecommerce.products.models.Product;
import ecommerce.products.repository.ProductRepository;
import ecommerce.products.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {


   private final ProductRepository productRepository;
   private final ProductService productService;


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


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ResourceNotFoundException {
            Product updatedProduct = productService.updateShopProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ResourceNotFoundException{
            boolean deletedProduct = productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
    }




}
