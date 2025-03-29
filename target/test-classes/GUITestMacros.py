import pyautogui
import time

# 버튼 위치 데이터
button_positions = {
    # 숫자 버튼
    '0': {'x': 920, 'y': 745},
    '1': {'x': 840, 'y': 660},
    '2': {'x': 920, 'y': 660},
    '3': {'x': 1000, 'y': 660},
    '4': {'x': 840, 'y': 615},
    '5': {'x': 920, 'y': 615},
    '6': {'x': 1000, 'y': 615},
    '7': {'x': 840, 'y': 575},
    '8': {'x': 920, 'y': 575},
    '9': {'x': 1000, 'y': 575},

    # 연산 버튼
    '+': {'x': 1080, 'y': 660},  # 더하기
    '-': {'x': 1080, 'y': 615},  # 빼기
    '*': {'x': 1080, 'y': 575},  # 곱하기
    '/': {'x': 1080, 'y': 530},  # 나누기
    '=': {'x': 1080, 'y': 745}    # '=' 버튼
}

# 잠시 대기 (계산기 프로그램이 열릴 시간을 주기 위해)
time.sleep(1)

# 버튼 클릭 함수
def click_button(button):
    if button in button_positions:
        pos = button_positions[button]
        pyautogui.click(pos['x'], pos['y'])
        time.sleep(0.5)  # 클릭 간 대기시간

# 매크로 테스트 실행 예시
click_button('1')  # '1' 버튼 클릭
click_button('+')   # '+' 버튼 클릭
click_button('2')   # '2' 버튼 클릭
click_button('=')   # '=' 버튼 클릭
