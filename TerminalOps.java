import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class TerminalOps {

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

        reduceTo(randList);
    
}

    public static void joining(List<Integer> randList) {

        System.out.println("============== JOINING ================");

        String stringList = randList.stream()
        .map(n -> "%d%d".formatted(n,n))
        .collect(Collectors.joining(" / ", "|", "|"));

        System.out.println(stringList);


    }

    public static void reducing(List<Integer> randList) {

        System.out.println("============== REDUCING ================");

        // Optional indicates that the thing may or may not contain an answer
        // If .reducing is not provided an identity
        Optional<Integer> opt = randList.stream()
        .map(n -> "%d%d".formatted(n,n))
        .map(Integer::parseInt)
        .collect(Collectors.reducing((acc, i) -> {

            System.out.printf("Total: %d, i: %d\n", acc, i);

            return acc + i;

            })

        );

        if(opt.isPresent()) {
            System.out.println(">>> Total " + opt.get()); // if opt is present, get the answer
        } else {
            System.out.println("Reducing produces no result");
        }


    }

    public static void joiningAsReducing(List<Integer> randList) {

        // joining is just a specialised version of reduce

        Optional<String> opt = randList.stream()
        .map(n -> "%d%d".formatted(n,n))
        .collect(Collectors.reducing((acc, i) -> {

            return "%s, %s".formatted(acc, i);

            })

        );

        if(opt.isPresent()) {
            System.out.println(">>> Reduction: " + opt.get()); // if opt is present, get the answer
        } else {
            System.out.println("Reducing produces no result");
        }

    }

    public static void reduceTo(List<Integer> randList) {

        System.out.println("============== REDUCING ================");

        // Definitely get an answer back as compared to Optional
        int result = randList.stream()
        .map(n -> "%d%d".formatted(n,n))
        .map(Integer::parseInt)
        .collect(Collectors.reducing(0 // total = 0 -> initalises the sum
        , (acc, i) -> {

            System.out.printf("Total: %d, i: %d\n", acc, i);

            return acc + i;

            })

        );


    }
}
