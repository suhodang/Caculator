package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.util.Stack;

class JButtonMemoryTest {

    @Test
    void testMemoryAdd() {
        JTextField resultView = new JTextField("5");
        Stack<String> memory = new Stack<>();
        Stack<String> memoryTemp = new Stack<>();

        JButtonMemory memoryButton = new JButtonMemory("M+", resultView, memory, memoryTemp);
        memoryButton.doClick();

        assertEquals("5", memory.peek());
    }

    @Test
    void testMemoryClear() {
        JTextField resultView = new JTextField("5");
        Stack<String> memory = new Stack<>();
        Stack<String> memoryTemp = new Stack<>();

        JButtonMemory memoryButtonAdd = new JButtonMemory("M+", resultView, memory, memoryTemp);
        memoryButtonAdd.doClick();

        JButtonMemory memoryButtonClear = new JButtonMemory("MC", resultView, memory, memoryTemp);
        memoryButtonClear.doClick();

        assertTrue(memory.isEmpty());
    }
}
