package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Stack;

/**
 * 계산기 메모리 버튼의 이벤트와 초기화 설정하는 클래스입니다.
 * 'JButton' 클래스를 상속 받고 메모리 버튼에 대한 이벤트를 담고 있습니다.
 *
 * @author 2023011794_Ji Woo Park (gav705@naver.com)
 * @version 0.8.7
 * @since 0.0.1
 *
 * {@code @created} 2024-10-22
 * {@code @lastModified} 2024-10-30
 *
 * {@code @changelog}
 * <ul>
 *   <li>2024-10-22: 최초 생성</li>
 *   <li>2024-10-28: 버튼 기능 생성 </li>
 *   <li>2024-10-30: 오류 개선 </li>
 * </ul>
 */
class JButtonWhite extends JButton {
    Font font = new Font("맑은 고딕", Font.PLAIN, 18);

    /**
     * 주어진 숫자를 형식화하여 문자열로 반환합니다.
     *
     * @param number 형식화할 숫자
     * @return 소수점 이하가 0일 경우는 천 단위 구분 기호가 있는 정수 형태의 문자열을,
     *         그렇지 않은 경우는 소수점이 포함된 문자열을 반환합니다.
     */
    public static String formatDouble(double number) {
        if (number == (long) number) {
            return String.format("%,d", (long) number);
        } else {
            return String.format("%s", number);
        }
    }

    /**
     * 주어진 문자열이 수학 연산자인지 확인합니다.
     *
     * @param str 확인할 문자열
     * @return 문자열이 "+", "-", "×", 또는 "÷" 중 하나이면 true를 반환하고,
     *         그렇지 않으면 false를 반환합니다.
     */
    private boolean isOperator(String str) {
        return "+".equals(str) || "-".equals(str) ||
                "×".equals(str) || "÷".equals(str);
    }

    /**
     * java.JButtonWhite 클래스의 생성자로, 버튼의 속성을 설정하고 액션 리스너를 추가합니다.
     *
     * <p>
     * 액션 리스너 기능:
     * <ul>
     *   <li>숫자 버튼: 입력된 숫자를 스택에 추가하고, 결과 텍스트 필드에 표시</li>
     *   <li>부호 바꾸기: 현재 숫자의 부호를 반전시킴</li>
     *   <li>소수점 버튼: 소수점을 추가하며, 이미 존재할 경우 추가하지 않음</li>
     * </ul>
     * </p>
     *
     * @param text 버튼에 표시될 텍스트
     * @param result 결과를 표시하는 JTextField
     * @param preview 계산 과정을 보여주는 JTextField
     * @param temp 입력된 값을 저장하는 Stack
     * @param preveiwStack 계산 과정을 저장하는 Stack
     */
    public JButtonWhite(String text, JTextField  result, JTextField preview, Stack temp, Stack preveiwStack) {
        super.setBackground(Color.WHITE);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(font);
        setPreferredSize(new Dimension(77, 46));
        this.setText(text);

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String btnStr = getText();
                if (btnStr.matches("\\d+")) {  // GPT로 정규식 물어보고 가져옴 숫자일 때 경우
                    if (temp.isEmpty() && btnStr.equals("0")) {
                        return;  // 첫 숫자가 0일 때 스택에 추가하지 않고 리턴
                    }

                    temp.push(btnStr);

                    // 텍스트 크기 동적 조절
                    if (temp.size() < 10) {
                        Font font = new Font("Dialog", Font.BOLD, 48);
                        result.setFont(font);
                    }
                    else if (temp.size() > 16) {
                        throw new StackOverflowError();
                    } else if (temp.size() == 16) {
                        Font font = new Font("Dialog", Font.BOLD, 45 - temp.size());
                        result.setFont(font);
                    } else if (temp.size() >= 10) {
                        Font font = new Font("Dialog", Font.BOLD, 47 - temp.size());
                        result.setFont(font);
                    }

                    String str = "";
                    for (int i = 0; i < temp.size(); i++) {
                        str += temp.get(i);
                    }


//                    StringBuffer sb = new StringBuffer(str);
//                    String reverse = sb.reverse().toString();
//                    reverse = reverse.replaceAll("(.{3})", "$1,");
//                    sb = new StringBuffer(reverse);
//                    if (sb.charAt(sb.length() - 1) == ',')
//                        sb.deleteCharAt(sb.length() - 1);

                    double doubleString = Double.parseDouble(str);
                    result.setText(String.format("%s", formatDouble(doubleString)));
                }

                // 부호 바꾸기 연산 처리 (negate)
                if(Objects.equals(text, "+/-")){
                    String operation = null;
                    int position = -1;

                    // 연산자 찾기
                    for (int i = preveiwStack.size()-1; i >= 0; i--) {
                        String current = (String) preveiwStack.get(i);
                        if (isOperator(current)) {
                            operation = current;
                            position = i ;
                            break;
                        }
                    }

                    double number = Double.parseDouble(result.getText());
                    if (position != -1) {
                        double num1 = Double.parseDouble(String.valueOf(preveiwStack.get(position - 1)));
                        preview.setText(String.format("%s %s negate(%s) =",formatDouble(num1), operation, formatDouble(number)));
                    }
                    number *= -1;
                    result.setText(String.format("%s", formatDouble(number)));
                }

                // 소수점 연산 처리
                if (Objects.equals(text, ".")) {
                    String currentText = result.getText();

                    // 소수점이 이미 존재하는지 확인
                    if (!currentText.contains(".")) {
                        // 소수점이 없으면 추가
                        temp.push(".");
                        result.setText(currentText + ".");
                    }
                }
            }
        });
    }
}