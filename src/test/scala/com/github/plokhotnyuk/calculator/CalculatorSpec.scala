package com.github.plokhotnyuk.calculator

class CalculatorSpec extends BaseSpec {
  "User can enter numbers by pressing digit buttons" in {
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

  "User can enter numbers using keyboard or numeric keypad" in {
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

  "User can enter float numbers" in {
    User clicks "1"
    Display indicates "1"
    User clicks "."
    Display indicates "1."
    User clicks "2"
    Display indicates "1.2"
  }

  "User can add numbers" in {
    User clicks "2+5="
    Display indicates "7"
  }

  "User can subtract numbers" in {
    User clicks "2-5="
    Display indicates "-3"
  }

  "User can multiply numbers" in {
    User clicks "2*5="
    Display indicates "10"
  }

  "User can divide numbers" in {
    User clicks "1/8="
    Display indicates "0.125"
  }

  "User can calculate with previously calculated value" in {
    User clicks "1+1="
    User clicks "/8="
    Display indicates "0.25"
  }

  "User can calculate with previously entered value and operation" in {
    User clicks "8*2="
    User clicks "3="
    Display indicates "24"
  }

  "User can calculate with previously entered values and operation" in {
    User clicks "1+1="
    User clicks "="
    Display indicates "3"
  }

  "User can calculate previous operation by entering new operation" in {
    skipped("because not implemented yet")
    User clicks "1+1+1="
    Display indicates "3"
  }

  "User can reset entered or calculated values and operation" in {
    User clicks "1C"
    Display indicates "0"
    User clicks "1+C"
    Display indicates "0"
    User clicks "1+1C"
    Display indicates "0"
    User clicks "1+1=C"
    Display indicates "0"
  }

  "Calculator should be titled and displays 0 when started" in {
    Calculator titled "Calculator"
    Display indicates "0"
  }

  "Calculator should remove redundant dot with following zeros" in {
    User clicks "1.1+9.9="
    Display indicates "11"
  }

  "Calculator should do nothing when calculate button pressed after entering the first number" in {
    User clicks "1="
    Display indicates "1"
  }

  "Calculator should skip duplicated dots" in {
    User clicks "2.."
    Display indicates "2."
  }

  "Calculator should remove leading zeros" in {
    User clicks "00"
    Display indicates "0"
    User clicks "1"
    Display indicates "1"
  }

  "Calculator should not remove leading zero before dot" in {
    User clicks ".1"
    Display indicates "0.1"
  }

  "Calculator should not remove zeros after dot when entering" in {
    User clicks "0.1000"
    Display indicates "0.1000"
    User clicks "1"
    Display indicates "0.10001"
  }

  "Calculator should report error when divide by zero" in {
    User clicks "1/0="
    Display indicates "Error"
  }

  "Calculator should limit length of entered numbers to 16 digits" in {
    User clicks "12345678901234567"
    Display indicates "1234567890123456"
  }

  "Calculator should calculate with precision up 16 digits" in {
    User clicks "111111111111111*9="
    Display indicates "999999999999999"
    User clicks "1/6="
    Display indicates "0.1666666666666667"
  }

  "Calculator should report error in case of overflow" in {
    User clicks "9999999999999999*9="
    Display indicates "Error"
  }

  "Calculator should indicate 0 in case of underflow" in {
    User clicks "0.9/9999999999999999="
    Display indicates "0"
  }
}