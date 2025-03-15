import java.util.Scanner;

public class InputExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        
        System.out.println("안녕하세요, " + name + "님!");
        
        scanner.close();
    }
}
