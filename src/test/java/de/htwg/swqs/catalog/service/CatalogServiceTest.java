package de.htwg.swqs.catalog.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.htwg.swqs.catalog.model.Product;
import de.htwg.swqs.catalog.repository.CatalogRepository;
import de.htwg.swqs.catalog.utils.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

public class CatalogServiceTest {

  private List<Product> productList = Arrays.asList(
      new Product(1, "A", "aaa", new BigDecimal("1")),
      new Product(2, "B", "bbb", new BigDecimal("2")),
      new Product(2, "C", "ccc", new BigDecimal("3")),
      new Product(2, "D", "ddd", new BigDecimal("4")),
      new Product(2, "E", "eee", new BigDecimal("5")),
      new Product(2, "F", "fff", new BigDecimal("6"))
  );

  @Test
  public void getAllProductsSuccessful() {
    // setup
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    CatalogService catalogService = new CatalogServiceImpl(catalogRepository);
    when(catalogRepository.findAll()).thenReturn(productList);

    // execute
    List<Product> retrievedList = catalogService.getAllProducts();

    // verify
    assertEquals(productList, retrievedList);
  }

  @Test(expected = ProductNotFoundException.class)
  public void getAllProductsAndRetrieveEmptyList() {
    // setup
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    CatalogService catalogService = new CatalogServiceImpl(catalogRepository);
    when(catalogRepository.findAll()).thenReturn(new ArrayList<>());

    // execute
    List<Product> retrievedList = catalogService.getAllProducts();

    // verify -> the 'expected' parameter in test annotation
  }

  @Test
  public void getProductByIdSuccessful() {
    // setup
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    CatalogService catalogService = new CatalogServiceImpl(catalogRepository);
    Product sampleProduct = new Product(42, "TestProduct", "bla bla", new BigDecimal("54"));
    when(catalogRepository.findById(anyLong())).thenReturn(Optional.of(sampleProduct));

    // execute
    Product retrievedProduct = catalogService.getProductById(42);

    // verify
    assertEquals(sampleProduct, retrievedProduct);
  }

  @Test(expected = ProductNotFoundException.class)
  public void getProductByIdThrowsException() {
    // setup
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    CatalogService catalogService = new CatalogServiceImpl(catalogRepository);
    when(catalogRepository.findById(anyLong())).thenReturn(Optional.empty());

    // execute
    Product retrievedProduct = catalogService.getProductById(42);

    // verify -> the 'expected' parameter in test annotation
  }
}
