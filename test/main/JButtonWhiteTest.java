package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.util.Stack;

class JButtonWhiteTest {

    @Test
    void testNumberInput() {
        JTextField resultView = new JTextField();
        JTextField preview = new JTextField();
        Stack<String> temp = new Stack<>();
        Stack<String> preResult = new Stack<>();

        JButtonWhite button = new JButtonWhite("7", resultView, preview, temp, preResult);
        button.doClick();

        assertEquals("7", resultView.getText());
    }

    @Test
    void testNegate() {
        JTextField resultView = new JTextField("5");
        JTextField preview = new JTextField();
        Stack<String> temp = new Stack<>();
        Stack<String> preResult = new Stack<>();

        JButtonWhite negateButton = new JButtonWhite("+/-", resultView, preview, temp, preResult);
        negateButton.doClick();

        assertEquals("-5", resultView.getText());
    }
}
