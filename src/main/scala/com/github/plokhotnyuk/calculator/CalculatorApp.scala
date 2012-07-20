package com.github.plokhotnyuk.calculator

import javax.swing._
import javax.swing.border.EmptyBorder
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent

/**
 * The calculator application that creates and displays the calculator window.
 */
object CalculatorApp extends App {
  private val font = new Font("Verdana", Font.BOLD, 14)
  private val calculator = new CalculatorModel()
  private val display = new JTextField() {
    setName("Display")
    setText(calculator.getCurrValue)
    setHorizontalAlignment(SwingConstants.RIGHT)
    setFont(font)
    setEditable(false)
  }

  new JFrame() {
    setName("Calculator")
    setTitle("Calculator")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setContentPane(new CalculatorPanel() {
      setBorder(new EmptyBorder(2, 2, 2, 2))
      atNewLineWithColumnSpan(display, 4)
      atNewLine(button("C")).beside(button("/")).beside(button("*")).beside(button("-"))
      atNewLine(button("7")).beside(button("8")).beside(button("9")).besideWithRowSpan(button("+"), 2)
      atNewLine(button("4")).beside(button("5")).beside(button("6"))
      atNewLine(button("1")).beside(button("2")).beside(button("3")).besideWithRowSpan(button("="), 2)
      atNewLineWithColumnSpan(button("0"), 2).beside(button("."))
    })
    setResizable(false)
    pack()
  }.setVisible(true)

  private def button(name: String): JButton = new JButton() {
    setName(name)
    setAction(calculatorAction(name))
    setFocusable(false)
    setFont(font)
    registerKeyEventDispatcher(this)
  }

  private def registerKeyEventDispatcher(button: JButton) {
    KeyboardFocusManager.getCurrentKeyboardFocusManager.addKeyEventDispatcher(new KeyEventDispatcher() {
      override def dispatchKeyEvent(ev: KeyEvent): Boolean = {
        if (ev.getID == KeyEvent.KEY_TYPED && String.valueOf(ev.getKeyChar) == button.getName) button.doClick()
        false
      }
    })
  }

  private def calculatorAction(name: String): AbstractAction = new AbstractAction(name) {
    override def actionPerformed(event: ActionEvent) {
      try {
        calculator.pressButton(name)
        display.setText(calculator.getCurrValue)
      } catch {
        case ex: IllegalArgumentException => Toolkit.getDefaultToolkit.beep()
      }
    }
  }
}
