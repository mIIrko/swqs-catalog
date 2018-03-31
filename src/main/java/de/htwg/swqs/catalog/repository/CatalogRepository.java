package de.htwg.swqs.catalog.repository;

import de.htwg.swqs.catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * https://projects.spring.io/spring-data-jpa/
 *
 * The repository interface to access the product database;
 * with spring data we need only the interface, all the default crud operations are deleted by default.
 *
 * Only custom queries has to be declared!
 *
 */
public interface CatalogRepository extends JpaRepository<Product, Long> {

    // findAll & findById is implemented by spring data

    List<Product> findByNameIsContaining(String name);

}



