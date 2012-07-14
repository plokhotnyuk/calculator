package com.github.plokhotnyuk.calculator

import java.math.BigDecimal
import java.math.MathContext
import CalculatorModel._
import annotation.tailrec

object CalculatorModel {
  private val Precision = 16
  private val MaxValue = new BigDecimal("9999999999999999")
  private val MinValue = new BigDecimal("0.0000000000000001")
}

/**
 * A model of calculator that allow entering values and calculate simple arithmetic functions.
 */
class CalculatorModel {
  private var prevValue: String = _
  private var currValue: String = _
  private var operation: String = _
  private var isNewValue: Boolean = _

  reset()

  /**
   * Process pressing of calculator button.
   *
   * @param name the name of pressed button
   */
  def pressButton(name: String) {
    name match {
      case "C" => reset()
      case "=" => completeOperation()
      case "+" | "-" | "*" | "/" => setOperation(name)
      case _ => appendDigit(name)
    }
  }

  private def reset() {
    currValue = "0"
    setOperation("=")
  }

  private def appendDigit(digit: String) {
    val num = if (isNewValue && digit != ".") "" else currValue
    if (isDuplicatedDot(num, digit) || isLengthMaximal(num)) throw new IllegalArgumentException()
    currValue = dropLeftZeros(num + digit)
    isNewValue = false
  }

  def getCurrValue: String = currValue

  private def setOperation(op: String) {
    operation = op
    prevValue = currValue
    isNewValue = true
  }

  private def completeOperation() {
    try {
      currValue = dropRightZeros(round(evaluate()).toString)
    } catch {
      case ex: RuntimeException => currValue = "Error"
    }
    isNewValue = true
  }

  private def evaluate(): BigDecimal = {
    val x = new BigDecimal(prevValue)
    val y = new BigDecimal(currValue)
    operation.charAt(0) match {
      case '+' => x.add(y)
      case '-' => x.subtract(y)
      case '*' => x.multiply(y)
      case '/' => x.divide(y, new MathContext(Precision))
      case _ => y
    }
  }

  private def round(value: BigDecimal): BigDecimal = {
    val roundedValue = value.round(new MathContext(Precision))
    if (roundedValue.abs().compareTo(MaxValue) > 0) throw new IllegalArgumentException()
    if (roundedValue.abs().compareTo(MinValue) < 0) BigDecimal.ZERO else roundedValue
  }

  private def isLengthMaximal(v: String): Boolean = v.replace(".", "").length == Precision

  private def isDuplicatedDot(v: String, digit: String): Boolean = v.contains(".") && digit == "."

  @tailrec
  private def dropRightZeros(v: String): String =
    if (v.contains(".") && (v.endsWith("0") || v.endsWith("."))) dropRightZeros(v.dropRight(1)) else v

  @tailrec
  private def dropLeftZeros(v: String): String =
    if (v.startsWith("0") && v.length > 1 && !v.startsWith("0.")) dropLeftZeros(v.drop(1)) else v
}
