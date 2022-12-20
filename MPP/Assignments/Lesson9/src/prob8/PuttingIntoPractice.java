package prob8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PuttingIntoPractice {
    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> transactions2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted((t1, t2) -> t1.getValue() - t2.getValue())
                .collect(Collectors.toList());
        System.out.println(transactions2011);


        // Query 2: What are all the unique cities where the traders work?
        List<Trader> traders = new ArrayList<>();
        traders.addAll(Arrays.asList(raoul, mario, alan, brian));
        List<String> traderCities = traders.stream()
                .map(t -> t.getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(traderCities);


        // Query 3: Find all traders from Cambridge and sort them by name.
        List<Trader> cambridgeTraders = traders.stream()
                .filter(t -> t.getCity().compareTo("Cambridge") == 0)
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .collect(Collectors.toList());
        System.out.println(cambridgeTraders);


        // Query 4: Return a string of all traders names sorted alphabetically.
        String traderNames = traders.stream()
                .map(t -> t.getName())
                .sorted((t1, t2) -> t1.compareTo(t2))
                .reduce((n1, n2) -> n1 + ", " + n2)
                .orElse("");
        System.out.println(traderNames);

        // Query 5: Are there any trader based in Milan?
        Optional<Trader> milanTraders = traders.stream()
                .filter(t -> t.getCity().compareTo("Milan") == 0)
                .findFirst();
        milanTraders.ifPresent(System.out::println);

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        List<Transaction> updatedTransaction = transactions.stream()
                .map(t -> {
                    if(t.getTrader().getCity().equals("Milan"))
                        t.getTrader().setCity("Cambridge");
                    return t;
                })
                .collect(Collectors.toList());
        System.out.println(updatedTransaction);


        // Query 7: What's the highest value in all the transactions?
        Optional<Integer> highestValue = transactions.stream()
                .map(t -> t.getValue())
                .max((t1, t2) -> t1 - t2);
        System.out.println(highestValue.orElse(0));
    }
}
