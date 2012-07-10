package com.github.plokhotnyuk.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * A model of calculator that allow entering values and calculate simple arithmetic functions.
 */
public class CalculatorModel {
    private static final int PRECISION = 16;
    private static final BigDecimal MAX_VALUE = new BigDecimal("9999999999999999");
    private static final BigDecimal MIN_VALUE = new BigDecimal("0.0000000000000001");

    private String prevValue, currValue, operation;
    private boolean isNewValue;

    /**
     * Creates calculator model in initial state displaying 0.
     */
    public CalculatorModel() {
        reset();
    }

    /**
     * Returns current entered or calculated value to display.
     *
     * @return the string representation of value to display
     */
    public String getCurrValue() {
        return currValue;
    }

    /**
     * Process pressing of calculator button.
     *
     * @param name the name of pressed button
     */
    public void pressButton(String name) {
        if ("C".equals(name)) {
            reset();
        } else if ("=".equals(name)) {
            completeOperation();
        } else if ("+".equals(name) || "-".equals(name) || "*".equals(name) || "/".equals(name)) {
            setOperation(name);
        } else {
            appendDigit(name);
        }
    }

    private void reset() {
        currValue = "0";
        setOperation("=");
    }

    private void appendDigit(String digit) {
        String num = isNewValue && !digit.equals(".") ? "" : currValue;
        if (isDuplicatedDot(num, digit) || isLengthMaximal(num)) {
            throw new IllegalArgumentException();
        }
        currValue = dropLeftZeros(num + digit);
        isNewValue = false;
    }

    private void setOperation(String op) {
        operation = op;
        prevValue = currValue;
        isNewValue = true;
    }

    private void completeOperation() {
        try {
            currValue = dropRightZeros(round(evaluate()).toString());
        } catch (RuntimeException ex) {
            currValue = "Error";
        }
        isNewValue = true;
    }

    private BigDecimal evaluate() {
        BigDecimal x = new BigDecimal(prevValue);
        BigDecimal y = new BigDecimal(currValue);
        BigDecimal z;
        switch (operation.charAt(0)) {
            case '+':
                z = x.add(y);
                break;
            case '-':
                z = x.subtract(y);
                break;
            case '*':
                z = x.multiply(y);
                break;
            case '/':
                z = x.divide(y, new MathContext(PRECISION));
                break;
            default:
                z = y;
        }
        return z;
    }

    private BigDecimal round(BigDecimal value) {
        BigDecimal roundedValue = value.round(new MathContext(PRECISION));
        if (roundedValue.abs().compareTo(MAX_VALUE) > 0) {
            throw new IllegalArgumentException();
        }
        return roundedValue.abs().compareTo(MIN_VALUE) < 0 ? BigDecimal.ZERO : roundedValue;
    }

    private boolean isLengthMaximal(String num) {
        return num.replace(".", "").length() == PRECISION;
    }

    private boolean isDuplicatedDot(String num, String digit) {
        return num.contains(".") && digit.equals(".");
    }

    private String dropRightZeros(String num) {
        if (num.contains(".") && (num.endsWith("0") || num.endsWith("."))) {
            return dropRightZeros(num.substring(0, num.length() - 1));
        }
        return num;
    }

    private String dropLeftZeros(String num) {
        if (num.startsWith("0") && num.length() > 1 && !num.startsWith("0.")) {
            return dropLeftZeros(num.substring(1));
        }
        return num;
    }
}