package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAddition() {
        assertEquals(5, calculatorService.calculate(2, 3, "+"));
    }

    @Test
    void testSubtraction() {
        assertEquals(2, calculatorService.calculate(5, 3, "-"));
    }

    @Test
    void testMultiplication() {
        assertEquals(15, calculatorService.calculate(5, 3, "×"));
    }

    @Test
    void testDivision() {
        assertEquals(2, calculatorService.calculate(6, 3, "÷"));
    }

    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculatorService.calculate(6, 0, "÷"));
        assertEquals("0으로 나눌 수 없습니다", exception.getMessage());
    }

    @Test
    void testInvalidOperator() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(6, 3, "?"));
        assertEquals("잘못된 연산자: ?", exception.getMessage());
    }
}
