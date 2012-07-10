package com.github.plokhotnyuk.calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The calculator application that creates and displays the calculator window.
 */
public final class CalculatorApp {
    private static final int DEFAULT_FONT_SIZE = 14;
    private static final Font DEFAULT_FONT = new Font("Verdana", Font.BOLD, DEFAULT_FONT_SIZE);
    private static final int PRESS_TIME = 100;
    private static final int COLUMN_NUMBER = 4;
    private static final String CALCULATOR_NAME = "Calculator";
    private static final String DISPLAY_NAME = "Display";

    private CalculatorModel calculator = new CalculatorModel();
    private JTextField display = new JTextField() {{
        setName(DISPLAY_NAME);
        setText(calculator.getCurrValue());
        setHorizontalAlignment(SwingConstants.RIGHT);
        setFont(DEFAULT_FONT);
        setEditable(false);
    }};

    private CalculatorApp() {
        new JFrame() {{
            setName(CALCULATOR_NAME);
            setTitle(CALCULATOR_NAME);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setContentPane(new CalculatorPanel() {{
                setBorder(new EmptyBorder(2, 2, 2, 2));
                atNewLineWithColumnSpan(display, COLUMN_NUMBER);
                atNewLine(button("C")).beside(button("/")).beside(button("*")).beside(button("-"));
                atNewLine(button("7")).beside(button("8")).beside(button("9")).besideWithRowSpan(button("+"), 2);
                atNewLine(button("4")).beside(button("5")).beside(button("6"));
                atNewLine(button("1")).beside(button("2")).beside(button("3")).besideWithRowSpan(button("="), 2);
                atNewLineWithColumnSpan(button("0"), 2).beside(button("."));
            }});
            setResizable(false);
            pack();
        }}.setVisible(true);
    }

    private JButton button(final String name) {
        return new JButton() {{
            setName(name);
            setAction(calculatorAction(name));
            setFocusable(false);
            setFont(DEFAULT_FONT);
            registerKeyEventDispatcher(this);
        }};
    }

    private AbstractAction calculatorAction(final String name) {
        return new AbstractAction(name) {
            /** {@inheritDoc} */
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    calculator.pressButton(name);
                    display.setText(calculator.getCurrValue());
                } catch (IllegalArgumentException ex) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
    }

    /**
     * Launch the calculator application.
     *
     * @param args the command line arguments that are ignored
     */
    public static void main(String[] args) {
        new CalculatorApp();
    }

    private static void registerKeyEventDispatcher(final JButton button) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            /** {@inheritDoc} */
            @Override
            public boolean dispatchKeyEvent(KeyEvent ev) {
                if (ev.getID() == KeyEvent.KEY_TYPED && String.valueOf(ev.getKeyChar()).equals(button.getName())) {
                    button.doClick(PRESS_TIME);
                }
                return false;
            }
        });
    }
}