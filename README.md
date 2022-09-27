# Principles of operation application CALCULATOR

There are 3 endpoints in application, which enable cooperation with database.

PUT:
- add calculation if not exist in DB and return result as a body response
- if calculation in db exist, take result and return as a body responses
- required input string format: {number}{unit}{symbol}{number}{unit}
- where {number} is float number, {unit} is ft or NM or m, {symbol} is + or - or * or /


To test endopoints: 
- When you build application, run Swagger localhost:8080/swagger-ui/#
  - > [Swagger Ui](http://localhost:8080/swagger-ui/#)
  - from the list choose: calculator-controller and then choose request HTTP
  - Click: Try it out, and then Execute
  - for every request is possible to provide all length unit (ft,NM,m), but return unit which is (assigned) in the path /api/ft return ft, /api/m return m, /api/nm return NM

- The application is using H2 database, which is run locally: http://localhost:8080/h2-ui
  - > [H2 Console](http://localhost:8080/h2-ui)
  - At the beginning database is empty
  - in console you can monitor endpoints
  - after application is terminated database will be cleaned out

## Application based on Spring Boot, Maven, H2.
## Run Spring Boot application
```
mvn spring-boot:run
```
or run CalculatorApplication.java in direct calculator
