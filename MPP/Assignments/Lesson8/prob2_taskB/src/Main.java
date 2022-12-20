import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("GMC", 24100.0, 18));
        products.add(new Product("Nissan", 25400.0, 9));
        products.add(new Product("Ford", 25100.0, 66));
        products.add(new Product("Infiniti", 57800.0, 51));
        products.add(new Product("Lincoln", 31700.0, 50));

        Collections.sort(products, new ProductTitleComparator());
        System.out.println(products);
    }
}