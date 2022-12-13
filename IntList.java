import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class IntList {

    public static void main(String[] args) {
        
        // Randomly generate a list

        int max = 200;
        int range = 1000;

        Random rand = new SecureRandom();

        List<Integer> randList = new LinkedList<>();
        for(int i = 0; i < max; i++) {
            randList.add(rand.nextInt(range));
        }

        System.out.println(">>> List: " + randList);

        filter(randList);

        String newString = randList.stream()
        .map((n) -> n.toString().concat(n.toString()))
        .collect(Collectors.joining(" "));

        List<Integer> randList2 = randList.stream()
        .map(n -> "%d%d".formatted(n,n))
        .map(Integer::parseInt) // Same method signature as Integer apply(String)
        // Method reference
        //.map(Integer.parseInt(n)) // Same as <- since it has the same method signature
        .toList();

        System.out.println(newString);
        System.out.println(randList2);


    }

    public static void filter(List<Integer> randList) {

        //Iteration way
        System.out.println("========================");
        List<Integer> resultList = new LinkedList<>();
        for(int n : randList) {
            if((n % 3) == 0) {
                resultList.add(n);
            }

        }

        System.out.println(">>> resultList: " + resultList);

        // Stream way
        resultList = randList.stream()
        // boolean test(int i)
        .filter((n) -> {
            return 0 == (n % 3); // It will pass if condition is true
        })
        .sorted()
        .limit(5)
        .toList();
        ;

        System.out.println(">>> resultList: " + resultList);

    }
    
}
