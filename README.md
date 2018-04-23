[![pipeline status](https://gitlab.in.htwg-konstanz.de/mibay/swqs-catalog/badges/master/pipeline.svg)](https://gitlab.in.htwg-konstanz.de/mibay/swqs-catalog/commits/master)
[![coverage report](https://gitlab.in.htwg-konstanz.de/mibay/swqs-catalog/badges/master/coverage.svg)](https://gitlab.in.htwg-konstanz.de/mibay/swqs-catalog/commits/master)


# swqs-catalog
Component that delivers the products for the shop

### description
This component is the simplest one, it got no dependencies to the other components. It is shipped with just a configuration, no main entry point. It can be run through the different unit and component tests.

### usage
All interfaces the component owns are defined in the `CatalogService` and can be called through this.

### build instructions
To build this project run `mvn install`. This runs also all unit and integration tests.

### reports
The project includes multiple maven code analyzing plugins. When running `mvn site` a html site with all reports, test results and the javadoc will be generated.




_____

Mirko Bay, 2018