package de.htwg.swqs.catalog;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;


/*
 * The RunWith(SpringRunner.class) Annotation is used to provide a bridge between Spring Boot test features and JUnit.
 * Whenever using any Spring Boot testing features in the JUnit tests, this annotation will be required.
 */
@RunWith(SpringRunner.class)
/*
 * the @DataJpaTest Annotation provides some standard setup needed for testing the persistence layer:
 * <ul>
 *     <li>configuring H2, an in-memory database </li>
 *     <li>setting Hibernate, Spring Data, and the DataSource</li>
 *     <li>performing an @EntityScan</li>
 *     <li>turning on SQL logging</li>
 * </ul>
 */
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void findByIdAndReturnFoundProduct() {

        // setup
        Product sampleProduct = new Product(1, "Sample Product", "just a sample product", BigDecimal.valueOf(3.14));
        entityManager.persist(sampleProduct);
        entityManager.flush();

        // execute
        Product found = productRepository.findById(sampleProduct.getId()).get();

        // verify
        assertEquals(found.getId(), sampleProduct.getId());

        // teardown
        // is done by the @DirtiesContext Annotation at class level
    }

    @Test(expected = NoSuchElementException.class)
    public void findByIdAndNoProductFoundThrowsException() {

        // no setup needed, we want to find anything

        // execute
        // must throw a exception, because the Optional is empty
        Product found = productRepository.findById(1L).get();

        // verification is done by the expected exception

    }

    @Test
    public void findAllAndReturnAllProducts(){

        // setup
        Product sampleProduct = new Product(1, "Sample Product", "just a sample product", BigDecimal.valueOf(3.14));
        Product anotherSampleProduct = new Product(2, "Another sample Product", "just another sample product", BigDecimal.valueOf(33));
        entityManager.persist(sampleProduct);
        entityManager.persist(anotherSampleProduct);
        entityManager.flush();

        // execute
        List<Product> productList = productRepository.findAll();

        // verify
        assertTrue(productList.containsAll(Arrays.asList(sampleProduct, anotherSampleProduct)));

    }

    @Test
    public void findAllAndReturnEmptyList() {

        // no setup needed, we want to find anything

        // setup & verify
        assertTrue(productRepository.findAll().isEmpty());

    }

}
