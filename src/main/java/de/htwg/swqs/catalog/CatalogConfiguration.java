package de.htwg.swqs.catalog;

import de.htwg.swqs.catalog.repository.CatalogRepository;
import de.htwg.swqs.catalog.service.CatalogService;
import de.htwg.swqs.catalog.service.CatalogServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@ComponentScan("de.htwg.swqs.catalog")
@EnableJpaRepositories("de.htwg.swqs.catalog.repository")
@EntityScan("de.htwg.swqs.catalog.model")
public class CatalogConfiguration {

  @Bean
  public CatalogService catalogService(CatalogRepository catalogRepository) {
    return new CatalogServiceImpl(catalogRepository);
  }

}
