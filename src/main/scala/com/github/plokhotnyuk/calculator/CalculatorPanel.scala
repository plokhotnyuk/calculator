package com.github.plokhotnyuk.calculator

import javax.swing._
import java.awt._
//remove if not needed
import scala.collection.JavaConversions._

/**
 * A panel for calculator that allow easy place display and buttons in a grid layout.
 */
class CalculatorPanel extends JPanel {

  private var row: Int = _

  private var column: Int = -1

  setLayout(new GridBagLayout())

  /**
   * Add component at the left side, from a new line.
   *
   * @param component the component to add
   * @return this for chaining calls
   */
  def atNewLine(component: JComponent): CalculatorPanel = atNewLineWithColumnSpan(component, 1)

  /**
   * Add component at the left side, from a new line with column (horizontal) span.
   *
   * @param component the component to add
   * @param width the number of columns to span
   * @return this for chaining calls
   */
  def atNewLineWithColumnSpan(component: JComponent, width: Int): CalculatorPanel = {
    row += 1
    column = 0
    add(component, constraints(width, 1))
    column += width - 1
    this
  }

  /**
   * Add component beside previous at the same line.
   *
   * @param component the component to add
   * @return this for chaining calls
   */
  def beside(component: JComponent): CalculatorPanel = besideWithRowSpan(component, 1)

  /**
   * Add component beside previous at the same line with row (vertical) span.
   *
   * @param component the component to add
   * @param height the number of rows (lines) to span
   * @return this for chaining calls
   */
  def besideWithRowSpan(component: JComponent, height: Int): CalculatorPanel = {
    column += 1
    add(component, constraints(1, height))
    this
  }

  private def constraints(width: Int, height: Int): GridBagConstraints = {
    new GridBagConstraints() {

      gridx = column

      gridy = row

      gridwidth = width

      gridheight = height

      insets = new Insets(2, 2, 2, 2)

      fill = GridBagConstraints.BOTH
    }
  }
}
