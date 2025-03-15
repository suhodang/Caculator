package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Stack;

/**
 * 계산기 GUI 메인 클래스입니다.
 *
 * @author 2023011794_Ji Woo Park (gav705@naver.com)
 * @version 0.8.15
 * @since 0.0.1
 *
 * {@code @created} 2024-10-13
 * {@code @lastModified} 2024-10-22
 *
 * {@code @changelog}
 * <ul>
 *   <li>2024-10-13: 최초 생성</li>
 *   <li>2024-10-18: GUI 완성 </li>
 *   <li>2024-10-22: 필드 생성 </li>
 *   <li>2024-10-29: 기능 모두 구현 </li>
 * </ul>
 */
public class Calculator extends JFrame {

    Stack<String> temp = new Stack<>();
    Stack<String> result = new Stack<>();
    Stack<String> preResult = new Stack<>();

    Stack<String> memory = new Stack<>();
    Stack<String> memoryTemp = new Stack<>();
    String[] memoryArr = {"MC", "MR", "M+", "M-", "MS", "M∨"}; // 나중에 Mv 구현.
    String[] numberPadArr = {
            "％", "CE", "C", "<",
            "¹／χ", "χ²", "²√χ", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
    };
    JTextField preview, resultView;

    /**
     * 계산기 GUI 속성을 설정합니다.
     *
     * {@code @created} 2024-10-13
     * {@code @lastModified}
     *
     * {@code @changelog}
     * <ul>
     *   <li>2024-10-13: 최초 생성</li>
     *   <li>2024-10-18: GUI 완성 </li>
     * </ul>
     */
    public Calculator() {
        setTitle("계산기");
        setMinimumSize(new Dimension(336, 509));
        setSize(340, 510);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage("img/cal.png");
        setIconImage(img);

        showMenu();
        showResult();
        showNumBtn();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 주어진 실수(double) 값을 문자열 형식으로 변환합니다.
     *
     * {@code @created} 2024-10-27
     * {@code @lastModified} 2024-10-28
     *
     * @param number 변환할 실수(double) 값
     * @return 소수점 이하가 없으면 정수형, 있으면 실수형으로 변환된 문자열
     */
    public static String formatDouble(double number) {
        // 소수점 이하가 0인지 확인
        if (number == (long) number) {
            return String.format("%,d", (long) number);
        } else {
            return String.format("%s", number);
        }
    }

    /**
     * 주어진 스택(preResult)을 기반으로 산술 연산을 수행하고 결과를 업데이트합니다.
     *
     * <p>
     * 스택을 역순으로 순회하여 가장 최근에 입력된 연산자를 찾고,
     * 해당 연산자 기준으로 이전과 다음 숫자를 가져와 연산을 수행합니다.
     * 연산 결과는 `preview`와 `resultView`에 표시되며,
     * 계산 도중 에러가 발생할 경우 (예: 0으로 나누기) 에러 메시지를 출력합니다.
     * </p>
     *
     * @param preResult 연산 과정에서 사용되는 스택 (연산자와 피연산자 리스트)
     *
     */
    public void arithmetic(Stack preResult) {
        // 연산자를 찾기 위해 preResult 리스트를 순회
        String operation = null;
        int position = -1;

        String str = resultView.getText();
        str = str.replace(",", "");

        // 연산자 찾기
        for (int i = preResult.size()-1; i >= 0; i--) {
            String current = (String) preResult.get(i);
            if (isOperator(current)) {
                operation = current;
                position = i ;
                break;
            }
        }

        int index = position;

        double num2 = Double.parseDouble(String.valueOf(preResult.get(index + 1))); // 뒤에 있는 수
        if ((index - 1) == -1) {
            preview.setText(String.format("0 %s %s =  ", operation, formatDouble(num2)));
            resultView.setText(String.format("%s", formatDouble(num2)));
        }
        else {
            double num1 = Double.parseDouble(String.valueOf(preResult.get(index - 1))); // 앞에 있는 수
            double num3;

            if (position == -1) {
                preview.setText(String.format("%s =  ", str));
            } else {
                num3 = Double.parseDouble(str);

                double result = calculateResult(num1, num2, operation);

                // 계산 결과가 에러인 경우 (예: 0으로 나누기)
                if (result == Double.MIN_VALUE) {
                    preview.setText("0으로 나눌 수 없습니다");
                    return;
                }

//                System.out.println("num1: "+num1);
//                System.out.println("num2: "+num2);
//                System.out.println("num3: "+num3);
//                System.out.println(preResult);
//                System.out.println("==============");

                if (Objects.equals(num2, num3)) {
                    preview.setText(String.format("%s %s %s =  ", formatDouble(num1), operation, formatDouble(num2)));
                    resultView.setText(String.format("%s", formatDouble(result)));
                } else if (preResult.get(index - 1) == null) {
                    preview.setText(String.format("0 %s %s =  ", operation, formatDouble(num2)));
                    resultView.setText(String.format("%s", formatDouble(result)));
                } else {
                    String doubleResult = formatDouble(calculateResult(num3, num2, operation));
                    preview.setText(String.format("%s %s %s =  ", formatDouble(num3), operation, formatDouble(num2)));
                    resultView.setText(String.format("%s", doubleResult));
                }
            }
        }
        temp.clear();
    }

    /**
     * 주어진 문자열이 산술 연산자인지 확인합니다.
     *
     * {@code @created} 2024-10-27
     * {@code @lastModified} 2024-10-27
     *
     * {@code @changelog}
     * <ul>
     *   <li>2024-10-27: 최초 생성</li>
     * </ul>
     *
     * @param str 확인할 문자열
     * @return 문자열이 연산자이면 true, 그렇지 않으면 false
     */
    private boolean isOperator(String str) {
        return "+".equals(str) || "-".equals(str) ||
                "×".equals(str) || "÷".equals(str);
    }

    /**
     * 두 숫자(`num1`과 `num2`)와 연산자(`operation`)에 따라 산술 연산을 수행합니다.
     *
     * <p>
     * 주어진 연산자에 따라 덧셈, 뺄셈, 곱셈 또는 나눗셈을 수행하며,
     * 나눗셈의 경우 `num2`가 0이면 최소값 `Double.MIN_VALUE`를 반환합니다.
     * 정의되지 않은 연산자일 경우에도 `Double.MIN_VALUE`를 반환합니다.
     * </p>
     *
     * {@code @created} 2024-10-27
     * {@code @lastModified} 2024-10-30
     *
     * {@code @changelog}
     * <ul>
     *   <li>2024-10-27: 최초 생성</li>
     * </ul>
     *
     * @param num1 첫 번째 피연산자
     * @param num2 두 번째 피연산자
     * @param operation 산술 연산자 ("+", "-", "×", "÷")
     * @return 연산 결과, 연산이 불가능한 경우 `Double.MIN_VALUE`
     */
    private double calculateResult(Double num1, Double num2, String operation) {
        return switch (operation) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "×" -> num1 * num2;
            case "÷" -> {
                if (num2 != 0) {
                    yield (double) num1 / num2;
                }
                yield Double.MIN_VALUE;
            }
            default -> Double.MIN_VALUE;
        };
    }

    /**
     * 계산기 인터페이스의 메뉴 표시줄을 초기화하고 설정합니다.
     *
     * <p>
     * 이 메서드는 메뉴 바에 표시될 버튼과 라벨을 설정하고,
     * 이미지 아이콘을 크기에 맞게 조정하여 메뉴에 추가합니다.
     * 기본 메뉴에는 설정 버튼(`settingBtn`)과 계산기 이름(`calculatorName`)이 포함되며,
     * 이후 기록 기능을 위한 버튼(`recordBtn`)이 추가될 수 있습니다.
     * </p>
     *
     * <p>
     * 메뉴 구성 요소는 `toolBarPanel`에 배치되며,
     * `subToolBtnPanel`과 `subToolPanel1`에 개별 구성 요소가 담겨 설정됩니다.
     * </p>
     */
    private void showMenu() {
        ImageIcon recordImg = new ImageIcon("img/record.png");
        Image img = recordImg.getImage();
        Image changeImg = img.getScaledInstance(17, 17, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);

        JPanel toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new GridLayout());
        JPanel subToolBtnPanel = new JPanel(new BorderLayout());
        subToolBtnPanel.setPreferredSize(new Dimension(40, 0));


        JPanel subToolPanel1 = new JPanel();
        Font settingBtnFont = new Font("맑은 고딕", Font.BOLD, 20);
        JLabel settingBtn = new JLabel(" ≡");
        settingBtn.setPreferredSize(new Dimension(30, 20));
        settingBtn.setFont(settingBtnFont);

        JLabel calculatorName = new JLabel("표준");
        calculatorName.setFont(settingBtnFont);

        subToolPanel1.add(settingBtn);
        subToolPanel1.add(calculatorName);

        /*
        나중에 기록 기능 구현
         */
//        JPanel subToolPanel2 = new JPanel();
//        JButton recordBtn = new JButton(changeIcon);
//        recordBtn.setBackground(new Color(0xeeeeee));
//        recordBtn.setPreferredSize(new Dimension(20, 20));
//        recordBtn.setFont(settingBtnFont);

//        subToolPanel2.add(recordBtn);

        //        subToolBtnPanel.add(subToolPanel2, BorderLayout.EAST);
        subToolBtnPanel.add(subToolPanel1, BorderLayout.WEST);

        toolBarPanel.add(subToolBtnPanel);


        add(toolBarPanel);
    }

    /**
     * 메인 결과 패널(`resultMain`)을 초기화하고 구성 요소를 추가합니다.
     *
     * <p>
     * `resultMain` 패널에는 두 개의 하위 패널인 `previewPanel`과 `resultViewPanel`이 포함됩니다.
     * `previewPanel`은 계산 과정이나 현재 입력을 표시하는 `preview` 텍스트 필드를,
     * `resultViewPanel`은 최종 결과를 굵고 큰 글씨로 표시하는 `resultView` 텍스트 필드를 담고 있습니다.
     * </p>
     *
     * <p>
     * 텍스트 필드는 모두 오른쪽 정렬되어 있으며, `resultView`는 기본적으로 크기 48의 굵은 글씨체로 표시됩니다.
     * 이 메서드는 메인 계산기 인터페이스에 필요한 구성 요소를 설정하고 추가합니다.
     * </p>
     *
     * {@code @created} 2024-10-13
     * {@code @lastModified} 2024-10-22
     *
     * {@code @changelog}
     * <ul>
     *   <li>2024-10-13: 최초 생성 </li>
     *   <li>2024-10-22: 디자인 개선 </li>
     * </ul>
     *
     * @see JPanel
     * @see JTextField
     */
    private void showResult() {
        JPanel resultMain = new JPanel();
        resultMain.setLayout(new GridLayout(2, 1));

        JPanel previewPanel = new JPanel(new GridLayout());
//        previewPanel.setPreferredSize(new Dimension(10, 0));

        preview = new JTextField(27) {
            public void setBorder(Border border) {}
        };
        preview.setText("");
        preview.setEditable(false);
        preview.setHorizontalAlignment(JTextField.RIGHT);

        previewPanel.add(preview);

        JPanel resultViewPanel = new JPanel(new GridLayout());
        resultViewPanel.setPreferredSize(new Dimension(50, 40));

        Font resultFont = new Font("Dialog", Font.BOLD, 48);
        resultView = new JTextField(11) {
            public void setBorder(Border border) {}
        };
        resultView.setText("0");
        resultView.setHorizontalAlignment(SwingConstants.RIGHT);
        resultView.setFont(resultFont);
        resultView.setEditable(false);

        resultViewPanel.add(resultView);

        resultMain.add(previewPanel);
        resultMain.add(resultViewPanel);

        add(resultMain);
    }

    /**
     * 숫자, 연산 버튼과 메모리 버튼을 초기화하고 설정합니다.
     *
     * <ol>
     *     <li>
     *          이 메서드는 계산기 인터페이스의 연산, 숫자 패드와 메모리 버튼을 생성하여 배치합니다.
     *          숫자 버튼은 `numBtnPanel`에 6x4 그리드 형식으로 배치되며, 배열 `numberPadArr`에 따라 초기화됩니다.
     *     </li>
     *     <li>
     *          `mainPanel`에 메모리 패널(`memoryP`)과 숫자 패널(`numBtnPanel`)을 추가한 후, 이 패널을 계산기 인터페이스에 배치합니다.
     *     </li>
     * </ol>
     *
     * {@code @created} 2024-10-13
     * {@code @lastModified} 2024-10-22
     *
     * {@code @changelog}
     * <ul>
     *   <li>2024-10-13: 최초 생성 </li>
     *   <li>2024-10-17: 숫자, 연산, 메모리 버튼 생성 </li>
     *   <li>2024-10-18: 디자인 개선 </li>
     *   <li>2024-10-22: 버튼 ActionListener 추가 </li>
     *   <li>2024-10-30: 메모리 버튼 추가 방식 개선 </li>
     * </ul>
     */
    private void showNumBtn() {
        Font font = new Font("맑은 고딕", Font.PLAIN, 21);
        JPanel mainPanel = new JPanel();

        JPanel memoryP = new JPanel();
        memoryP.setLayout(new GridLayout(1, 6, 2, 0));
        JButtonMemory mcBtn = new JButtonMemory(memoryArr[0], resultView, memory, memoryTemp);
        JButtonMemory mrBtn = new JButtonMemory(memoryArr[1], resultView, memory, memoryTemp);
        JButtonMemory mAddBtn = new JButtonMemory(memoryArr[2], resultView, memory, memoryTemp);
        JButtonMemory mSubBtn = new JButtonMemory(memoryArr[3], resultView, memory, memoryTemp);
        JButtonMemory msBtn = new JButtonMemory(memoryArr[4], resultView, memory, memoryTemp);
//        java.JButtonMemory meViewBtn = new java.JButtonMemory(memoryArr[5], resultView, memory, memoryTemp);

        mcBtn.setEnabled(false);
        mrBtn.setEnabled(false);
//        meViewBtn.setEnabled(false);

        mcBtn.addActionListener(e -> {
            mcBtn.setEnabled(false);
            mrBtn.setEnabled(false);
//            meViewBtn.setEnabled(false);
        });

        mAddBtn.addActionListener(e -> {
            mcBtn.setEnabled(true);
            mrBtn.setEnabled(true);
//            meViewBtn.setEnabled(true);
        });

        mSubBtn.addActionListener(e -> {
            mcBtn.setEnabled(true);
            mrBtn.setEnabled(true);
//            meViewBtn.setEnabled(true);
        });

        msBtn.addActionListener(e -> {
            mcBtn.setEnabled(true);
            mrBtn.setEnabled(true);
//            meViewBtn.setEnabled(true);
        });

        memoryP.add(mcBtn);
        memoryP.add(mrBtn);
        memoryP.add(mAddBtn);
        memoryP.add(mSubBtn);
        memoryP.add(msBtn);
//        memoryP.add(meViewBtn);

        JPanel numBtnPanel = new JPanel();
        numBtnPanel.setLayout(new GridLayout(6, 4, 3, 3));
        for(int i=0; i < 24; i++) {
            JButton numberPadBtn;
            numberPadBtn = new JButtonWhite(
                    numberPadArr[i], resultView, preview,temp, preResult
            );
            if(i < 8)
                numberPadBtn = new JButtonS(
                        numberPadArr[i], resultView, preview, temp, preResult
                );
            else if(i % 4 ==3){
                numberPadBtn = new JButtonS(
                        numberPadArr[i], resultView, preview, temp, preResult
                );
                numberPadBtn.setFont(font);
            }

            if (i == 23){
                numberPadBtn.setBackground(new Color(0x00649E));
                numberPadBtn.setForeground(Color.WHITE);
                numberPadBtn.setFont(font);
                numberPadBtn.addActionListener(new ActionListener() {

                    /**
                     * '=' 버튼을 눌렀을 때 호출되어, 현재 `resultView`의 값을 가져와 계산을 수행하고 결과를 표시합니다.
                     *
                     * <ol>
                     *   <li> `resultView`에서 현재 숫자를 가져와 앞에 붙은 '0'을 제거한 후, 콤마도 제거한 상태로 `previewStr`에 저장합니다. </li>
                     *   <li> `previewStr`을 `preResult` 스택에 푸시하여 연산에 사용할 준비를 합니다. </li>
                     *   <li> 만약 `previewStr`이 스택의 마지막 값과 동일하고 스택에 다른 값이 없으면, 미리보기 필드에 해당 값을 "0 = " 형태로 출력하고 스택을 초기화합니다. </li>
                     *   <li> 다른 경우에는 `arithmetic()` 메서드를 호출하여 연산을 수행한 후, 결과를 `result` 스택에 저장합니다. </li>
                     * </ol>
                     *
                     * {@code @created} 2024-10-22
                     * {@code @lastModified} 2024-10-30
                     *
                     * {@code @changelog}
                     * <ul>
                     *   <li>2024-10-22: 최초 생성 </li>
                     *   <li>2024-10-28: 기능 구현 </li>
                     *   <li>2024-10-30: 결과창에 데이터 보내는 오류 개선 </li>
                     * </ul>
                     *
                     * @param e `ActionEvent` 객체로, '=' 버튼이 눌렸을 때 발생합니다.
                     */
                    public void actionPerformed(ActionEvent e) {
                        String num = resultView.getText();
                        String numStr = num.replaceFirst("^0+", "");
                        String previewStr;
                        if(numStr.isEmpty() || numStr.isBlank())
                            previewStr = "0";
                        else
                            previewStr = numStr;

//                        System.out.println("previewStr: " + previewStr);
                        previewStr = previewStr.replace(",", "");
                        preResult.push(previewStr);

                        // 아무것도 없을 시 미리보기에 0 = 출력
                        if(Objects.equals(previewStr, preResult.peek()) && preResult.size() < 2) {
                            preview.setText(String.format("%s =  ", previewStr));
                            resultView.setText(String.format("%s", resultView.getText()));
                            preResult.clear();
                            temp.clear();
                            preResult.push(previewStr);
                        } else {
                            arithmetic(preResult);
                            result.push(previewStr);
                        }
                    }
                });
            }

            numBtnPanel.add(numberPadBtn);
        }

        mainPanel.add(memoryP, BorderLayout.NORTH);
        mainPanel.add(numBtnPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

}
//end.......