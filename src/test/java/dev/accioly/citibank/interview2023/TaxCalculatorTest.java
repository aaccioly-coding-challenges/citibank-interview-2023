package dev.accioly.citibank.interview2023;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TaxCalculatorTest {


    @Test
    void taxOfaSalaryofZeroIsZero() {
        TaxCalculator taxCalculator = new TaxCalculator();

        BigDecimal salary = BigDecimal.ZERO;
        BigDecimal tax = taxCalculator.calculate(salary);

        String expectedTax = "0.00";
        assertThat(tax).isEqualTo(expectedTax);
    }

    @ParameterizedTest(name = "Band [{index}] - {0}")
    @CsvSource({
            "'[0 - 10,000)      :  Tax: 1.89%',  1000.00,    18.90",
            "'[10,000 - 20,000) :  Tax: 2.5%',   10000.00,   250.00",
            "'[20,000 - 50,000) :  Tax: 4.75%',  20000.00,   950.00",
            "'[50,000 - 100,000):  Tax: 8.25%',  50000.00,   4125.00",
            "'>= 100,000:       :  Tax: 10.5%',  100000.00,  10500.00"
    })
    void canCalculateTaxesInEachBand(String ignoredTestLabel, String salary, String expectedTax) {
        TaxCalculator taxCalculator = new TaxCalculator();

        BigDecimal tax = taxCalculator.calculate(new BigDecimal(salary));

        assertThat(tax).isEqualTo(expectedTax);
    }

    @Test
    void properlyRoundsTaxesToTwoDigits() {
        TaxCalculator taxCalculator = new TaxCalculator();

        BigDecimal salary = new BigDecimal("9999.99");
        BigDecimal tax = taxCalculator.calculate(salary);

        String expectedTax = "189.00";
        assertThat(tax).isEqualTo(expectedTax);
    }

    @Test
    void negativeSalaryThrowsException() {
        TaxCalculator taxCalculator = new TaxCalculator();

        BigDecimal salary = new BigDecimal("-1.00");
        assertThatThrownBy(() -> taxCalculator.calculate(salary))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Salary cannot be negative");
    }
}
