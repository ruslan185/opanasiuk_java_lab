import java.io.IOException;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringCalculator cal = new StringCalculator();

        while (true) {
            System.out.println("Введіть вираз: ");
            String numbers = br.readLine().replace("\\n", "\n");
            int result = cal.add(numbers);

            if (result != -1) System.out.println("Ваш результат: "+result);
            else System.out.println("Ваш вираз не коректний");

            System.out.println("Бажаєте продовжити?(y/n): ");
            String yn = br.readLine().replace("\\n", "\n");
            if (Objects.equals(yn, "n")) break;
        }
    }
}