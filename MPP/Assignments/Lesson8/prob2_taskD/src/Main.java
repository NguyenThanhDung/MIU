import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    enum SortMethod { PRICE, TITLE }

    public void sort(List<Product> products, SortMethod sortMethod){
        Collections.sort(products, (p1, p2) -> {
            if(sortMethod == SortMethod.PRICE) {
                if (p1.price == p2.price) return 0;
                else if (p1.price < p2.price) return -1;
                return 1;
            } else {
                int result = p1.title.compareTo(p2.title);
                if (result == 0) {
                    if (p1.model == p2.model) return 0;
                    else if (p1.model < p2.model) return -1;
                    return 1;
                } else {
                    return result;
                }
            }
        });
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("GMC", 24100.0, 18));
        products.add(new Product("Nissan", 25400.0, 9));
        products.add(new Product("Ford", 25100.0, 66));
        products.add(new Product("Nissan", 32700.0, 18));
        products.add(new Product("GMC", 19100.0, 12));
        products.add(new Product("Infiniti", 57800.0, 51));
        products.add(new Product("Lincoln", 31700.0, 50));

        Main main = new Main();
        main.sort(products, SortMethod.PRICE);
        System.out.println(products);
        main.sort(products, SortMethod.TITLE);
        System.out.println(products);
    }
}