package de.htwg.swqs.catalog.service;

import de.htwg.swqs.catalog.model.Product;
import de.htwg.swqs.catalog.repository.CatalogRepository;
import de.htwg.swqs.catalog.utils.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException{
        List<Product> productList = this.catalogRepository.findAll();

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("No products available");
        }
        return productList;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException{
        Optional<Product> product = this.catalogRepository.findById(id);

        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product.get();
    }

    @Override
    public List<Product> getProductByName(String name) throws ProductNotFoundException{
        List<Product> productList = this.catalogRepository.findByNameIsContaining(name);

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("No products containing '" + name + "' in name found");
        }
        return productList;
    }
}
