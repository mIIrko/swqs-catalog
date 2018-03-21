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


    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> productList= this.productRepository.findAll();

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("No products available");
        }
        return productList;

    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product.get();

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
        List<Product> productList = this.productRepository.findByNameIsContaining(name);

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("No products containing '" + name + "' in name found");
        }
        return productList;


    }
}
