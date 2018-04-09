package de.htwg.swqs.catalog.repository;

import de.htwg.swqs.catalog.CatalogConfiguration;
import de.htwg.swqs.catalog.model.Product;
import de.htwg.swqs.catalog.repository.CatalogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
@SpringBootTest(classes = CatalogConfiguration.class)
@DataJpaTest
public class CatalogRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatalogRepository catalogRepository;

    @Test
    public void findByIdAndReturnFoundProduct() {

        // setup
        Product sampleProduct = new Product(1, "Sample Product", "just a sample product", BigDecimal.valueOf(3.14));
        entityManager.persist(sampleProduct);
        entityManager.flush();
        // this.catalogRepository.saveAndFlush(sampleProduct);
        // execute
        Product found = catalogRepository.findById(sampleProduct.getId()).get();

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
        Product found = catalogRepository.findById(1L).get();

        // verification is done by the expected exception

    }

    @Test
    public void findAllAndReturnAllProducts() {

        // setup
        Product sampleProduct = new Product(1, "Sample Product", "just a sample product", BigDecimal.valueOf(3.14));
        Product anotherSampleProduct = new Product(2, "Another sample Product", "just another sample product", BigDecimal.valueOf(33));
        entityManager.persist(sampleProduct);
        entityManager.persist(anotherSampleProduct);
        entityManager.flush();


        // execute
        List<Product> productList = catalogRepository.findAll();

        // verify
        assertTrue(productList.containsAll(Arrays.asList(sampleProduct, anotherSampleProduct)));

    }

    @Test
    public void findAllAndReturnEmptyList() {

        // no setup needed, we want to find anything
        // entityManager.clear();

        // execute
        // List<Product> list = catalogRepository.findAll();

        // verify
        // assertTrue(list.isEmpty());

    }

}
