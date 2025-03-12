import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Stack;


///**
// * 계산기 메모리 버튼의 이벤트와 초기화 설정하는 클래스입니다.
// * 'JButton' 클래스를 상속 받고 메모리 버튼에 대한 이벤트를 담고 있습니다.
// *
// * @author 2023011794_Ji Woo Park (gav705@naver.com)
// * @version 0.6.2
// * @since 0.0.1
// *
// * {@code @created} 2024-10-13
// * {@code @lastModified} 2024-10-30
// *
// * {@code @changelog}
// * <ul>
// *   <li>2024-10-22: 최초 생성</li>
// *   <li>2024-10-18: 메모리 버튼 기능 생성 </li>
// *   <li>2024-10-30: 오류 개선 </li>
// * </ul>
// */
//class java.JButtonMemory extends JButton {
//    Font font = new Font("맑은 고딕", Font.PLAIN, 13);
//    Color c = new Color(0xeeeeee);
//
//
//    /**
//     * `java.JButtonMemory` 생성자: 메모리 관련 기능을 수행하는 버튼을 생성합니다.
//     *
//     * <p>
//     * 이 생성자는 지정된 텍스트와 `resultView`, 메모리 스택(`memory`) 및 메모리 임시 스택(`memoryTemp`)을 사용하여
//     * 버튼의 초기 설정과 액션 리스너를 구성합니다.
//     * </p>
//     *
//     * @param text       버튼에 표시될 텍스트(예: "MC", "MR", "M+", "M-", "MS" 등).
//     * @param resultView 결과를 표시하는 `JTextField`.
//     * @param memory     메모리 값을 저장하는 스택.
//     * @param memoryTemp 임시 메모리 스택.
//     *
//     * <p>
//     * 액션 리스너 기능:
//     * <ul>
//     *   <li>MC: 메모리 스택 초기화</li>
//     *   <li>MR: 메모리에서 가장 최근 값을 불러와 `resultView`에 표시</li>
//     *   <li>M+: `resultView` 값과 메모리 값을 더하여 저장</li>
//     *   <li>M-: `resultView` 값과 메모리 값을 빼서 저장</li>
//     *   <li>MS: `resultView` 값을 메모리에 저장</li>
//     *   <li>M∨: 메모리 값을 표시 (추후 구현 예정)</li>
//     * </ul>
//     * </p>
//     */
//    public java.JButtonMemory(String text, JTextField resultView, Stack memory, Stack memoryTemp) {
//        super.setBackground(c);
//        setBorderPainted(false);
//        setFocusPainted(false);
//        this.setText(text);
//        setFont(font);
//
//        addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                String str = resultView.getText();
//                str = str.replace(",", "");
//
//
//                // MemoryClear(메모리 초기화)
//                if (Objects.equals(text, "MC")) {
//                    memory.clear();
//                }
//
//                // MemoryRoad(메모리 값 불러오기)
//                if (Objects.equals(text, "MR")) {
//                    resultView.setText(String.valueOf(memory.peek()));
//                }
//
//                // MemoryAdd(메모리 값 더하기)
//                if (Objects.equals(text, "M+")) {
//                    if (memory.isEmpty()) {
//                        memoryTemp.push(str);
//                        memory.push(str);
////                        System.out.println(memory);
//                    }
//                    else {
//                        int memoryNum =  Integer.parseInt(String.valueOf(memory.peek()));
//                        int tempNum =  Integer.parseInt(String.valueOf(memoryTemp.peek()));
//                        memoryNum = memoryNum + tempNum;
//                        memory.push(memoryNum);
////                        System.out.println(memory);
//                    }
//                }
//
//                // MemorySub(메모리 값 빼기)
//                if (Objects.equals(text, "M-")) {
//                    if (memory.size() == 0) {
//                        int strN = Integer.parseInt(str);
//                        strN = strN * -1;
//                        str = String.valueOf(strN);
//                        memoryTemp.push(str);
//                        memory.push(str);
//                        System.out.println(memory);
//                    }
//                    else {
//                        int memoryNum =  Integer.parseInt(String.valueOf(memory.peek()));
//                        int tempNum =  Integer.parseInt(String.valueOf(memoryTemp.peek()));
//                        tempNum = tempNum + memoryNum;
//                        memory.push(tempNum);
//                        System.out.println(memory);
//                    }
//                }
//
//                // MemorySave(메모리 값 불러오기)
//                if (Objects.equals(text, "MS")) {
//                    memoryTemp.push(str);
//                    memory.push(str);
//                }
//
//                // MemoryView(메모리 값 리스트 보기)
////                if (Objects.equals(text, "M∨")) {
////                    memoryTemp.push(str);
////                    memory.push(str);
////                }
//            }
//        });
//    }
//}

///**
// * 계산기 메모리 버튼의 이벤트와 초기화 설정하는 클래스입니다.
// * 'JButton' 클래스를 상속 받고 메모리 버튼에 대한 이벤트를 담고 있습니다.
// *
// * @author 2023011794_Ji Woo Park (gav705@naver.com)
// * @version 0.8.7
// * @since 0.0.1
// *
// * {@code @created} 2024-10-22
// * {@code @lastModified} 2024-10-30
// *
// * {@code @changelog}
// * <ul>
// *   <li>2024-10-22: 최초 생성</li>
// *   <li>2024-10-28: 버튼 기능 생성 </li>
// *   <li>2024-10-30: 오류 개선 </li>
// * </ul>
// */
//class JButtonWhite extends JButton {
//    Font font = new Font("맑은 고딕", Font.PLAIN, 18);
//
//    /**
//     * 주어진 숫자를 형식화하여 문자열로 반환합니다.
//     *
//     * @param number 형식화할 숫자
//     * @return 소수점 이하가 0일 경우는 천 단위 구분 기호가 있는 정수 형태의 문자열을,
//     *         그렇지 않은 경우는 소수점이 포함된 문자열을 반환합니다.
//     */
//    public static String formatDouble(double number) {
//        if (number == (long) number) {
//            return String.format("%,d", (long) number);
//        } else {
//            return String.format("%s", number);
//        }
//    }
//
//    /**
//     * 주어진 문자열이 수학 연산자인지 확인합니다.
//     *
//     * @param str 확인할 문자열
//     * @return 문자열이 "+", "-", "×", 또는 "÷" 중 하나이면 true를 반환하고,
//     *         그렇지 않으면 false를 반환합니다.
//     */
//    private boolean isOperator(String str) {
//        return "+".equals(str) || "-".equals(str) ||
//                "×".equals(str) || "÷".equals(str);
//    }
//
//    /**
//     * java.JButtonWhite 클래스의 생성자로, 버튼의 속성을 설정하고 액션 리스너를 추가합니다.
//     *
//     * <p>
//     * 액션 리스너 기능:
//     * <ul>
//     *   <li>숫자 버튼: 입력된 숫자를 스택에 추가하고, 결과 텍스트 필드에 표시</li>
//     *   <li>부호 바꾸기: 현재 숫자의 부호를 반전시킴</li>
//     *   <li>소수점 버튼: 소수점을 추가하며, 이미 존재할 경우 추가하지 않음</li>
//     * </ul>
//     * </p>
//     *
//     * @param text 버튼에 표시될 텍스트
//     * @param result 결과를 표시하는 JTextField
//     * @param preview 계산 과정을 보여주는 JTextField
//     * @param temp 입력된 값을 저장하는 Stack
//     * @param preveiwStack 계산 과정을 저장하는 Stack
//     */
//    public JButtonWhite(String text, JTextField  result, JTextField preview, Stack temp, Stack preveiwStack) {
//        super.setBackground(Color.WHITE);
//        setBorderPainted(false);
//        setFocusPainted(false);
//        setFont(font);
//        setPreferredSize(new Dimension(77, 46));
//        this.setText(text);
//
//        this.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String btnStr = getText();
//                if (btnStr.matches("\\d+")) {  // GPT로 정규식 물어보고 가져옴 숫자일 때 경우
//                    if (temp.isEmpty() && btnStr.equals("0")) {
//                        return;  // 첫 숫자가 0일 때 스택에 추가하지 않고 리턴
//                    }
//
//                    temp.push(btnStr);
//
//                    // 텍스트 크기 동적 조절
//                    if (temp.size() < 10) {
//                        Font font = new Font("Dialog", Font.BOLD, 48);
//                        result.setFont(font);
//                    }
//                    else if (temp.size() > 16) {
//                        throw new StackOverflowError();
//                    } else if (temp.size() == 16) {
//                        Font font = new Font("Dialog", Font.BOLD, 45 - temp.size());
//                        result.setFont(font);
//                    } else if (temp.size() >= 10) {
//                        Font font = new Font("Dialog", Font.BOLD, 47 - temp.size());
//                        result.setFont(font);
//                    }
//
//                    String str = "";
//                    for (int i = 0; i < temp.size(); i++) {
//                        str += temp.get(i);
//                    }
//
//
////                    StringBuffer sb = new StringBuffer(str);
////                    String reverse = sb.reverse().toString();
////                    reverse = reverse.replaceAll("(.{3})", "$1,");
////                    sb = new StringBuffer(reverse);
////                    if (sb.charAt(sb.length() - 1) == ',')
////                        sb.deleteCharAt(sb.length() - 1);
//
//                    double doubleString = Double.parseDouble(str);
//                    result.setText(String.format("%s", formatDouble(doubleString)));
//                }
//
//                // 부호 바꾸기 연산 처리 (negate)
//                if(Objects.equals(text, "+/-")){
//                    String operation = null;
//                    int position = -1;
//
//                    // 연산자 찾기
//                    for (int i = preveiwStack.size()-1; i >= 0; i--) {
//                        String current = (String) preveiwStack.get(i);
//                        if (isOperator(current)) {
//                            operation = current;
//                            position = i ;
//                            break;
//                        }
//                    }
//
//                    double number = Double.parseDouble(result.getText());
//                    if (position != -1) {
//                        double num1 = Double.parseDouble(String.valueOf(preveiwStack.get(position - 1)));
//                        preview.setText(String.format("%s %s negate(%s) =",formatDouble(num1), operation, formatDouble(number)));
//                    }
//                    number *= -1;
//                    result.setText(String.format("%s", formatDouble(number)));
//                }
//
//                // 소수점 연산 처리
//                if (Objects.equals(text, ".")) {
//                    String currentText = result.getText();
//
//                    // 소수점이 이미 존재하는지 확인
//                    if (!currentText.contains(".")) {
//                        // 소수점이 없으면 추가
//                        temp.push(".");
//                        result.setText(currentText + ".");
//                    }
//                }
//            }
//        });
//    }
//}


///**
// * 계산기 연산 버튼의 이벤트와 초기화 설정하는 클래스입니다.
// * 'JButton' 클래스를 상속 받고 연산 버튼에 대한 이벤트를 담고 있습니다.
// *
// * @author 2023011794_Ji Woo Park (gav705@naver.com)
// * @version 0.12.20
// * @since 0.0.1
// *
// * {@code @created} 2024-10-22
// * {@code @lastModified} 2024-10-31
// *
// * {@code @changelog}
// * <ul>
// *   <li>2024-10-22: 최초 생성 </li>
// *   <li>2024-10-28: 버튼 기능 모두 구현 </li>
// *   <li>2024-10-31: 기능 오류 개선 </li>
// * </ul>
// */
//class JButtonS extends JButton {
//    Color c = new Color(0xfbfbfb);
//    Font font = new Font("맑은 고딕", Font.PLAIN, 14);
//
//    /**
//     * 주어진 숫자를 형식화하여 문자열로 반환합니다.
//     *
//     * @param number 형식화할 숫자
//     * @return 소수점 이하가 0일 경우는 천 단위 구분 기호가 있는 정수 형태의 문자열을,
//     *         그렇지 않은 경우는 소수점이 포함된 문자열을 반환합니다.
//     */
//    public static String formatDouble(double number) {
//        // 소수점 이하가 0인지 확인
//        if (number == (long) number) {
//            return String.format("%,d", (long) number);
//        } else {
//            return String.format("%s", number);
//        }
//    }
//
//    /**
//     * java.JButtonS 클래스의 생성자로, 버튼의 속성을 설정하고 액션 리스너를 추가합니다.
//     *
//     * <p>
//     * 액션 리스너 기능:
//     * <ul>
//     *   <li>퍼센트 연산: 결과를 백분율로 계산</li>
//     *   <li>전체 리셋: 스택과 텍스트 필드를 초기화</li>
//     *   <li>결과창 리셋: 입력 값을 지우고 초기화</li>
//     *   <li>지우기: 마지막 입력을 삭제</li>
//     *   <li>1/(A): 입력된 숫자의 역수를 계산</li>
//     *   <li>sqr: 입력된 숫자의 제곱을 계산</li>
//     *   <li>²√: 입력된 숫자의 제곱근을 계산</li>
//     *   <li>기본 연산: 더하기, 빼기, 곱하기, 나누기 연산 처리</li>
//     * </ul>
//     * </p>
//     *
//     * @param text 버튼에 표시될 텍스트
//     * @param result 결과를 표시하는 JTextField
//     * @param privew 계산 과정을 보여주는 JTextField
//     * @param temp 입력된 값을 저장하는 Stack
//     * @param preveiwStack 계산 과정을 저장하는 Stack
//     */
//    public JButtonS(String text, JTextField  result, JTextField  privew, Stack temp, Stack preveiwStack) {
//        super.setBackground(c);
//        setBorderPainted(false);
//        setFocusPainted(false);
//        this.setText(text);
//        setFont(font);
//
//        addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String strNum = "";
//
//                for(int i=0; i < temp.size(); i++) { // 하나의 문자열로 가공
//                    strNum += temp.get(i);
//                }
//
//                double num;
//                String numStr = strNum.replaceFirst("^0+", ""); // 0은 한 번만 나오게 가공
//                if(numStr.isEmpty() || numStr.isBlank()) // 문자의 가독성을 높이는 가공
//                    num = 0;
//                else
//                    num = Double.parseDouble(numStr);
//
//                // 퍼센트 연산 기능 처리
//                if(Objects.equals(text, "％")){
//                    double percentNum;
//                    if(preveiwStack.isEmpty() || Objects.equals(result.getText(), "0")){
//                        privew.setText("0");
//                        result.setText("0");
//                        temp.clear();
//                    }
//                    else if(preveiwStack.size() >= 2){
//                        percentNum = Double.parseDouble(String.valueOf(preveiwStack.get(0)));
//                        String oper = String.valueOf(preveiwStack.get(1));
//                        double percentResult = (percentNum/100) * percentNum;
//                        privew.setText(String.format("%s %s %s", result.getText(), oper, formatDouble(percentResult)));
//                        result.setText(String.format("%s", formatDouble(percentResult)));
//                    }
//                }
//
//                String resultStr = result.getText();
//                String previewStr = resultStr.replace(",", "");
//
//                //전체 리셋 기능 처리
//                if(Objects.equals(text, "C")){
//                    preveiwStack.clear();
//                    temp.clear();
//                    privew.setText("");
//                    Font font = new Font("Dialog", Font.BOLD, 48);
//                    result.setFont(font);
//                    result.setText("0");
//                }
//
//                // 결과창 리셋 기능 처리
//                if(Objects.equals(text, "CE")){
//                    temp.clear();
//                    Font font = new Font("Dialog", Font.BOLD, 48);
//                    result.setFont(font);
//                    result.setText("0");
//                }
//
//                // 지우기 연산 처리
//                if(Objects.equals(text, "<")){
//                    resultStr = resultStr.replace(",", "");
//                    resultStr = resultStr.substring(0, resultStr.length()-1);
////                    double resultNum = Double.parseDouble(resultStr);
////                    resultStr = formatDouble(resultNum);
//
//                    Font font = new Font("Dialog", Font.BOLD, 48);
//                    result.setFont(font);
//                    temp.pop();
//
//                    if(resultStr.isEmpty()){
//                        Font fonts = new Font("Dialog", Font.BOLD, 48);
//                        result.setFont(fonts);
//                        result.setText("0");
//                    } else if (temp.size() < 10) {
//                        Font fonts = new Font("Dialog", Font.BOLD, 48);
//                        result.setFont(fonts);
//                        result.setText(resultStr);
//                    }
//                    else if (temp.size() == 16) {
//                        Font fonts = new Font("Dialog", Font.BOLD, 45 - temp.size());
//                        result.setFont(fonts);
//                        result.setText(resultStr);
//                    } else {
//                        Font fonts = new Font("Dialog", Font.BOLD, 47 - temp.size());
//                        result.setFont(fonts);
//                        result.setText(resultStr);
//                    }
//                }
//
//                // 1/(A) 연산 처리
//                if(Objects.equals(text, "¹／χ")){
//                    double number = Double.parseDouble(previewStr);
//                    number = 1 / number;
//                    privew.setText(String.format("1/(%s)", previewStr));
//                    result.setText(String.format("%s", formatDouble(number)));
//                    temp.clear();
//                }
//
//                // sqr 연산 처리
//                if(Objects.equals(text, "χ²")){
//                    double number = Double.parseDouble(previewStr);
//                    number *= number;
//                    privew.setText(String.format("sqr(%s)", previewStr));
//                    result.setText(String.format("%s", formatDouble(number)));
//                    temp.clear();
//                }
//
//                // root 연산 처리
//                if(Objects.equals(text, "²√χ")){
//                    double number = Double.parseDouble(previewStr);
//                    number = Math.sqrt(number);
//                    privew.setText(String.format("√(%s)", previewStr));
//                    result.setText(String.format("%s", formatDouble(number)));
//                    temp.clear();
//                }
//
//                // 더하기 연산 처리
//                if (Objects.equals(text, "+")) {
////                     비어있을 때는 => "0 + " 이게 뜸
//                    if (Objects.equals(resultStr, "0")) {
//
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "+")) {
//                            preveiwStack.push("+");
//                            privew.setText(String.join(" ", preveiwStack));
//                        }
//                        privew.setText("0 +  ");
//                        temp.clear();
//                    }
//                    else if (!preveiwStack.isEmpty() || num != 0) { // 'A + ' => "3 + " 이케 떠야함
//
//                        preveiwStack.push(previewStr);
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "+"))
//                            preveiwStack.push("+");
//                        privew.setText(String.format("%s +  ", previewStr));
//                        temp.clear();
//                    }
//                }
//
//                // 빼기 연산 처리
//                if (Objects.equals(text, "-")) {
//
//                    if (Objects.equals(result.getText(), "0")) {
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "-")) {
//                            preveiwStack.push("-");
//                            privew.setText(String.join(" ", preveiwStack));
//                        }
//                        privew.setText("0 -  ");
//                        temp.clear();
//                    }
//                    else if (!preveiwStack.isEmpty() || num != 0) {
//                        preveiwStack.push(previewStr);
//
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "-"))
//                            preveiwStack.push("-");
//
//                        privew.setText(String.format("%s -  ", previewStr));
//                        temp.clear();
//                    }
//                }
//
//                // 곱하기 연산 처리
//                if (Objects.equals(text, "×")) {
//
//                    if (Objects.equals(result.getText(), "0")) {
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "×")) {
//                            preveiwStack.push("×");
//                            privew.setText(String.join(" ", preveiwStack));
//                        }
//                        privew.setText("0 *  ");
//                        temp.clear();
//                    }
//                    else if (!preveiwStack.isEmpty() || num != 0) {
//                        preveiwStack.push(previewStr);
//
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "×"))
//                            preveiwStack.push("×");
//
//                        privew.setText(String.format("%s ×  ", previewStr));
//                        temp.clear();
//                    }
//                }
//
//                // 나누기 연산 처리
//                if (Objects.equals(text, "÷")) {
//
//                    if (Objects.equals(result.getText(), "0")) {
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "÷")) {
//                            preveiwStack.push("÷");
//                            privew.setText(String.join(" ", preveiwStack));
//                        }
//                        privew.setText("0 ÷  ");
//                        temp.clear();
//                    }
//                    else if (!preveiwStack.isEmpty() || num != 0) {
//                        preveiwStack.push(previewStr);
//
//                        if (preveiwStack.isEmpty() || !Objects.equals(preveiwStack.peek(), "÷"))
//                            preveiwStack.push("÷");
//
//                        privew.setText(String.format("%s ÷  ", previewStr));
//                        temp.clear();
//                    }
//                }
//            }
//        });
//    }
//}

