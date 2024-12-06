package ecommerce.products.services;

import ecommerce.products.exceptions.ResourceNotFoundException;
import ecommerce.products.models.Product;
import ecommerce.products.models.User;
import ecommerce.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * @updateShopProducts - update shop products
     * @param id
     * @param product
     * @return
     * @throws ResourceNotFoundException
     */
    public Product updateShopProduct(Long id, Product product) throws ResourceNotFoundException {
        Optional<Product> findProduct = productRepository.findById(id);

        if (findProduct.isPresent()) {
            Product existingProduct = findProduct.get();

            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setStocks(product.getStocks());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setAvailable(product.isAvailable());
            return productRepository.save(existingProduct);
        }else {
            throw new ResourceNotFoundException("Product not found with id: ");
        }

    }

    /**
     * @getProduct - get product by id
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public Product getProduct(Long id) throws ResourceNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }else{
            throw new ResourceNotFoundException("Product not found with id: ");
        }
    }

    /**
     * @getAllProducts - get all products
     * @return
     * @throws ResourceNotFoundException
     */
    public Page<Product> getAllProducts(Pageable pageable) throws ResourceNotFoundException {
      Page<Product> products = productRepository.findAll(pageable);
        if(!products.isEmpty()) {
            return products;
        }else{
            throw new ResourceNotFoundException("Product page is empty");
        }
    }


    /**
     * @deleteProduct - deletes product based on the id
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public boolean deleteProduct(Long id) throws ResourceNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
           productRepository.delete(product.get());
           return true;
        }else{
            throw new ResourceNotFoundException("No products found");
        }
    }

}


