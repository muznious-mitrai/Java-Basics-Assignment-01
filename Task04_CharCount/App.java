package Task04_CharCount;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter String: ");
            String string = scanner.nextLine();
            if (string.equalsIgnoreCase("99")) {
                scanner.close();
                System.exit(0);
            }

            HashMap<Character, Integer> cMap = new HashMap<>();
            char[] chars = string.toCharArray();
            for (char c : chars) {
                if (c == ' ') continue;
                cMap.put(c, cMap.get(c) == null ? 1 : cMap.get(c) + 1);
            }
            cMap.forEach((c, i) -> System.out.println(c + ":" + i));
        }

    }
}
