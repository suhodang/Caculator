package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.util.Stack;

class JButtonSTest {

    @Test
    void testAddition() {
        JTextField resultView = new JTextField();
        JTextField preview = new JTextField();
        Stack<String> temp = new Stack<>();
        Stack<String> preResult = new Stack<>();

        JButtonS addButton = new JButtonS("+", resultView, preview, temp, preResult);
        resultView.setText("5");
        addButton.doClick();

        assertFalse(preResult.isEmpty(), "preResult 스택이 비어 있습니다."); // 추가된 예외 방지 코드
        assertEquals("+", preResult.peek());
    }

    @Test
    void testClear() {
        JTextField resultView = new JTextField("10");
        JTextField preview = new JTextField();
        Stack<String> temp = new Stack<>();
        Stack<String> preResult = new Stack<>();

        JButtonS clearButton = new JButtonS("C", resultView, preview, temp, preResult);
        clearButton.doClick();

        assertEquals("0", resultView.getText());
    }
}
