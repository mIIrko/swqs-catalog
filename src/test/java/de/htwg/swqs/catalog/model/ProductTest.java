package de.htwg.swqs.catalog.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.junit.Test;

/**
 * Here we demonstrate how to test a pojo.
 *
 */
public class ProductTest {

  @Test
  public void setterAndGetterTest() {
    // setup
    String name = "some product";
    String description = "some description";
    long id = 1L;
    BigDecimal priceEuro = new BigDecimal("3.14");
    Product p = new Product();

    // execute
    p.setId(id);
    p.setName(name);
    p.setDescription(description);
    p.setPriceEuro(priceEuro);

    long retrievedId = p.getId();
    String retrievedName = p.getName();
    String retrievedDescription = p.getDescription();
    BigDecimal retrievedPriceEuro = p.getPriceEuro();

    // verify
    assertEquals(id, retrievedId);
    assertEquals(name, retrievedName);
    assertEquals(description, retrievedDescription);
    assertEquals(priceEuro, retrievedPriceEuro);
  }

  @Test
  public void controllerWithParameterTest() {

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

  @Test
  public void toStringTest() {
    // setup
    String name = "some product";
    String description = "some description";
    long id = 1L;
    BigDecimal priceEuro = new BigDecimal("3.14");
    Product p = new Product(id, name, description, priceEuro);

    // execute
    String returnedString = p.toString();

    assertTrue(returnedString.contains(String.valueOf(id)));
    assertTrue(returnedString.contains(name));
    assertTrue(returnedString.contains(description));
    assertTrue(returnedString.contains(priceEuro.toString()));
  }

  @Test
  public void hashCodeTest() {
    // setup
    Product p = new Product(1L, "some name", "some description", new BigDecimal("0.99"));
    Product o = new Product(2L, "some name", "some description", new BigDecimal("0.99"));

    // execute
    int hashP = p.hashCode();
    int hashO = o.hashCode();

    // verify
    assertNotEquals(hashP, hashO);
    assertEquals(hashP, p.hashCode());
  }

  @Test
  public void equalsTestWithSameObject() {
    // setup
    Product p = new Product(1L, "some name", "some description", new BigDecimal("0.99"));

    // verify
    assertTrue(p.equals(p));
  }

  @Test
  public void equalsTestWithSameProperties() {
    // setup
    Product p = new Product(1L, "some name", "some description", new BigDecimal("0.99"));
    Product o = new Product(1L, "some name", "some description", new BigDecimal("0.99"));

    // verify
    assertTrue(p.equals(o));
  }

  @Test
  public void equalsTestWithDifferentObject() {
    // setup
    Product p = new Product(1L, "some name", "some description", new BigDecimal("0.99"));
    Product o = new Product(2L, "another name", "another description", new BigDecimal("0.99"));

    // verify
    assertFalse(p.equals(o));
  }

  @Test
  public void equalsTestWithNull() {
    // setup
    Product p = new Product(1L, "some name", "some description", new BigDecimal("0.99"));

    // verify
    assertFalse(p.equals(null));
  }



}
