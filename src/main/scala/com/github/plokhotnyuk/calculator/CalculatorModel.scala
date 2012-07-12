package com.github.plokhotnyuk.calculator

import java.math.BigDecimal
import java.math.MathContext
import CalculatorModel._
import scala.reflect.{BeanProperty, BooleanBeanProperty}
//remove if not needed
import scala.collection.JavaConversions._

object CalculatorModel {

  private val PRECISION = 16

  private val MAX_VALUE = new BigDecimal("9999999999999999")

  private val MIN_VALUE = new BigDecimal("0.0000000000000001")
}

/**
 * A model of calculator that allow entering values and calculate simple arithmetic functions.
 */
class CalculatorModel {

  var prevValue: String = _

  var currValue: String = _

  var operation: String = _

  private var isNewValue: Boolean = _

  reset()

  /**
   * Process pressing of calculator button.
   *
   * @param name the name of pressed button
   */
  def pressButton(name: String) {
    if ("C" == name) {
      reset()
    } else if ("=" == name) {
      completeOperation()
    } else if ("+" == name || "-" == name || "*" == name || "/" == name) {
      setOperation(name)
    } else {
      appendDigit(name)
    }
  }

  private def reset() {
    currValue = "0"
    setOperation("=")
  }

  private def appendDigit(digit: String) {
    val num = if (isNewValue && digit != ".") "" else currValue
    if (isDuplicatedDot(num, digit) || isLengthMaximal(num)) {
      throw new IllegalArgumentException()
    }
    currValue = dropLeftZeros(num + digit)
    isNewValue = false
  }

  def getCurrValue(): String = {
    currValue
  }

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
      case '/' => x.divide(y, new MathContext(PRECISION))
      case _ => y
    }
  }

  private def round(value: BigDecimal): BigDecimal = {
    val roundedValue = value.round(new MathContext(PRECISION))
    if (roundedValue.abs().compareTo(MAX_VALUE) > 0) {
      throw new IllegalArgumentException()
    }
    if (roundedValue.abs().compareTo(MIN_VALUE) < 0) BigDecimal.ZERO else roundedValue
  }

  private def isLengthMaximal(num: String): Boolean = {
    num.replace(".", "").length == PRECISION
  }

  private def isDuplicatedDot(num: String, digit: String): Boolean = num.contains(".") && digit == "."

  private def dropRightZeros(num: String): String = {
    if (num.contains(".") && (num.endsWith("0") || num.endsWith("."))) {
      return dropRightZeros(num.substring(0, num.length - 1))
    }
    num
  }

  private def dropLeftZeros(num: String): String = {
    if (num.startsWith("0") && num.length > 1 && !num.startsWith("0.")) {
      return dropLeftZeros(num.substring(1))
    }
    num
  }
}
