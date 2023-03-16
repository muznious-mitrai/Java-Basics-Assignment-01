package Task05_SortStrings;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter words in one line, use spaces");
            String string = scanner.nextLine();
            if (string.equalsIgnoreCase("99")) {
                scanner.close();
                System.exit(0);
            }

            List<String> stringList = Arrays.asList(string.split(" "));

            //Using TreeSet for Natural Order, this doesn't keep duplicate values
            TreeSet<String> treeSet = new TreeSet<>(stringList);
            treeSet.forEach(t -> System.out.print(t + " "));
            System.out.println("\t(Using TreeSet for Natural Order)");

            //Using Stream API sort function, this keeps duplicate values
            stringList.stream().sorted(Comparator.naturalOrder()).forEach(t -> System.out.print(t + " "));
            System.out.println("\t(Using Stream API sort function)");


        }

    }

}
