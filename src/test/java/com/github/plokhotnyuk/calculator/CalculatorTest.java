package com.github.plokhotnyuk.calculator;

import com.googlecode.yatspec.junit.SpecRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpecRunner.class)
public class CalculatorTest extends BaseTest {
    final User user = new User();
    final Calculator calculator = new Calculator();
    final Calculator.Display display = calculator.display;

    @Test
    public void calculatorShouldBeTitledAndDisplaysZeroWhenStarted() {
        calculator.titled("Calculator");
        display.indicates("0");
    }

    @Test
    public void userCanEnterNumbersByPressingDigitButtons() {
        user.clicks("1");
        display.indicates("1");
        user.clicks("2");
        display.indicates("12");
        user.clicks("3");
        display.indicates("123");
        user.clicks("4");
        display.indicates("1234");
        user.clicks("5");
        display.indicates("12345");
        user.clicks("6");
        display.indicates("123456");
        user.clicks("7");
        display.indicates("1234567");
        user.clicks("8");
        display.indicates("12345678");
        user.clicks("9");
        display.indicates("123456789");
        user.clicks("0");
        display.indicates("1234567890");
    }

    @Test
    public void userCanEnterNumbersUsingKeyboardOrNumericKeypad() {
        user.types("1");
        display.indicates("1");
        user.types("2");
        display.indicates("12");
        user.types("3");
        display.indicates("123");
        user.types("4");
        display.indicates("1234");
        user.types("5");
        display.indicates("12345");
        user.types("6");
        display.indicates("123456");
        user.types("7");
        display.indicates("1234567");
        user.types("8");
        display.indicates("12345678");
        user.types("9");
        display.indicates("123456789");
        user.types("0");
        display.indicates("1234567890");
    }

    @Test
    public void userCanEnterFloatNumbers() {
        user.clicks("1");
        display.indicates("1");
        user.clicks(".");
        display.indicates("1.");
        user.clicks("2");
        display.indicates("1.2");
    }

    @Test
    public void userCanAddNumbers() {
        user.clicks("2+5=");
        display.indicates("7");
    }

    @Test
    public void userCanSubtractNumbers() {
        user.clicks("2-5=");
        display.indicates("-3");
    }

    @Test
    public void userCanMultiplyNumbers() {
        user.clicks("2*5=");
        display.indicates("10");
    }

    @Test
    public void userCanDivideNumbers() {
        user.clicks("1/8=");
        display.indicates("0.125");
    }

    @Test
    public void userCanCalculateWithPreviouslyCalculatedValue() {
        user.clicks("1+1=");
        user.clicks("/8=");
        display.indicates("0.25");
    }

    @Test
    public void userCanCalculateWithPreviouslyEnteredValueAndOperation() {
        user.clicks("8*2=");
        user.clicks("3=");
        display.indicates("24");
    }

    @Test
    public void userCanCalculateWithPreviouslyEnteredValuesAndOperation() {
        user.clicks("1+1=");
        user.clicks("=");
        display.indicates("3");
    }

    @Test
    @Ignore("pending")
    public void userCanCalculatePreviousOperationByEnteringNewOperation() {
        user.clicks("1+1+1=");
        display.indicates("3");
    }

    @Test
    public void userCanResetEnteredValuesAndOperation() {
        user.clicks("1C");
        display.indicates("0");
        user.clicks("1+C");
        display.indicates("0");
        user.clicks("1+1C");
        display.indicates("0");
        user.clicks("1+1=C");
        display.indicates("0");
    }

    @Test
    public void calculatorShouldDoNothingWhenCalculateButtonPressedAfterEnteringTheFirstNumber() {
        user.clicks("1=");
        display.indicates("1");
    }

    @Test
    public void calculatorShouldSkipDuplicatedDots() {
        user.clicks("2..");
        display.indicates("2.");
    }

    @Test
    public void calculatorShouldRemoveRedundantDotWithFollowingZeros() {
        user.clicks("1.1+9.9=");
        display.indicates("11");
    }

    @Test
    public void calculatorShouldRemoveLeadingZeros() {
        user.clicks("00");
        display.indicates("0");
        user.clicks("1");
        display.indicates("1");
    }

    @Test
    public void calculatorShouldNotRemoveLeadingZeroBeforeDot() {
        user.clicks(".1");
        display.indicates("0.1");
    }

    @Test
    public void calculatorShouldNotRemoveZerosAfterDotWhenEntering() {
        user.clicks("0.1000");
        display.indicates("0.1000");
        user.clicks("1");
        display.indicates("0.10001");
    }

    @Test
    public void calculatorShouldReportErrorWhenDivideByZero() {
        user.clicks("1/0=");
        display.indicates("Error");
    }

    @Test
    public void calculatorShouldLimitLengthOfEnteredNumbersToSixteenDigits() {
        user.clicks("12345678901234567");
        display.indicates("1234567890123456");
    }

    @Test
    public void calculatorShouldCalculateWithPrecisionUpToSixteenDigits() {
        user.clicks("1111111111111111*9=");
        display.indicates("9999999999999999");
        user.clicks("1/6=");
        display.indicates("0.1666666666666667");
    }

    @Test
    public void calculatorShouldReportErrorInCaseOfOverflow() {
        user.clicks("9999999999999999*9=");
        display.indicates("Error");
    }

    @Test
    public void calculatorShouldIndicate0InCaseOfUnderflow() {
        user.clicks("0.9/9999999999999999=");
        display.indicates("0");
    }
}