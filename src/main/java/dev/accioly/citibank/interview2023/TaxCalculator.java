package dev.accioly.citibank.interview2023;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TaxCalculator {

    private static final NavigableMap<BigDecimal, BigDecimal> TAX_BAND_THRESHOLDS;
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    static {
        var tempMap = new TreeMap<BigDecimal, BigDecimal>();
        tempMap.put(BigDecimal.ZERO, new BigDecimal("0.0189"));
        tempMap.put(new BigDecimal("10000.00"), new BigDecimal("0.0250"));
        tempMap.put(new BigDecimal("20000.00"), new BigDecimal("0.0475"));
        tempMap.put(new BigDecimal("50000.00"), new BigDecimal("0.0825"));
        tempMap.put(new BigDecimal("100000.00"), new BigDecimal("0.105"));

        TAX_BAND_THRESHOLDS = Collections.unmodifiableNavigableMap(tempMap);
    }

    public BigDecimal calculate(BigDecimal salary) {
        if (salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        BigDecimal taxPercentage = getTaxPercentage(salary);

        return salary.multiply(taxPercentage)
                .setScale(SCALE, ROUNDING_MODE);
    }

    private BigDecimal getTaxPercentage(BigDecimal value) {
        return TAX_BAND_THRESHOLDS.floorEntry(value).getValue();
    }
}
