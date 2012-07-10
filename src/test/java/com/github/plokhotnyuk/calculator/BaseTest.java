package com.github.plokhotnyuk.calculator;

import com.github.plokhotnyuk.calculator.CalculatorApp;
import com.objogate.wl.Automaton;
import com.objogate.wl.robot.RoboticAutomaton;
import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JButtonDriver;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JTextFieldDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;
import org.junit.After;
import org.junit.Before;

import javax.swing.*;

import static com.objogate.wl.swing.driver.ComponentDriver.named;
import static com.objogate.wl.swing.driver.ComponentDriver.showingOnScreen;
import static com.objogate.wl.swing.driver.JFrameDriver.topLevelFrame;
import static org.hamcrest.Matchers.*;

public abstract class BaseTest {
    private static final int TIMEOUT_MILLIS = 1000;
    private static final int POLL_DELAY_MILLIS = 100;

    private JFrameDriver calculatorDriver;


    @Before
    public void launchCalculatorApp() {
        CalculatorApp.main(null);
        calculatorDriver = new JFrameDriver(new GesturePerformer(),
                topLevelFrame(named("Calculator"), showingOnScreen()),
                new AWTEventQueueProber(TIMEOUT_MILLIS, POLL_DELAY_MILLIS));
    }

    @After
    public void closeCalculatorApp() {
        calculatorDriver.dispose();
    }

    public class User {
        private Automaton automaton = new RoboticAutomaton();

        public void clicks(String buttons) {
            for (Character button : buttons.toCharArray()) {
                buttonDriver(button.toString()).click();
            }
        }

        public void types(String keys) {
            for (Character key : keys.toCharArray()) {
                automaton.typeCharacter(key);
            }
        }
    }

    public class Calculator {
        public Display display = new Display();

        public void titled(String title) {
            calculatorDriver.hasTitle(title);
        }

        public class Display {
            public void indicates(String title) {
                displayDriver().hasText(title);
            }
        }
    }

    private JTextFieldDriver displayDriver() {
        return new JTextFieldDriver(calculatorDriver, JTextField.class, allOf(named("Display"), showingOnScreen()));
    }

    private JButtonDriver buttonDriver(String button) {
        return new JButtonDriver(calculatorDriver, JButton.class, allOf(named(button), showingOnScreen()));
    }
}
