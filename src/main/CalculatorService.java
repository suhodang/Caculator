package main;

/**
 * CalculatorService 클래스: 계산 기능을 담당하는 서비스 클래스
 */
public class CalculatorService {

    /**
     * 두 숫자와 연산자에 따라 산술 연산을 수행합니다.
     *
     * @param num1 첫 번째 숫자
     * @param num2 두 번째 숫자
     * @param operation 연산자 (+, -, ×, ÷)
     * @return 연산 결과, 0으로 나누는 경우 예외 발생
     */
    public double calculate(double num1, double num2, String operation) {
        return switch (operation) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "×" -> num1 * num2;
            case "÷" -> {
                if (num2 == 0) throw new ArithmeticException("0으로 나눌 수 없습니다");
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException("잘못된 연산자: " + operation);
        };
    }
}
