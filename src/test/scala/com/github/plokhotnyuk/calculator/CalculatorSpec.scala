package com.github.plokhotnyuk.calculator

class CalculatorSpec extends BaseSpec {
  "User can enter numbers" in {
    "by pressing digit buttons" in {
      User clicks "1"
      Display indicates "1"
      User clicks "2"
      Display indicates "12"
      User clicks "3"
      Display indicates "123"
      User clicks "4"
      Display indicates "1234"
      User clicks "5"
      Display indicates "12345"
      User clicks "6"
      Display indicates "123456"
      User clicks "7"
      Display indicates "1234567"
      User clicks "8"
      Display indicates "12345678"
      User clicks "9"
      Display indicates "123456789"
      User clicks "0"
      Display indicates "1234567890"
    }

    "using keyboard or numeric keypad" in {
      User types "1"
      Display indicates "1"
      User types "2"
      Display indicates "12"
      User types "3"
      Display indicates "123"
      User types "4"
      Display indicates "1234"
      User types "5"
      Display indicates "12345"
      User types "6"
      Display indicates "123456"
      User types "7"
      Display indicates "1234567"
      User types "8"
      Display indicates "12345678"
      User types "9"
      Display indicates "123456789"
      User types "0"
      Display indicates "1234567890"
    }

    "float numbers also supported" in {
      User clicks "1"
      Display indicates "1"
      User clicks "."
      Display indicates "1."
      User clicks "2"
      Display indicates "1.2"
    }
  }

  "User can do basic arithmetic operations with entered numbers" in {
    "adding" in {
      User clicks "2+5="
      Display indicates "7"
    }

    "subtraction" in {
      User clicks "2-5="
      Display indicates "-3"
    }

    "multiplying" in {
      User clicks "2*5="
      Display indicates "10"
    }

    "division" in {
      User clicks "1/8="
      Display indicates "0.125"
    }
  }

  "User can calculate complex statements with minimum effors" in {
    "using previously calculated value" in {
      User clicks "1+1="
      User clicks "/8="
      Display indicates "0.25"
    }

    "using previously entered value and operation" in {
      User clicks "8*2="
      User clicks "3="
      Display indicates "24"
    }

    "using previously entered values and operation" in {
      User clicks "1+1="
      User clicks "="
      Display indicates "3"
    }

    "by calculating previous operation when entering new operation" in {
      skipped("because not implemented yet")
      User clicks "1+1+1="
      Display indicates "3"
    }
  }

  "Calculator should have initial state" in {
    "on start should be titled and displays 0 when started" in {
      Calculator titled "Calculator"
      Display indicates "0"
    }

    "after pressing reset button" in {
      User clicks "1C"
      Display indicates "0"
      User clicks "1+C"
      Display indicates "0"
      User clicks "1+1C"
      Display indicates "0"
      User clicks "1+1=C"
      Display indicates "0"
    }

    "should do nothing when calculate button pressed after entering the first number" in {
      User clicks "1="
      Display indicates "1"
    }
  }

  "Calculator should properly format numbers" in {
    "redundant dot with following zeros should be removed" in {
      User clicks "1.1+9.9="
      Display indicates "11"
    }

    "duplicated dots should be skipped" in {
      User clicks "2.."
      Display indicates "2."
    }

    "leading zeros should be removed for whole numbers" in {
      User clicks "00"
      Display indicates "0"
      User clicks "1"
      Display indicates "1"
    }

    "leading zero before dot should not be removed" in {
      User clicks ".1"
      Display indicates "0.1"
    }

    "zeros after dot should not be removed when entering" in {
      User clicks "0.1000"
      Display indicates "0.1000"
      User clicks "1"
      Display indicates "0.10001"
    }
  }

  "Calculator should properly behave on edge cases" in {
    "by reporting error when divide by zero" in {
      User clicks "1/0="
      Display indicates "Error"
    }

    "by limiting length of entered numbers to 16 digits" in {
      User clicks "12345678901234567"
      Display indicates "1234567890123456"
    }

    "by calculating with precision up 16 digits" in {
      User clicks "111111111111111*9="
      Display indicates "999999999999999"
      User clicks "1/6="
      Display indicates "0.1666666666666667"
    }

    "by reporting of error in case of overflow" in {
      User clicks "9999999999999999*9="
      Display indicates "Error"
    }

    "by indicating 0 in case of underflow" in {
      User clicks "0.9/9999999999999999="
      Display indicates "0"
    }
  }
}