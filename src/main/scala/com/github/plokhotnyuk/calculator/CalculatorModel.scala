package com.github.plokhotnyuk.calculator

import scala.math.BigDecimal
import java.math.MathContext
import annotation.tailrec

/**
 * A model of calculator that allow entering values and calculate simple arithmetic functions.
 */
class CalculatorModel {
  private val precision = 16
  private val maxValue = BigDecimal("9999999999999999")
  private val minValue = BigDecimal("0.0000000000000001")
  private var prevValue: String = _
  private var currValue: String = _
  private var operation: String = _
  private var isNewValue: Boolean = _

  reset()

  def getCurrValue: String = currValue

  /**
   * Process pressing of calculator button.
   *
   * @param name the name of pressed button
   */
  def pressButton(name: String): Unit =
    name match {
      case "C" => reset()
      case "=" => completeOperation()
      case "+" | "-" | "*" | "/" => setOperation(name)
      case _ => appendDigit(name)
    }

  private def reset(): Unit = {
    currValue = "0"
    setOperation("=")
  }

  private def appendDigit(digit: String): Unit = {
    val v = if (isNewValue && digit != ".") "" else currValue
    if (isDuplicatedDot(v, digit) || isLengthMaximal(v)) throw new IllegalArgumentException
    currValue = dropLeftZeros(v + digit)
    isNewValue = false
  }

  private def setOperation(op: String): Unit = {
    operation = op
    prevValue = currValue
    isNewValue = true
  }

  private def completeOperation(): Unit = {
    currValue = try dropRightZeros(round(evaluate()).toString) catch {
      case _: RuntimeException => "Error"
    }
    isNewValue = true
  }

  private def evaluate(): BigDecimal = {
    val x = BigDecimal(prevValue)
    val y = BigDecimal(currValue)
    operation match {
      case "+" => x + y
      case "-" => x - y
      case "*" => x * y
      case "/" => x / y
      case _ => y
    }
  }

  private def round(v: BigDecimal): BigDecimal = v.abs match {
    case va if va > maxValue => throw new IllegalArgumentException
    case va if va < minValue => BigDecimal(0)
    case _ => v.round(new MathContext(precision))
  }

  private def isLengthMaximal(v: String): Boolean = v.replace(".", "").length == precision

  private def isDuplicatedDot(v: String, digit: String): Boolean = v.contains(".") && digit == "."

  @tailrec
  private def dropRightZeros(v: String): String =
    if (v.contains(".") && (v.endsWith("0") || v.endsWith("."))) dropRightZeros(v.dropRight(1)) else v

  @tailrec
  private def dropLeftZeros(v: String): String =
    if (v.startsWith("0") && v.length > 1 && !v.startsWith("0.")) dropLeftZeros(v.drop(1)) else v
}
