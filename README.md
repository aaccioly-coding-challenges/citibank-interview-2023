# Citibank coding challenge

This is a solution for a coding challenge that I've completed for Citibank while interviewing for the role of
Architecture Lead.

## Requirement

A piece of simple library code that accepts a salary in pounds and pence, and returns the amount of tax that is due, in
pounds and pence.

Tax is applied in bands.

### Tax Bands

* 0 - 10,000: 1.89%
* 10,000 - 20,000: 2.5%
* 20,000 - 50,000: 4.75%
* 50,000 - 100,000: 8.25%
* &gt; 100,000: 10.5%

Bands are **not** cumulative - ie. a salary of 15,000 falls in the 2.5% band, therefore tax due is 2.5% × 15,000.

### Details

* Code must be production ready, using core Java.
* Assume that the library code will be used by another team in their application.
* Work as you would if you were writing this as part of your day job.
* You may make use of resources on the Internet, e.g. Java API, Stack Overflow, etc.

### Further assumptions and clarifications from the interviews

* `BigDecimal` is a good choice for representing money.
* The floor of a band is inclusive. The ceiling of a band is exclusive. For example, a salary of 10,000 falls in the
  10,000 - 20,000 band.
* Round `HALF_UP` to the nearest penny.
* I didn't ask about negative salaries, but I assume that they are not allowed.
* Real world code would have logging, but I didn't add them to the solution because they weren't required.

## How to build

The project uses Gradle as a build tool. To build the project, run:

    ./gradlew build

## How to run tests

To run the tests, run:

    ./gradlew test

To rerun the tests when all tasks are up-to-date, run:

    ./gradlew --no-build-cache cleanTest test

This project uses Adarsh Ramamurthy's [Gradle Test Logger Plugin](https://github.com/radarsh/gradle-test-logger-plugin)
to produce more readable test output.

## Post interview improvements

1. Implemented the babd selection logic using  `NavigableMap#floorEntry` and `NavigableMap#ceilingEntry` as
   suggested during the interview.
2. Throw an `IllegalArgumentException` when the salary is negative.
3. Simplified tests by using `ParameterizedTest` to test tax calculation for each band.
