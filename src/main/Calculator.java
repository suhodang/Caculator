package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Stack;

/**
 * 계산기 GUI 클래스 (연산 기능을 CalculatorService에 위임)
 */
public class Calculator extends JFrame {
    private final CalculatorService calculatorService = new CalculatorService(); // 연산 기능 위임
    private Stack<String> temp = new Stack<>();
    private Stack<String> preResult = new Stack<>();
    private JTextField preview, resultView;

    String[] numberPadArr = {
            "％", "CE", "C", "<",
            "¹／χ", "χ²", "²√χ", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
    };

    public Calculator() {
        setTitle("계산기");
        setMinimumSize(new Dimension(336, 509));
        setSize(340, 510);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        showMenu();
        showResult();
        showNumBtn();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showMenu() {
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
        subToolBtnPanel.add(subToolPanel1, BorderLayout.WEST);

        toolBarPanel.add(subToolBtnPanel);
        add(toolBarPanel);
    }

    private void showResult() {
        JPanel resultMain = new JPanel();
        resultMain.setLayout(new GridLayout(2, 1));

        JPanel previewPanel = new JPanel(new GridLayout());
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

    private void showNumBtn() {
        Font font = new Font("맑은 고딕", Font.PLAIN, 21);
        JPanel mainPanel = new JPanel();
        JPanel numBtnPanel = new JPanel();
        numBtnPanel.setLayout(new GridLayout(6, 4, 3, 3));

        for (int i = 0; i < 24; i++) {
            JButton numberPadBtn = new JButton(numberPadArr[i]);
            numberPadBtn.setFont(font);

            if (i == 23) { // "=" 버튼
                numberPadBtn.setBackground(new Color(0x00649E));
                numberPadBtn.setForeground(Color.WHITE);
                numberPadBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleEqualButton();
                    }
                });
            } else {
                numberPadBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(((JButton) e.getSource()).getText());
                    }
                });
            }
            numBtnPanel.add(numberPadBtn);
        }

        mainPanel.add(numBtnPanel);
        add(mainPanel);
    }

    private void handleButtonClick(String buttonText) {
        if ("+".equals(buttonText) || "-".equals(buttonText) || "×".equals(buttonText) || "÷".equals(buttonText)) {
            preResult.push(resultView.getText().replace(",", ""));
            preResult.push(buttonText);
            preview.setText(String.join(" ", preResult));
            temp.clear();
        } else if ("C".equals(buttonText)) {
            preResult.clear();
            temp.clear();
            preview.setText("");
            resultView.setText("0");
        } else if ("CE".equals(buttonText)) {
            temp.clear();
            resultView.setText("0");
        } else {
            temp.push(buttonText);
            resultView.setText(String.join("", temp));
        }
    }

    private void handleEqualButton() {
        if (preResult.size() < 2) return;

        String num1Str = preResult.get(preResult.size() - 2);
        String operator = preResult.get(preResult.size() - 1);
        String num2Str = resultView.getText().replace(",", "");

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = calculatorService.calculate(num1, num2, operator);
            resultView.setText(String.valueOf(result));
            preview.setText(String.format("%s %s %s = ", num1, operator, num2));
        } catch (Exception e) {
            resultView.setText("오류");
        }

        preResult.clear();
        temp.clear();
    }
}
.
