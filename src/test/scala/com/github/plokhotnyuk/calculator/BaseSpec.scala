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
import org.scalatest.BeforeAndAfterEach
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

abstract class BaseSpec extends AnyFreeSpec with Matchers with BeforeAndAfterEach {
  private val TimeoutMillis = 5000
  private val PollDelayMillis = 100
  private lazy val calculatorDriver = new JFrameDriver(new GesturePerformer,
    JFrameDriver.topLevelFrame(named("Calculator"), showingOnScreen),
    new AWTEventQueueProber(TimeoutMillis, PollDelayMillis))

  override def beforeEach(): Unit = {
    CalculatorApp.main(null)
    Thread.sleep(PollDelayMillis)
  }

  override def afterEach(): Unit = calculatorDriver.dispose()

  object User {
    private lazy val automaton = new RoboticAutomaton

    def clicks(buttons: String): Unit = for (button <- buttons) buttonDriver(button.toString).click()

    def types(keys: String): Unit = for (key <- keys) automaton.typeCharacter(key)
  }

  object Calculator {
    def titled(title: String): Unit = calculatorDriver.hasTitle(title)
  }

  object Display {
    def indicates(title: String): Unit = displayDriver.hasText(title)
  }

  private def displayDriver =
    new JTextFieldDriver(calculatorDriver, classOf[JTextField], both(named("Display")).and(showingOnScreen))

  private def buttonDriver(button: String) =
    new JButtonDriver(calculatorDriver, classOf[JButton], both(named(button)).and(showingOnScreen))
}