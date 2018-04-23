package de.htwg.swqs.catalog.utils;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String exception) {
    super(exception);
  }
}
