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
 * @version 0.6.2
 * @since 0.0.1
 *
 * {@code @created} 2024-10-13
 * {@code @lastModified} 2024-10-30
 *
 * {@code @changelog}
 * <ul>
 *   <li>2024-10-22: 최초 생성</li>
 *   <li>2024-10-18: 메모리 버튼 기능 생성 </li>
 *   <li>2024-10-30: 오류 개선 </li>
 * </ul>
 */
class JButtonMemory extends JButton {
    Font font = new Font("맑은 고딕", Font.PLAIN, 13);
    Color c = new Color(0xeeeeee);


    /**
     * `java.JButtonMemory` 생성자: 메모리 관련 기능을 수행하는 버튼을 생성합니다.
     *
     * <p>
     * 이 생성자는 지정된 텍스트와 `resultView`, 메모리 스택(`memory`) 및 메모리 임시 스택(`memoryTemp`)을 사용하여
     * 버튼의 초기 설정과 액션 리스너를 구성합니다.
     * </p>
     *
     * @param text       버튼에 표시될 텍스트(예: "MC", "MR", "M+", "M-", "MS" 등).
     * @param resultView 결과를 표시하는 `JTextField`.
     * @param memory     메모리 값을 저장하는 스택.
     * @param memoryTemp 임시 메모리 스택.
     *
     * <p>
     * 액션 리스너 기능:
     * <ul>
     *   <li>MC: 메모리 스택 초기화</li>
     *   <li>MR: 메모리에서 가장 최근 값을 불러와 `resultView`에 표시</li>
     *   <li>M+: `resultView` 값과 메모리 값을 더하여 저장</li>
     *   <li>M-: `resultView` 값과 메모리 값을 빼서 저장</li>
     *   <li>MS: `resultView` 값을 메모리에 저장</li>
     *   <li>M∨: 메모리 값을 표시 (추후 구현 예정)</li>
     * </ul>
     * </p>
     */
    public JButtonMemory(String text, JTextField resultView, Stack memory, Stack memoryTemp) {
        super.setBackground(c);
        setBorderPainted(false);
        setFocusPainted(false);
        this.setText(text);
        setFont(font);

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String str = resultView.getText();
                str = str.replace(",", "");


                // MemoryClear(메모리 초기화)
                if (Objects.equals(text, "MC")) {
                    memory.clear();
                }

                // MemoryRoad(메모리 값 불러오기)
                if (Objects.equals(text, "MR")) {
                    resultView.setText(String.valueOf(memory.peek()));
                }

                // MemoryAdd(메모리 값 더하기)
                if (Objects.equals(text, "M+")) {
                    if (memory.isEmpty()) {
                        memoryTemp.push(str);
                        memory.push(str);
//                        System.out.println(memory);
                    }
                    else {
                        int memoryNum =  Integer.parseInt(String.valueOf(memory.peek()));
                        int tempNum =  Integer.parseInt(String.valueOf(memoryTemp.peek()));
                        memoryNum = memoryNum + tempNum;
                        memory.push(memoryNum);
//                        System.out.println(memory);
                    }
                }

                // MemorySub(메모리 값 빼기)
                if (Objects.equals(text, "M-")) {
                    if (memory.size() == 0) {
                        int strN = Integer.parseInt(str);
                        strN = strN * -1;
                        str = String.valueOf(strN);
                        memoryTemp.push(str);
                        memory.push(str);
                        System.out.println(memory);
                    }
                    else {
                        int memoryNum =  Integer.parseInt(String.valueOf(memory.peek()));
                        int tempNum =  Integer.parseInt(String.valueOf(memoryTemp.peek()));
                        tempNum = tempNum + memoryNum;
                        memory.push(tempNum);
                        System.out.println(memory);
                    }
                }

                // MemorySave(메모리 값 불러오기)
                if (Objects.equals(text, "MS")) {
                    memoryTemp.push(str);
                    memory.push(str);
                }

                // MemoryView(메모리 값 리스트 보기)
//                if (Objects.equals(text, "M∨")) {
//                    memoryTemp.push(str);
//                    memory.push(str);
//                }
            }
        });
    }
}