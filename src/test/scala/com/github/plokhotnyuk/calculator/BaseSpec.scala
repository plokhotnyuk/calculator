package com.github.plokhotnyuk.calculator

import com.objogate.wl.robot.RoboticAutomaton
import com.objogate.wl.swing.AWTEventQueueProber
import com.objogate.wl.swing.driver.JButtonDriver
import com.objogate.wl.swing.driver.JFrameDriver
import com.objogate.wl.swing.driver.JTextFieldDriver
import com.objogate.wl.swing.gesture.GesturePerformer
import javax.swing._
import com.objogate.wl.swing.driver.ComponentDriver._
import org.hamcrest.Matchers._
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.{BeforeExample, AfterExample}
import org.specs2.execute.Result

abstract class BaseSpec extends SpecificationWithJUnit with BeforeExample with AfterExample {
  private val TimeoutMillis = 1000
  private val PollDelayMillis = 100
  private lazy val calculatorDriver = new JFrameDriver(new GesturePerformer,
    JFrameDriver.topLevelFrame(named("Calculator"), showingOnScreen),
    new AWTEventQueueProber(TimeoutMillis, PollDelayMillis))

  override def before {
    CalculatorApp.main(null)
  }

  override def after {
    calculatorDriver.dispose()
  }

  object User {
    private val automaton = new RoboticAutomaton

    def clicks(buttons: String): Result = {
      for (button <- buttons) buttonDriver(button.toString).click()
      success
    }

    def types(keys: String): Result = {
      for (key <- keys) automaton.typeCharacter(key)
      success
    }
  }

  object Calculator {
    def titled(title: String): Result = {
      calculatorDriver.hasTitle(title)
      success
    }
  }

  object Display {
    def indicates(title: String): Result = {
      displayDriver.hasText(title)
      success
    }
  }

  private def displayDriver =
    new JTextFieldDriver(calculatorDriver, classOf[JTextField], both(named("Display")).and(showingOnScreen))

  private def buttonDriver(button: String) =
    new JButtonDriver(calculatorDriver, classOf[JButton], both(named(button)).and(showingOnScreen))
}