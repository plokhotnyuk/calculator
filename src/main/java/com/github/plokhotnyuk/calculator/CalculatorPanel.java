package com.github.plokhotnyuk.calculator;

import javax.swing.*;
import java.awt.*;

/**
 * A panel for calculator that allow easy place display and buttons in a grid layout.
 */
public class CalculatorPanel extends JPanel {
    private int row, column = -1;

    /**
     * Creates the calculator panel with GridBagLayout.
     */
    public CalculatorPanel() {
        setLayout(new GridBagLayout());
    }

    /**
     * Add component at the left side, from a new line.
     *
     * @param component the component to add
     * @return this for chaining calls
     */
    public CalculatorPanel atNewLine(JComponent component) {
        return atNewLineWithColumnSpan(component, 1);
    }

    /**
     * Add component at the left side, from a new line with column (horizontal) span.
     *
     * @param component the component to add
     * @param width the number of columns to span
     * @return this for chaining calls
     */
    public CalculatorPanel atNewLineWithColumnSpan(JComponent component, int width) {
        row++;
        column = 0;
        add(component, constraints(width, 1));
        column += width - 1;
        return this;
    }

    /**
     * Add component beside previous at the same line.
     *
     * @param component the component to add
     * @return this for chaining calls
     */
    public CalculatorPanel beside(JComponent component) {
        return besideWithRowSpan(component, 1);
    }

    /**
     * Add component beside previous at the same line with row (vertical) span.
     *
     * @param component the component to add
     * @param height the number of rows (lines) to span
     * @return this for chaining calls
     */
    public CalculatorPanel besideWithRowSpan(JComponent component, int height) {
        column++;
        add(component, constraints(1, height));
        return this;
    }

    private GridBagConstraints constraints(final int width, final int height) {
        return new GridBagConstraints() {{
            gridx = column;
            gridy = row;
            gridwidth = width;
            gridheight = height;
            insets = new Insets(2, 2, 2, 2);
            fill = GridBagConstraints.BOTH;
        }};
    }
}