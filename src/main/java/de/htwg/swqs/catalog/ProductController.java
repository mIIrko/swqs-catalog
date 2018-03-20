package de.htwg.swqs.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> productList= this.productRepository.findAll();
        LOGGER.info("Generic endpoint for all products called");

        if (productList.isEmpty()) {
            LOGGER.info("product list is empty");
            return productList;
        } else {
            return productList;
        }
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            LOGGER.info("product with id {} not found", id);
            // todo: error handling
            return null;
        }
    }

    /**
     * Returns a list of products that contains the {@code name}(or part of it)
     *
     *
     * @param name  name parameter in the uri
     * @return      a list with the products containing the given name
     */
    @GetMapping(params = "name")
    public List<Product> getProductByName(@RequestParam(value = "name") String name) {
        LOGGER.info("Endpoint for searching products by specific name called");
        List<Product> productList = this.productRepository.findByNameIsContaining(name);
        if (productList.isEmpty()) {
            LOGGER.info("product with name {} not found", name);
            return productList;
        } else {
            return productList;
        }

    }
}
