package ecommerce.products.repository.mongorepo;

import ecommerce.products.models.mongo.Products;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface MongoProductRepository extends MongoRepository<Products, String> {

}
