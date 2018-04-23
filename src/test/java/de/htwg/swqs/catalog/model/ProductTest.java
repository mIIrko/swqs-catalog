package de.htwg.swqs.catalog.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class ProductTest {

  @Test
  public void controllerTest() {

    // setup
    String name = "some product";
    String description = "some description";
    long id = 1L;
    BigDecimal priceEuro = new BigDecimal("3.14");

    // execute
    Product p = new Product(id, name, description, priceEuro);

    // verify
    assertEquals(id, p.getId());
    assertEquals(name, p.getName());
    assertEquals(description, p.getDescription());
    assertEquals(priceEuro, p.getPriceEuro());
  }
}
